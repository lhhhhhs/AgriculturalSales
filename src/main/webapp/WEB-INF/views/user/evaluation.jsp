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
    <form action="${APP_PATH}/saveEvaluation" method="post">
        <input type="hidden" name="orderId" value="${orderId}">
            <textarea name="evaluation" class="form-control" rows="3" style="margin-top: 10px;"></textarea>
            <input type="submit" style="margin-left: 48%" class="btn btn-danger" value="保存"></input>
    </form>
</div>
<%--包含脚--%>
<%@include file="../common/foot.jsp" %>
<script type="text/javascript">
</script>
</body>
</html>

