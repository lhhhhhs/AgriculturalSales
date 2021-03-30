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
<%@include file="common/head.jsp" %>
<%--包含搜索--%>
<%@include file="common/search.jsp" %>


<div class="bodyMain">

</div>
<div class="bodyMain ProductShow">
    <div style="margin-left: 100px;margin-right: 100px;">
        <div style="margin: 10px;border: 1px solid #cae4e7;">
            <div style="margin: 15px;width: 100%;height: 20px;">用户名：${evaluation.uName} 时间：${evaluation.createTime}</div>
            <hr>
            <div style="margin: 30px;">${evaluation.evaluationText}</div>
        </div>
        <p style="margin-left: 10px;">回复：</p>
        <c:forEach items="${replyList}" var="reply">
            <div style="margin: 10px;border: 1px solid #b1e6e9;">
                <div style="margin: 5px;width: 100%;height: 20px;">用户名：${reply.uName} 时间：${reply.createTime}</div>
                <hr>
                <div style="margin: 30px;">${reply.evaluationText}</div>
            </div>
        </c:forEach>
        <form action="${APP_PATH}/sendReply" method="post">
            <input type="hidden" name="id" value="${id}">
            <textarea class="form-control" name="replyText" rows="3" placeholder="请填写您的评论！"></textarea>
            <button style="float: right;margin-top: 5px;" type="submit" class="btn btn-default">回复</button>
        </form>
    </div>

</div>
<%--包含脚--%>
<%@include file="common/foot.jsp" %>
<script type="text/javascript">
    $(function () {
    })
</script>
</body>
</html>

