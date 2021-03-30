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
        <table id="areaTable" lay-filter="areaTable"></table>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © maomingtechan.com
    </div>
</div>

<div style="display: none" id="updateWin">
    <form class="layui-form layui-form-pane" id="updateForm" lay-filter="updateForm"
          style="width: 80%;margin-left: 10%;">
        <input type="hidden" name="id">
        <div class="layui-form-item" pane>
            <label class="layui-form-label">code</label>
            <div class="layui-input-block">
                <input type="text" name="areaid" lay-verify="required|number" autocomplete="off" placeholder=""
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">名称</label>
            <div class="layui-input-block">
                <input type="text" name="area" lay-verify="required" autocomplete="off" placeholder=""
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" pane>
            <label class="layui-form-label">市code</label>
            <div class="layui-input-block">
                <input type="text" name="cityid" lay-verify="required|number" autocomplete="off" placeholder=""
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" style="margin-top: 70px;">
            <div class="layui-input-block" style="margin-left: 50%;margin-top: -10%;">
                <button class="layui-btn" lay-submit lay-filter="doSubmit">修改</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<div style="display: none" id="addWin">
    <form class="layui-form layui-form-pane" id="addForm" lay-filter="addForm"
          style="width: 80%;margin-left: 10%;">
        <div class="layui-form-item" pane>
            <label class="layui-form-label">code</label>
            <div class="layui-input-block">
                <input type="text" name="areaid" lay-verify="required|number" autocomplete="off" placeholder=""
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">名称</label>
            <div class="layui-input-block">
                <input type="text" name="area" lay-verify="required" autocomplete="off" placeholder=""
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" pane>
            <label class="layui-form-label">市code</label>
            <div class="layui-input-block">
                <input type="text" name="cityid" lay-verify="required|number" autocomplete="off" placeholder=""
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" style="margin-top: 70px;">
            <div class="layui-input-block" style="margin-left: 50%;margin-top: -10%;">
                <button class="layui-btn" lay-submit lay-filter="doAddSubmit">添加</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
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
        var windowAddIndex;
        var upload = layui.upload;
        $("#area").addClass("layui-this");
        $("#addNav").addClass("layui-nav-itemed");

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
            elem: '#areaTable'
            , url: '${APP_PATH}/areaTable' //数据接口
            , height: 550
            , page: true //开启分页
            , toolbar: '#toolBar'
            , cols: [[ //表头
                {field: 'id', title: 'ID', sort: true}
                , {field: 'areaid', title: 'code'}
                , {field: 'area', title: '名称'}
                , {field: 'cityid', title: '市code'}
                , {fixed: 'right', title: '操作', toolbar: '#barOption', width: 180}
            ]]
        });

        //监听行工具事件
        table.on('tool(areaTable)', function (obj) {
            var data = obj.data;
            console.log(obj);
            if (obj.event === 'del') {
            } else if (obj.event === 'edit') {
                updateWindow(data);
            }
        });

        //打开修改窗口
        function updateWindow(data) {
            console.log(data);
            windowIndex = layer.open({
                type: 1,
                title: "修改",
                area: ['600px', '300px'],
                content: $("#updateWin"),
                success: function () {
                    form.val("updateForm", data);
                }
            })
        }

        function addWindow() {
            windowAddIndex = layer.open({
                type: 1,
                title: "添加",
                area: ['600px', '300px'],
                content: $("#addWin"),
                success: function () {
                }
            })
        }

        //修改表单的提交
        form.on('submit(doSubmit)', function (data) {
            $.post("${APP_PATH}/updateArea", data.field, function (result) {
                if (result.code == 100) {
                    layer.alert(result.msg, {icon: 1});
                    //关闭窗口
                    layer.close(windowIndex);
                    //刷新表格
                    tableIns.reload();
                } else {
                    layer.alert(result.msg, {icon: 2});
                }
            }, "json");
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });
        //添加表单的提交
        form.on('submit(doAddSubmit)', function (data) {
            $.post("${APP_PATH}/addArea", data.field, function (result) {
                if (result.code == 100) {
                    layer.alert(result.msg, {icon: 1});
                    //关闭窗口
                    layer.close(windowAddIndex);
                    //刷新表格
                    tableIns.reload();
                } else {
                    layer.alert(result.msg, {icon: 2});
                }
            }, "json");
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });


        //搜索
        var searchReload = $('#searchReload');
        $(document).on('click', '#searchBtn', function () {
            //执行重载
            tableIns.reload({
                url: '${APP_PATH}/areaTableSearch'//后台做模糊搜索接口路径
                , method: 'get'
                , where: {
                    name: searchReload.val()
                },
                page: {
                    curr: 1
                }
            })
        });

        $(document).on('click', '#addBtn', function () {
            addWindow();

        });


    });

</script>

<script type="text/html" id="toolBar">
    搜索名称：
    <div class="layui-inline">
        <input type="text" class="layui-input" name="name" id="searchReload" autocomplete="off">
    </div>
    <button type="button" class="layui-btn" id="searchBtn" data-type="reload">搜索</button>
    <button type="button" class="layui-btn" id="addBtn">添加</button>

</script>

<script type="text/html" id="imgtmp">
    <img src="${APP_PATH}{{d.imgPath}}" height="500px;" width="500px;">
</script>
<script type="text/html" id="imgtmp1">
    <img src="${APP_PATH}{{d.detailsImgPath}}" height="800px;" width="500px;">
</script>
<script type="text/html" id="barOption">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="edit">编辑</a>
</script>
</body>
</html>

