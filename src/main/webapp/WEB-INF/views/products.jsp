<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>茂名特产商城</title>
    <link rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/asale.css"/>
    <script type="text/javascript" src="${APP_PATH}/static/js/jquery-3.5.0.js"></script>
    <link
            href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
            rel="stylesheet" type="text/css"/>
    <script type="text/javascript"
            src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
<%--包含头--%>
<%@include file="common/head.jsp" %>
<%--包含搜索--%>
<%@include file="common/search.jsp" %>

<c:if test="${not empty pageInfo.list}">
<div class="bodyMain">
    <div class="listNote L">
        <div class="orderBg L"><a href="<c:if test="${money==0}">${APP_PATH }/product?pn=1<c:if test="${not empty KeyWord}">&KeyWord=${KeyWord}</c:if><c:if test="${not empty k1}">&k1=${k1}</c:if><c:if test="${not empty k2}">&k2=${k2}</c:if><c:if test="${not empty k3}">&k3=${k3}</c:if>&money=1&sales=0</c:if><c:if test="${money != 0}">${APP_PATH }/product?pn=1<c:if test="${not empty KeyWord}">&KeyWord=${KeyWord}</c:if><c:if test="${not empty k1}">&k1=${k1}</c:if><c:if test="${not empty k2}">&k2=${k2}</c:if><c:if test="${not empty k3}">&k3=${k3}</c:if>&money=0&sales=0</c:if>" <c:if test="${money == 1}">class="on"</c:if> id="money">价格</a>
            <a href="<c:if test="${sales == 0}">${APP_PATH }/product?pn=1<c:if test="${not empty KeyWord}">&KeyWord=${KeyWord}</c:if><c:if test="${not empty k1}">&k1=${k1}</c:if><c:if test="${not empty k2}">&k2=${k2}</c:if><c:if test="${not empty k3}">&k3=${k3}</c:if>&money=0&sales=1</c:if><c:if test="${sales != 0}">${APP_PATH }/product?pn=1<c:if test="${not empty KeyWord}">&KeyWord=${KeyWord}</c:if><c:if test="${not empty k1}">&k1=${k1}</c:if><c:if test="${not empty k2}">&k2=${k2}</c:if><c:if test="${not empty k3}">&k3=${k3}</c:if>&money=0&sales=0</c:if>" <c:if test="${sales == 1}">class="on"</c:if> id="sales">销量</a>
        </div>
    </div>
    <div class="bodyMain" style="margin-top: 50px;">
        <h3>共搜索到 ${pageInfo.total} 件商品</h3>
        <ul class="carousel" id="carousel-default">
            <c:forEach items="${pageInfo.list}" var="product">
                <li id="slide" style="height: 350px">
                    <div class="pic"><a href="${APP_PATH}/productDetails?id=${product.id}" target="_blank"><img style="width:100%; height:100%;"
                                                                      src="${APP_PATH}${product.imgPath}"/></a></div>
                    <div class="title"><a href="${APP_PATH}/productDetails?id=${product.id}" target="_blank">
                        <h2>${product.name}</h2>
                        <p>${product.info}</p>
                        <span>￥${product.price}</span> </a></div>
                </li>
            </c:forEach>
        </ul>
        <div>
            </c:if>
            <c:if test="${empty pageInfo.list}">
                <div class="bodyMain">
                    很抱歉，没有找到任何产品！
                </div>
            </c:if>
            <nav aria-label="Page navigation" style="float: right">
                <ul class="pagination">
                    <c:if test="${pageInfo.pageNum != 1 }">
                        <li>
                            <a href="${APP_PATH }/product?pn=1<c:if test="${not empty KeyWord}">&KeyWord=${KeyWord}</c:if><c:if test="${not empty k1}">&k1=${k1}</c:if><c:if test="${not empty k2}">&k2=${k2}</c:if><c:if test="${not empty k3}">&k3=${k3}</c:if><c:if test="${not empty money}">&money=${money}</c:if><c:if test="${not empty sales}">&sales=${sales}</c:if>">首页</a>
                        </li>
                        <li><a
                                href="${APP_PATH }/product?pn=${pageInfo.pageNum -1}<c:if test="${not empty KeyWord}">&KeyWord=${KeyWord}</c:if><c:if test="${not empty k1}">&k1=${k1}</c:if><c:if test="${not empty k2}">&k2=${k2}</c:if><c:if test="${not empty k3}">&k3=${k3}</c:if><c:if test="${not empty money}">&money=${money}</c:if><c:if test="${not empty sales}">&sales=${sales}</c:if>"
                                aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
                        </a></li>
                    </c:if>
                    <c:forEach items="${pageInfo.navigatepageNums }"
                               var="pageNum">
                        <c:if test="${pageNum ==pageInfo.pageNum}">
                            <li class="active"><a href="#">${pageNum }</a></li>
                        </c:if>
                        <c:if test="${pageNum !=pageInfo.pageNum}">
                            <li>
                                <a href="${APP_PATH }/product?pn=${pageNum }<c:if test="${not empty KeyWord}">&KeyWord=${KeyWord}</c:if><c:if test="${not empty k1}">&k1=${k1}</c:if><c:if test="${not empty k2}">&k2=${k2}</c:if><c:if test="${not empty k3}">&k3=${k3}</c:if><c:if test="${not empty money}">&money=${money}</c:if><c:if test="${not empty sales}">&sales=${sales}</c:if>">${pageNum }</a>
                            </li>
                        </c:if>
                    </c:forEach>
                    <c:if test="${pageInfo.pageNum !=pageInfo.pages}">
                        <li><a
                                href="${APP_PATH }/product?pn=${pageInfo.pageNum + 1}<c:if test="${not empty KeyWord}">&KeyWord=${KeyWord}</c:if><c:if test="${not empty k1}">&k1=${k1}</c:if><c:if test="${not empty k2}">&k2=${k2}</c:if><c:if test="${not empty k3}">&k3=${k3}</c:if><c:if test="${not empty money}">&money=${money}</c:if><c:if test="${not empty sales}">&sales=${sales}</c:if>"
                                aria-label="Next"> <span aria-hidden="true">&raquo;</span>
                        </a></li>
                        <li>
                            <a href="${APP_PATH }/product?pn=${pageInfo.pages }<c:if test="${not empty KeyWord}">&KeyWord=${KeyWord}</c:if><c:if test="${not empty k1}">&k1=${k1}</c:if><c:if test="${not empty k2}">&k2=${k2}</c:if><c:if test="${not empty k3}">&k3=${k3}</c:if><c:if test="${not empty money}">&money=${money}</c:if><c:if test="${not empty sales}">&sales=${sales}</c:if>">末页</a>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </div>
        <div class="bodyMain"><a href="#" class="DiyAd"> <img src="${APP_PATH}/static/images/diy_ad.jpg" width="1200"
                                                              height="100" alt=""/> </a>
        </div>
    </div>
    <%--包含脚--%>
    <%@include file="common/foot.jsp" %>
    <script type="text/javascript">
        // $("#money").click(function () {
        //     $(this).toggleClass("on");
        // });
        // $("#sales").click(function () {
        //     var salesIsOn = $(this).is("on");
        //     $(this).toggleClass("on");
        // });
    </script>
</body>
</html>
