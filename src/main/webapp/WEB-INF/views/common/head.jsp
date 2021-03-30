<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="siteNav">
    <div class="bodyMain">
        <div class="L Welogin"> 您好，欢迎来到<span class="activespan">茂名特产商城</span>！ </div>
        <ul id="JsQuickMemu" class="R">
            <li>
                <c:if test="${empty sessionScope.user}">
                    <a href="goLogin"> 【 登录/注册 】</a>
                </c:if>
                <c:if test="${not empty sessionScope.user}">
                    <a href="">  用户名：${sessionScope.user.phone} </a> <span>|</span>
                    <a href="${APP_PATH}/addressDefault"> 地址管理</a>
                </c:if>
                <span>|</span> </li>
            <li> <a href="${APP_PATH}/order" target="_blank">我的订单</a> </li>
            <li> <a href="${APP_PATH}/cart" target="_blank" class="cart">购物车（${cartCount}）</a> </li>
            <c:if test="${not empty sessionScope.user}">
                <li><span>|</span> <a href="${APP_PATH}/logout" onclick="return confirm('确定退出当前账号?');">注销账号</a> </li>
            </c:if>
        </ul>
    </div>
</div>