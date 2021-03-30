<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<!DOCTYPE html>
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
    <c:if test="${not empty msg}"><p style="text-align: center;color:#cae4e7;">${msg}</p></c:if>
    <button class="btn btn-success" style="margin-bottom: 10px;" id="allBtn">全选</button>
    <button class="btn btn-success" style="margin-bottom: 10px;" id="noOneBtn">全不选</button>
    <c:forEach items="${cartList}" var="item">
    <div class="panel panel-info">
        <div class="panel-heading">${item.product.name}</div>
        <div class="panel-body cartBody" style="height: 100px;">
            <div >
                <input  type="checkbox" name="checkbox" style="size: 25px;">
                <input type="hidden" name="id" value="${item.id}">
                <input type="hidden" name="price" value="${item.totalPrice}">
            </div>
            <div style="width: 100px;height: 120%;padding: 0px;margin-top: -5px;">
                <img  src="${APP_PATH}${item.product.imgPath}" width="100%" height="100%">
            </div>
            <div >单价：￥${item.product.price}</div>
            <div style="margin-top: -10px;" > <a href="${APP_PATH}/reductProduct?id=${item.id}">-</a> <span>数量：${item.pCount}</span> <a href="${APP_PATH}/addProduct?id=${item.id}">+</a> </div>
            <div > 总价：￥${item.totalPrice}</div>
            <div style="">
                <a style="font-size: 15px;" class="btn btn-info" href="${APP_PATH}/productDetails?id=${item.pId}">查看详情</a>
            </div>

        </div>
    </div>
    </c:forEach>
    <button class="btn btn-danger R" id="buyBtn" style="float: right;">去结算</button><div style="width: 250px;height: 30px;margin-top: 10px;font-size: 20px;color:#ff515b; font-family: 黑体" class="R">总价格：￥<span id="totalPrice">0</span></div>
</div>
<%--包含脚--%>
<%@include file="../common/foot.jsp" %>
<script type="text/javascript">
    $("#allBtn").click(function () {
        $("input[name='checkbox']").prop("checked","true");
        updateTotalPrice();
    });
    $("#noOneBtn").click(function () {
        $("input[name='checkbox']").prop("checked","");
        updateTotalPrice();
    });

    $("input[name='checkbox']").click(function () {
        updateTotalPrice();
    })

    function updateTotalPrice() {
        var totalPrice = 0.00;
        $.each($("input[name='checkbox']:checked"),function () {
            var val = $(this).parent("div").find("input:eq(2)").val();
            totalPrice = parseFloat(val)+parseFloat(totalPrice);
        })
        $("#totalPrice").text(totalPrice);
    }
    $("#buyBtn").click(function () {
        var cartList = new Array(0);
        $.each($("input[name='checkbox']:checked"),function () {
            var val = $(this).parent("div").find("input:eq(1)").val();
            $(this).parent("div").parent("div").parent("div").remove();
            cartList.push(val);
        });
        layui.use(['layer', 'form' ,'jquery'], function(){
            var layer = layui.layer,form = layui.form,$ = layui.$;
            $("#totalPrice").text(0);
                $.ajax({
                    url: "${APP_PATH}/CreateOrder?cartList="+cartList,
                    type: "POST",
                    //允许ajax携带cookie
                    xhrFields: {
                        withCredentials: true // 发送Ajax时，Request header中会带上 Cookie 信息。
                    },
                    crossDomain: true, // 发送Ajax时，Request header 中会包含跨域的额外信息，但不会含cookie（作用不明，不会影响请求头的携带）
                    success: function (res) {
                        if (res.code == 100) {
                            layer.msg("订单创建成功！快去查看吧");
                        } else {
                            layer.msg("订单创建失败！可能没有库存了！");
                        }
                    },
                    error:function (err) {
                        layer.msg("订单创建失败！");
                    }
                });
            })
    })
</script>


</body>
</html>

