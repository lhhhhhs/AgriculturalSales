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
</head>

<body>



<%--包含头--%>
    <%@include file="common/head.jsp"%>
<%--包含搜索--%>
    <%@include file="common/search.jsp"%>

<div class="menu">
    <div class="all-sort">
        <h2><a href="">全部商品分类</a></h2>
    </div>
</div>
<!--导航 End-->
<div class="nav_subnav">
    <div class="wrap">
        <div class="all-sort-list">
            <div class="item">
                <h3><span></span><a href="">品牌折扣</a></h3>
                <div class="item-list clearfix">
                    <div class="subitem">
                        <dl >
                            <dt><a href="#">数码电子</a></dt>
                            <dd><a href="#">U盘</a><a href="#">移动电源</a><a href="#">音箱</a><a href="#">鼠标</a><a href="#">键盘</a><a href="#">数码相框</a><a href="#">读卡器</a> <a href="#">MP3播放器</a><a href="#">车载充电器</a><a href="#">耳机</a><a href="#">转换插座</a><a href="#">USB集线器</a><a href="#">USB风扇</a><a href="#">万年历</a> <a href="#">笔筒</a><a href="#">时计产品</a><a href="#">广告手表</a><a href="#">其他</a> </dd>
                        </dl>
                        <dl class="">
                            <dt><a href="#">小家电</a></dt>
                            <dd><a href="#">电热水壶</a><a href="#">酸奶机</a><a href="#">豆浆机</a><a href="#">咖啡机</a><a href="#">手电筒</a><a href="#">榨汁搅拌机</a><a href="#">蒸蛋器</a> <a href="#">空气净化器</a><a href="#">空气加湿器</a><a href="#">灯具</a><a href="#">剃须刀</a><a href="#">电吹风</a><a href="#">吸尘器</a><a href="#">电烤箱</a> <a href="#">电饼铛</a><a href="#">电磁炉</a><a href="#">其他</a> </dd>
                        </dl>
                        <dl class="">
                            <dt><a href="#">品牌数码</a></dt>
                            <dd><a href="#">品胜</a><a href="#">小米</a><a href="#">小狗吸尘器</a><a href="#">莱克吸尘</a><a href="#">科 沃斯</a><a href="#">华为WATCH</a><a href="#">苹果WATCH</a> <a href="#">爱国者</a><a href="#">迷你音响</a><a href="#">飞利浦</a><a href="#">罗技</a><a href="#">荣事达</a><a href="#">小鸭</a><a href="#">奔腾电器</a> <a href="#">朗科</a><a href="#">sandisk闪迪</a><a href="#">小熊电器</a><a href="#">幻响</a> </dd>
                        </dl>
                    </div>
                    <div class="cat-right">
                        <ul>
                            <li><a href="#"><img src="images/ad1.jpg" width="256" height="463" alt="品牌折扣广告"/></a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="item">
                <h3><span></span><a href="">家居家纺</a></h3>
                <div class="item-list clearfix">
                    <div class="subitem">
                        <dl class="">
                            <dt><a href="#">家纺</a></dt>
                            <dd><a href="#">毛绒玩具</a><a href="#">太空枕</a><a href="#">空调被</a><a href="#">被子</a><a href="#">枕头</a><a href="#">凉席</a><a href="#">床品件套</a> <a href="#">毛毯</a><a href="#">抱枕</a><a href="#">抱枕被</a><a href="#">U型枕</a><a href="#">野餐垫/毯</a><a href="#">骨头枕</a><a href="#">广告毛巾</a> <a href="#">浴巾</a><a href="#">靠垫</a><a href="#">其他</a> </dd>
                        </dl>
                        <dl class="">
                            <dt><a href="#">家居</a></dt>
                            <dd><a href="#">时钟/挂钟</a><a href="#">台灯</a><a href="#">收纳盒</a><a href="#">雨伞</a><a href="#">收纳凳</a><a href="#">购物篮</a><a href="#">化妆包</a> <a href="#">沐浴套装</a><a href="#">烟具/烟具套装</a><a href="#">暖手宝</a><a href="#">秤</a><a href="#">温度计/湿度计</a><a href="#">化妆镜</a><a href="#">其他</a> </dd>
                        </dl>
                        <dl class="">
                            <dt><a href="#">餐具厨具</a></dt>
                            <dd><a href="#">锅具/套装</a><a href="#">便当盒/套装</a><a href="#">便当包</a><a href="#">保鲜盒/套装</a><a href="#">碗（套装）/盘子（套装）</a><a href="#">餐具/套装</a><a href="#">厨房工具/套装</a> <a href="#">调味罐/盒</a><a href="#">密封罐油壶/套装</a><a href="#">果蔬蓝/滴水筛</a><a href="#">其他</a> </dd>
                        </dl>
                        <dl class="">
                            <dt><a href="#">品牌家居</a></dt>
                            <dd><a href="#">洁丽雅</a><a href="#">苏泊尔</a><a href="#">乐扣乐扣</a><a href="#">美的</a><a href="#">十八子</a><a href="#">德世朗</a><a href="#">寇可</a><a href="#">其他</a> </dd>
                        </dl>
                    </div>
                    <div class="cat-right">
                        <ul>
                            <li><a href="#"><img src="images/ad1.jpg" width="256" height="463" alt="品牌折扣广告"/></a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="item">
                <h3><span></span><a href="">汽车用品</a></h3>
                <div class="item-list clearfix">
                    <div class="subitem">
                        <dl class="">
                            <dt><a href="#">数码电子</a></dt>
                            <dd><a href="#">U盘</a><a href="#">移动电源</a><a href="#">音箱</a><a href="#">鼠标</a><a href="#">键盘</a><a href="#">数码相框</a><a href="#">读卡器</a> <a href="#">MP3播放器</a><a href="#">车载充电器</a><a href="#">耳机</a><a href="#">转换插座</a><a href="#">USB集线器</a><a href="#">USB风扇</a><a href="#">万年历</a> <a href="#">笔筒</a><a href="#">时计产品</a><a href="#">广告手表</a><a href="#">其他</a> </dd>
                        </dl>
                        <dl class="">
                            <dt><a href="#">小家电</a></dt>
                            <dd><a href="#">电热水壶</a><a href="#">酸奶机</a><a href="#">豆浆机</a><a href="#">咖啡机</a><a href="#">手电筒</a><a href="#">榨汁搅拌机</a><a href="#">蒸蛋器</a> <a href="#">空气净化器</a><a href="#">空气加湿器</a><a href="#">灯具</a><a href="#">剃须刀</a><a href="#">电吹风</a><a href="#">吸尘器</a><a href="#">电烤箱</a> <a href="#">电饼铛</a><a href="#">电磁炉</a><a href="#">其他</a> </dd>
                        </dl>
                        <dl class="">
                            <dt><a href="#">品牌数码</a></dt>
                            <dd><a href="#">品胜</a><a href="#">小米</a><a href="#">小狗吸尘器</a><a href="#">莱克吸尘</a><a href="#">科 沃斯</a><a href="#">华为WATCH</a><a href="#">苹果WATCH</a> <a href="#">爱国者</a><a href="#">迷你音响</a><a href="#">飞利浦</a><a href="#">罗技</a><a href="#">荣事达</a><a href="#">小鸭</a><a href="#">奔腾电器</a> <a href="#">朗科</a><a href="#">sandisk闪迪</a><a href="#">小熊电器</a><a href="#">幻响</a> </dd>
                        </dl>
                    </div>
                    <div class="cat-right">
                        <ul>
                            <li><a href="#"><img src="images/ad1.jpg" width="256" height="463" alt="品牌折扣广告"/></a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="item">
                <h3><span></span><a href="">工艺礼品</a></h3>
                <div class="item-list clearfix">
                    <div class="subitem">
                        <dl class="fore1">
                            <dt><a href="#">数码电子</a></dt>
                            <dd><a href="#">U盘</a><a href="#">移动电源</a><a href="#">音箱</a><a href="#">鼠标</a><a href="#">键盘</a><a href="#">数码相框</a><a href="#">读卡器</a> <a href="#">MP3播放器</a><a href="#">车载充电器</a><a href="#">耳机</a><a href="#">转换插座</a><a href="#">USB集线器</a><a href="#">USB风扇</a><a href="#">万年历</a> <a href="#">笔筒</a><a href="#">时计产品</a><a href="#">广告手表</a><a href="#">其他</a> </dd>
                        </dl>
                        <dl class="fore2">
                            <dt><a href="#">小家电</a></dt>
                            <dd><a href="#">电热水壶</a><a href="#">酸奶机</a><a href="#">豆浆机</a><a href="#">咖啡机</a><a href="#">手电筒</a><a href="#">榨汁搅拌机</a><a href="#">蒸蛋器</a> <a href="#">空气净化器</a><a href="#">空气加湿器</a><a href="#">灯具</a><a href="#">剃须刀</a><a href="#">电吹风</a><a href="#">吸尘器</a><a href="#">电烤箱</a> <a href="#">电饼铛</a><a href="#">电磁炉</a><a href="#">其他</a> </dd>
                        </dl>
                        <dl class="fore3">
                            <dt><a href="#">品牌数码</a></dt>
                            <dd><a href="#">品胜</a><a href="#">小米</a><a href="#">小狗吸尘器</a><a href="#">莱克吸尘</a><a href="#">科 沃斯</a><a href="#">华为WATCH</a><a href="#">苹果WATCH</a> <a href="#">爱国者</a><a href="#">迷你音响</a><a href="#">飞利浦</a><a href="#">罗技</a><a href="#">荣事达</a><a href="#">小鸭</a><a href="#">奔腾电器</a> <a href="#">朗科</a><a href="#">sandisk闪迪</a><a href="#">小熊电器</a><a href="#">幻响</a> </dd>
                        </dl>
                    </div>
                    <div class="cat-right">
                        <ul>
                            <li><a href="#"><img src="images/ad1.jpg" width="256" height="463" alt="品牌折扣广告"/></a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <c:forEach items="${applicationScope.kind}" var="kind">
            <div class="item">
                <h3><span></span><a href="${APP_PATH}/product?k1=${kind.kind1.id}">${kind.kind1.name}</a></h3>
                <div class="item-list clearfix">
                    <div class="subitem">
                        <c:forEach items="${kind.kind2}" var="kind2">
                        <dl class="fore1">
                            <dt><a href="${APP_PATH}/product?k2=${kind2.id}">${kind2.name}</a></dt>
                            <dd><c:forEach items="${kind.map[kind2.id]}" var="kind3"><a href="${APP_PATH}/product?k3=${kind3.id}">${kind3.name}</a></c:forEach>  </dd>
                        </dl>
                        </c:forEach>
                    </div>
                    <div class="cat-right">
                        <ul>
                            <li><a href="${APP_PATH}/product?k1=${kind.kind1.id}"><img src="${APP_PATH}${kind.kind1.showImgPath}" width="256" height="463" alt="品牌折扣广告"/></a></li>
                        </ul>
                    </div>
                </div>
            </div>
            </c:forEach>
        </div>
    </div>
</div>
<!--所有分类 End-->
<div id="bn"> <span class="tu">
    <a href="#" class="lianjie">
    <img src="${APP_PATH}${applicationScope.img1}" height="490" /></a> <a href="#" class="lianjie" style="display:none">
    <img src="${APP_PATH}${applicationScope.img2}" height="490" /></a> <a href="#" class="lianjie" style="display:none">
    <img src="${APP_PATH}${applicationScope.img3}" height="490" /></a><a href="#" class="lianjie" style="display:none">
    <img src="${APP_PATH}${applicationScope.img4}" height="490" /></a></span>
    <div id="hao"> <a class="xu">1</a> <a class="xu">2</a> <a class="xu">3</a> <a class="xu">4</a> </div>
    <div class="tiao"></div>
</div>

<div class="bodyMain HotProducts" style="margin-top: 50px;">
    <div class="HotTitle">
        <h2>热销产品 /<span> hot-sale product</span></h2>
    </div>
    <ul class="carousel" id="carousel-default">
        <c:forEach items="${applicationScope.productsSales}" var="product">
        <li id="slide">
            <div class="pic"><a href="${APP_PATH}/productDetails?id=${product.id}" target="_blank"><img src="${APP_PATH}${product.imgPath}" width="100%" height="100%"/></a></div>
            <div class="title"><a href="${APP_PATH}/productDetails?id=${product.id}" target="_blank">
                <h2>${product.name}</h2>
                <p>${product.info}</p>
                <span>${product.price}</span> </a></div>
        </li>
        </c:forEach>
    </ul>
<%--    <script type="text/javascript" src="js/purejscarousel.js"></script>--%>
<%--    <script>--%>

<%--        $("#goleft").imgscroll({speed: 40,amount: 0,width: 1,dir: "left"});--%>
<%--    </script>--%>

</div>
<!--New listing product start-->
<div class="bodyMain NewsProducts">
    <div class="HotTitle">
        <h2>新品上市 /<span> New listing</span></h2>
    </div>
    <ul class="carousel" id="carouselDefault">
        <c:forEach items="${applicationScope.productsNew}" var="product">
            <li id="slide">
                <div class="pic"><a href="${APP_PATH}/productDetails?id=${product.pid}" target="_blank"><img src="${APP_PATH}${product.imgPath}" width="100%" height="100%"/></a></div>
                <div class="title"><a href="${APP_PATH}/productDetails?id=${product.pid}" target="_blank">
                    <h2>${product.name}</h2>
                    <p>${product.info}</p>
                    <span>${product.price}</span> </a></div>
            </li>
        </c:forEach>
    </ul>

<%--    <script>--%>
<%--        var carouselDefault = new PureJSCarousel({carousel:'#carouselDefault',slide:'#slide'})--%>
<%--    </script>--%>


</div>

<div class="HomeProducts">
    <%
        int i = 1;
    %>
    <c:forEach items="${applicationScope.product}" var="synthetic">
        <div class="bodyMain">
            <div class="pro_title"> <span><%=i++%>F</span>
                <h2>${synthetic.kind1.name}</h2>
                <ul class="HomeSpan R">
                    <c:forEach items="${synthetic.kind2List}" var="kind2"><a href="${APP_PATH}/product?k2=${kind2.id}">${kind2.name}</a></c:forEach> <a href="${APP_PATH}/product?k1=${synthetic.kind1.id}">进入></a>
                </ul>
            </div>
            <div class="bodyMain proMain"> <a class="pro_ad L"> <img src="${APP_PATH}${synthetic.kind1.showImgPath}" width="240" height="570" alt=""/> </a>
                <div class="ProHomeMain R">
                    <ul class="Prolist">
                        <c:forEach items="${synthetic.productList}" var="product">
                            <li>
                                <div class="Proimg"> <a href="${APP_PATH}/productDetails?id=${product.id}"><img src="${APP_PATH}${product.imgPath}" width="231" height="180" alt=""/></a> </div>
                                <div class="ProTxt"> <a href="${APP_PATH}/productDetails?id=${product.id}">
                                    <h2>${product.name}</h2>
                                    <p>${product.info}</p>
                                    <span>￥${product.price}</span></a></div>
                            </li>
                        </c:forEach>

                    </ul>
                </div>
            </div>
        </div>
    </c:forEach>



    <div class="bodyMain"> <a href="#" class="DiyAd"> <img src="${APP_PATH}/static/images/diy_ad.jpg" width="1200" height="100" alt=""/> </a>

    </div>
</div>


<%--包含脚--%>
<%@include file="common/foot.jsp"%>

<!--right qq start-->
<div class="side" >
    <ul style="margin-top: 100px;">
        <li><a href="${APP_PATH}/chatUser">
            <div class="sidebox"><img src="${APP_PATH}/static/images/side_icon01.png">客服中心</div>
        </a></li>
        <li style="border:none;"><a href="javascript:goTop();" class="sidetop"><img src="${APP_PATH}/static/images/side_icon05.png"></a></li>
    </ul>
</div>
<script type="text/javascript" src="${APP_PATH}/static/js/script.js"></script>

</body>
</html>
