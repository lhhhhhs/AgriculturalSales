<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>茂名特产商城</title>
    <link rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/asale.css"/>
    <script type="text/javascript" src="${APP_PATH}/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="${APP_PATH}/static/js/script.js"></script>
</head>
<body>
<%@include file="common/head.jsp"%>
<%--<div class="bodyMain header"> <a href="index.html" class="L logo" target="_blank"><img src="images/logo.gif" width="160" height="90" alt="茂名特产商城logo"/></a>--%>
<%--    <div class="phcSearch L">--%>
<%--        <form action="search.php" method="get" id="searchForm" name="searchForm" onSubmit="return checkSearchForm()" class="search-form">--%>
<%--            <input class="search_input L" type="text" name="keywords" id="keyword" value="请输入您想要的商品" autocomplete="off">--%>
<%--            <input type="hidden" value="k1" name="dataBi">--%>
<%--            <button type="submit" class="submit_btn L"></button>--%>
<%--        </form>--%>
<%--        <div class="search-hot" id="hotKeywordsShow"> <a href="#" class="hot-item">U盘定制</a> <a href="#" class="hot-item">展会礼品</a> <a href="#" class="hot-item">地产礼品</a> <a href="#" class="hot-item">小米礼品定制</a> <a href="#" class="hot-item">水杯定制</a> <a href="#" class="hot-item">双肩包定制</a></div>--%>
<%--    </div>--%>
<%--</div>--%>
</body>
</html>
