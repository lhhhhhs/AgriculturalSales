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
        <table id="replyTable" lay-filter="replyTable"></table>
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
        $("#reply").addClass("layui-this");
        $("#evaluationNav").addClass("layui-nav-itemed");


        //table实例
        var tableIns = table.render({
            elem: '#replyTable'
            , url: '${APP_PATH}/replyTable' //数据接口
            , height: 550
            , page: true //开启分页
            , toolbar: '#toolBar'
            , cols: [[ //表头
                {field: 'id', title: 'ID', sort: true,width: 80}
                , {field: 'evaluationText', title: '内容'}
                ,{field: 'createTime', title: '创建时间',width: 130,sort: true, minWidth: 100,templet:function (d) {
                        return dateToStr(d.createTime);
                    }}
                , {field: 'oid', title: '订单编号'}
                , {field: 'uName', title: '用户名'}
                , {field: 'pid', title: '商品id'}
                , {field: 'eid', title: '评论id'}
                , {fixed: 'right', title: '操作', toolbar: '#barOption', width: 180}
            ]]
        });
        //搜索
        var searchReload = $('#searchReload');
        $(document).on('click', '#searchBtn', function () {
            //执行重载
            tableIns.reload({
                url: '${APP_PATH}/replyTableSearch'//后台做模糊搜索接口路径
                , method: 'get'
                , where: {
                    str: searchReload.val()
                },
                page: {
                    curr: 1
                }
            })
        });

        table.on('tool(replyTable)', function (obj) {
            var data = obj.data;
            console.log(obj);
            if (obj.event === 'del') {
                layer.confirm('真的删除该回复吗', function (index) {
                    $.post("${APP_PATH}/delReply", {id:data.id}, function (result) {
                        console.log(result);
                        if (result.code == 100) {
                            layer.alert(result.msg, {icon: 1});
                            //刷新表格
                            tableIns.reload();
                        } else {
                            layer.alert(result.msg, {icon: 2});
                        }
                    }, "json");
                });
            }
        });


    });

    function dateToStr(date) {
        console.log(date);
        var y = date.year;
        var M = date.monthValue;
        var d = date.dayOfMonth;
        var str = y + "-" + M + "-" + d ;
        console.log(str);
        return str;
    }


</script>

<script type="text/html" id="toolBar">
    搜索关键字：
    <div class="layui-inline">
        <input type="text" class="layui-input" name="name" id="searchReload" autocomplete="off">
    </div>
    <button type="button" class="layui-btn" id="searchBtn" data-type="reload">搜索</button>
</script>

<script type="text/html" id="barOption">
    <a class="layui-btn layui-btn-xs" lay-event="del">删除</a>
</script>
</body>
</html>

