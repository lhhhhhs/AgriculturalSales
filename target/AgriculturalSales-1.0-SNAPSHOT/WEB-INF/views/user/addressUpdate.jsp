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
    <form action="${APP_PATH}/updateAddress" method="post" style="margin-left: 100px;margin-right: 100px;">
        <input type="hidden" name="id" value="${address.id}">
        <input type="hidden" name="orderId" value="${orderId}">
        <div class="form-group has-success col-md-6">
            <label for="exampleInputEmail1">收件人</label>
            <input type="text" class="form-control" name="consignee" value="${address.consignee}" id="exampleInputEmail1" placeholder="收件人">
        </div>
        <div class="form-group has-success col-md-6">
            <label for="exampleInputPassword1">手机号</label>
            <input type="text" name="phone" class="form-control" value="${address.phone}" id="exampleInputPassword1" placeholder="Password">
        </div>
        <div class="row">
            <div class="col-md-3">
                <label >省</label>
                <input type="text" name="province" value="${address.province}" class="form-control" placeholder="Jane Doe">
            </div>
            <div class="col-md-3">
                <label >市</label>
                <input type="text" name="city" value="${address.city}" class="form-control"  placeholder="Jane Doe">
            </div>
            <div class="col-md-3">
                <label >区/县</label>
                <input type="text" name="area" value="${address.area}" class="form-control"  placeholder="Jane Doe">
            </div>
        </div>
        <label style="margin-top: 10px;">详细地址</label>
        <textarea style="margin-top: 10px;" class="form-control" name="detailed" rows="3">${address.detailed}</textarea>
        <button style="margin-top: 10px;margin-left: 45%;" type="submit" class="btn btn-default">修改</button>
    </form>

</div>
<%--包含脚--%>
<%@include file="../common/foot.jsp" %>
<script type="text/javascript">
</script>
</body>
</html>

