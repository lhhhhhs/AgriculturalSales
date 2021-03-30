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
    <span style="color:red;">${msg}</span>
    <c:forEach items="${orderList}" var="order">
    <div class="panel panel-default" style="margin-left: 100px;margin-right: 100px;margin-top: 20px;">
        <div class="panel-heading">订单编号:${order.id}</div>
        <div class="panel-body orderBody">
            <span style="margin-right: 10px;">订单总金额：￥${order.totalPrice}</span>
            <span style="margin-left: 50px;" id="timeBtn">订单创建时间:${order.creatTime}</span>
            <span style="margin-left: 100px;">
                <c:if test="${order.status == 1}">未付款</c:if>
                <c:if test="${order.status == 2}">待发货</c:if>
                <c:if test="${order.status == 3}">已发货</c:if>
                <c:if test="${order.status == 4}">待评价</c:if>
                <c:if test="${order.status == 5}">已完成</c:if>
            </span>
            <c:if test="${order.status == 1}"><a href="${APP_PATH}/delOrder?orderId=${order.id}" class="btn btn-danger" id="btn" style="float: right">取消订单</a></c:if>
            <a href="${APP_PATH}/address?orderId=${order.id}" class="btn btn-success" style="float: right;margin-right: 10px;">查看地址</a>
            <a href="${APP_PATH}/orderDetails?orderId=${order.id}" class="btn btn-success" style="float: right;margin-right: 10px;">订单详情</a>
            <c:if test="${order.status == 1}"><a href="${APP_PATH}/payment?orderId=${order.id}" class="btn btn-success" style="float: right;margin-right: 10px;">去付款</a></c:if>
            <c:if test="${order.status == 4}"><a href="${APP_PATH}/evaluation?orderId=${order.id}" class="btn btn-success" style="float: right;margin-right: 10px;">去评论</a></c:if>
            <c:if test="${order.status == 3}"><a href="${APP_PATH}/confirmReceipt?orderId=${order.id}" class="btn btn-success" style="float: right;margin-right: 10px;">确认收货</a></c:if>
        </div>
    </div>
    </c:forEach>
</div>
<%--包含脚--%>
<%@include file="../common/foot.jsp" %>



</body>
</html>

