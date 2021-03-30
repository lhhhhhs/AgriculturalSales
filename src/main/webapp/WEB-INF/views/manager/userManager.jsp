<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${APP_PATH}/static/js/jquery-3.5.0.js"></script>
    <script type="text/javascript" src="${APP_PATH}/static/layui/layui.js"></script>
    <script type="text/javascript" src="${APP_PATH}/static/js/echarts.js"></script>
    <link href="${APP_PATH}/static/layui/css/layui.css" rel="stylesheet" type="text/css"/>
</head>
<div>
    <%--包含导航--%>
    <%@include file="../common/managerNav.jsp" %>

    <div class="layui-body" style="background-color: #F2F2F2">
        <!-- 内容主体区域 -->
        <table id="userTable" lay-filter="userTable"></table>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © maomingtechan.com
    </div>
</div>





<script>
    layui.use(['form', 'element', 'layer', 'carousel', 'jquery', 'table', 'laydate', 'layedit', 'upload'], function () {
        var element = layui.element;
        var layer = layui.layer;
        var carousel = layui.carousel;
        var $ = layui.jquery;
        var table = layui.table;
        var form = layui.form;
        var laydate = layui.laydate;
        var layedit = layui.layedit;
        var windowAddIndex;
        var windowItemIndex;
        var windowUserIndex;
        var upload = layui.upload;
        $("#user").addClass("layui-this");
        $("#userNav").addClass("layui-nav-itemed");


        //table实例
        var tableIns = table.render({
            elem: '#userTable'
            , url: '${APP_PATH}/userTable' //数据接口
            , height: 550
            , page: true //开启分页
            , toolbar: '#toolBar'
            , cols: [[ //表头
                {field: 'id', title: 'ID', sort: true}
                ,{field: 'phone', title: '电话', sort: true}
            ]]
        });
        //搜索
        var searchReload = $('#searchReload');
        $(document).on('click', '#searchBtn', function() {
            //执行重载
            tableIns.reload({
                url: '${APP_PATH}/userSearch'//后台做模糊搜索接口路径
                , method: 'get'
                ,where:{
                    phone:searchReload.val()
                },
                page:{
                    curr:1
                }
            })
        });

    });



</script>

<script type="text/html" id="toolBar">
    搜索电话号码：
    <div class="layui-inline">
        <input type="text" class="layui-input" name="name" id="searchReload" autocomplete="off">
    </div>
    <button type="button" class="layui-btn" id="searchBtn" data-type="reload">搜索</button>
</script>

<script type="text/html" id="imgtmp">
    <img src="${APP_PATH}{{d.imgPath}}" height="500px;" width="500px;">
</script>
<script type="text/html" id="time">
    <div>{{d.creatTime}}</div>
</script>
</body>
</html>

