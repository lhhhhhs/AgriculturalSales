<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">茂名特产后台</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a id="admin" href="${APP_PATH}/goAdmin">控制台</a></li>
            <li class="layui-nav-item"><a href="">客服</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;" id="other">其它</a>
                <dl class="layui-nav-child">
                    <dd><a href="${APP_PATH}/updateSalesRally">更新商品销量统计图</a></dd>
                    <dd><a href="${APP_PATH}/edit">基础设置</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">

            <li class="layui-nav-item">${sessionScope.user.adminName}</li>
            <li class="layui-nav-item"><a href="${APP_PATH}/exitManager">退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item" id="orderNav">
                    <a class="" href="javascript:;">订单管理</a>
                    <dl class="layui-nav-child">
                        <dd id="orderInfoManager"><a href="${APP_PATH}/orderInfoManager">订单信息查询</a></dd>
                        <dd id="orderStatusManager"><a href="${APP_PATH}/orderStatusManager">订单状态管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item " id="productNav">
                    <a href="javascript:;">商品管理</a>
                    <dl class="layui-nav-child">
                        <dd id="addProduct"><a href="${APP_PATH}/createProduct">创建商品</a></dd>
                        <dd id="managerProduct"><a  href="${APP_PATH}/productManager">商品管理</a></dd>
                        <dd id="managerNewProduct"><a href="${APP_PATH}/newProductManager">管理新品表</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item" id="userNav">
                    <a href="javascript:;">用户管理</a>
                    <dl class="layui-nav-child">
                        <dd id="user"><a href="${APP_PATH}/userManager">用户信息管理</a></dd>
                        <dd id="admin1"><a href="${APP_PATH}/adminManager">管理员</a></dd>
                    </dl>
                </li>

                <li class="layui-nav-item" id="evaluationNav">
                    <a href="javascript:;">评论管理</a>
                    <dl class="layui-nav-child">
                        <dd id="evaluation"><a href="${APP_PATH}/evaluationManager">评论管理</a></dd>
                        <dd id="reply"><a href="${APP_PATH}/replyManager">评论回复管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item" id="addNav">
                    <a href="javascript:;">地址信息管理</a>
                    <dl class="layui-nav-child">
                        <dd id="province"><a href="${APP_PATH}/provinceManager">省</a></dd>
                        <dd id="city"><a href="${APP_PATH}/cityManager">市</a></dd>
                        <dd id="area"><a href="${APP_PATH}/areaManager">区/县</a></dd>
                    </dl>
                </li>

                <li class="layui-nav-item" id="kindNav">
                    <a href="javascript:;">商品种类管理</a>
                    <dl class="layui-nav-child">
                        <dd id="kind1"><a href="${APP_PATH}/kind1Manager">类别1</a></dd>
                        <dd id="kind2"><a href="${APP_PATH}/kind2Manager">类别2</a></dd>
                        <dd id="kind3"><a href="${APP_PATH}/kind3Manager">类别3</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>