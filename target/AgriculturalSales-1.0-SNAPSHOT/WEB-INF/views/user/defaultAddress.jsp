<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>茂名特产商城</title>

    <script type="text/javascript" src="${APP_PATH}/static/js/jquery-3.5.0.js"></script>
    <link
            href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
            rel="stylesheet" type="text/css"/>
    <script type="text/javascript"
            src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/asale.css"/>
    <link rel="stylesheet" type="text/css" href="${APP_PATH}/static/layui/css/layui.css"/>
    <script type="text/javascript"
            src="${APP_PATH}/static/layui/layui.js"></script>
</head>

<body>
<%--包含头--%>
<%@include file="../common/head.jsp" %>
<%--包含搜索--%>
<%@include file="../common/search.jsp" %>


<div class="bodyMain">

</div>
<div class="bodyMain ProductShow">
    <c:if test="${not empty msg}"><p style="color: red;margin-left: 100px;">${msg}</p></c:if>
    <c:if test="${empty addressList}"><p style="color: red;margin-left: 100px;">请添加地址！</p></c:if>
    <c:if test="${not empty addressList}">
    <c:forEach items="${addressList}" var="address">
        <div class="panel panel-default" style="margin-left: 100px;margin-right: 100px;margin-top: 20px;">
            <div class="panel-heading">收货人:${address.consignee} 电话:${address.phone}  <span style="margin-left: 10px;">
                <c:if test="${address.defaultStatus == 1}">当前默认地址</c:if>
            </span></div>
            <div class="panel-body orderBody">
                <span style="margin-right: 10px;float: left">地址：${address.province}${address.city}${address.area}${address.detailed}</span>

                <a href="${APP_PATH}/delAddressDefault?addressId=${address.id}" class="btn btn-danger"
                   style="float: right;margin-right: 10px;">删除地址</a>
                <a href="${APP_PATH}/editAddress?addressId=${address.id}" class="btn btn-success" style="float: right;margin-right: 10px;">编辑</a>
                <a href="${APP_PATH}/defaultAddressDefault?addressId=${address.id}" class="btn btn-success" style="float: right;margin-right: 10px;">设为默认地址</a>

            </div>
        </div>
    </c:forEach>
    </c:if>
    <button id="addBtn" class="btn btn-default" style="margin-left: 100px;">新增地址+</button>
</div>
<%--包含脚--%>
<%@include file="../common/foot.jsp" %>

<div class="modal fade bs-example-modal-lg" id="AddaddressModel" tabindex="-1" role="dialog"
     aria-labelledby="myLargeModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">新增地址</h4>
            </div>
            <form action="${APP_PATH}/AddAddressDefault" method="post">
            <div class="modal-body" style="margin-left: 20px;">
                    <div class="input-group col-md-4  has-success">
                        <div class="input-group-addon">
                            <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                        </div>
                        <input type="text" width="200px;" class="form-control" name="consignee"
                               placeholder="收件人">
                    </div>
                    <div class="input-group col-md-6  has-success" style="margin-top: 10px;">
                        <div class="input-group-addon">
                            <span class="glyphicon glyphicon-phone" aria-hidden="true"></span>
                        </div>
                        <input type="text" width="200px;" class="form-control"  name="phone"
                               placeholder="电话">
                    </div>
                    <div class="row has-success" style="margin-top: 10px;">
                        <div class="col-xs-3">
                            <select name="province" id="Province" class="form-control">
                                <option value="">请选择省</option>
                            </select>
                        </div>
                        <div class="col-xs-3">
                            <select name="city" id="City" class="form-control">
                                <option value="">请先选择市</option>
                            </select>
                        </div>
                        <div class="col-xs-3">
                            <select name="area" id="District" class="form-control">
                                <option value="">请先选择县/区</option>
                            </select>
                        </div>
                    </div>
                    <textarea name="detailed" class="form-control" rows="3" style="margin-top: 10px;"></textarea>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">放弃</button>
                <input type="submit" class="btn btn-danger" value="保存"></input>
            </div>
            </form>
        </div>
    </div>
</div>
<script src="${APP_PATH}/static/js/area_data.js"></script>
<script type="text/javascript">
    $("#editAddressBtn").click(function () {
        $('#UpdateaddressModel').modal();
    });

    $("#addBtn").click(function () {
        $('#AddaddressModel').modal();
    });
    function checkboxOnclick(id){
            // $('input[name="header"]:checked').each(function(i){
            //     $(this).removeAttr("checked");
            // });
        window.location.href="${APP_PATH}/selectAddress?orderId=${id}&addressId="+id;
    }

    //级联选择
    var Province,City,District;
    var Province1,City1,District1;
    Province = document.getElementById("Province");
    City = document.getElementById("City");
    District = document.getElementById("District");
    // 初始化
    (function(){
        var ProvinceHtml = "";
        region.forEach(function(index){
            ProvinceHtml += "<option value='"+index.provinceCode+"'>"+index.provinceName+"</option>";
        });
        Province.innerHTML = ProvinceHtml;
    })();
    Province.onchange = function(){
        var that = this;
        var CityHtml = "";
        // 初始化县
        District.innerHTML = "<option value=''>请先选择市</option>";
        region.forEach(function(index){
            if(index.provinceCode == that.value){
                index.mallCityList.forEach(function(child){
                    CityHtml += "<option value='"+child.cityCode+"'>"+child.cityName+"</option>";
                });
                City.innerHTML = CityHtml;
                return ;
            }
        });
    };
    City.onchange = function(){
        var that = this;
        var DistrictHtml = "";
        region.forEach(function(index){
            if(index.provinceCode == Province.value){
                index.mallCityList.forEach(function(child){
                    if(child.cityCode == that.value){
                        child.mallAreaList.forEach(function(sun){
                            DistrictHtml += "<option value='"+sun.areaCode+"'>"+sun.areaName+"</option>";
                        });
                        District.innerHTML = DistrictHtml;
                        return ;
                    }
                });
            }
        });
    };
</script>
</body>
</html>

