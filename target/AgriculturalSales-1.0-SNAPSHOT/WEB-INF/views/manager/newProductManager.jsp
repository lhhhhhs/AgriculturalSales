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
        <table id="newProductTable" lay-filter="newProductTable"></table>
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
        var windowUpdateIndex;
        var windowDelIndex;
        var upload = layui.upload;
        $("#managerNewProduct").addClass("layui-this");
        $("#productNav").addClass("layui-nav-itemed");

        //自定义验证规则
        form.verify({
            img1: function (value) {
                if (value.length == 0) {
                    return '请上传商品图片';
                }
            },
            img2: function (value) {
                if (value.length == 0) {
                    return '请上传详情图片';
                }
            }
            , content: function (value) {
                layedit.sync(editIndex);
            }
        });



        //table实例
        var tableIns = table.render({
            elem: '#newProductTable'
            , url: '${APP_PATH}/newProductTable' //数据接口
            , height: 550
            , page: true //开启分页
            ,toolbar:'#toolBar'
            , cols: [[ //表头
                {field: 'id', title: 'ID', sort: true}
                ,{field: 'pid', title: 'pid', width: 80, sort: true}
                ,{field: 'createTime', title: '上市时间',sort: true, minWidth: 100,templet:function (d) {
                        return dateToStr(d.createTime);
                    }}
                , {field: 'name', title: '名称'}
                , {field: 'imgPath', title: '商品图片', width: 80, align: "center", templet: "#imgtmp"}
                , {field: 'price', sort: true, title: '价格'}
                , {field: 'sales', sort: true, title: '销量'}
                , {field: 'stock', sort: true, title: '库存'}
                , {field: 'info', title: '信息', width: 200}
                , {field: 'detailsImgPath', title: '详情图片', width: 120, align: "center", templet: "#imgtmp1"}
                , {fixed: 'right', title: '操作', toolbar: '#barOption', width: 180}
            ]]
        });

        //监听行工具事件
        table.on('tool(newProductTable)', function (obj) {
            var data = obj.data;
            console.log(obj);
            if (obj.event === 'del') {
                layer.confirm('真的删除该商品吗', function (index) {
                    $.post("${APP_PATH}/delNewProduct", {id:data.id}, function (result) {
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



        //搜索
        var searchReload = $('#searchReload');
        $(document).on('click', '#searchBtn', function() {
            //执行重载
            tableIns.reload({
                url: '${APP_PATH}/newProductTableSearch'//后台做模糊搜索接口路径
                , method: 'get'
                ,where:{
                    name:searchReload.val()
                },
                page:{
                    curr:1
                }
            })
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
    搜索商品名称：
    <div class="layui-inline">
        <input type="text" class="layui-input" name="name" id="searchReload" autocomplete="off">
    </div>
    <button type="button" class="layui-btn" id="searchBtn" data-type="reload">搜索</button>
</script>

<script type="text/html" id="imgtmp">
    <img src="${APP_PATH}{{d.imgPath}}" height="500px;" width="500px;">
</script>
<script type="text/html" id="imgtmp1">
    <img src="${APP_PATH}{{d.detailsImgPath}}" height="800px;" width="500px;">
</script>
<script type="text/html" id="barOption">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
</body>
</html>

