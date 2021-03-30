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
        <table id="kind2Table" lay-filter="kind2Table"></table>
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
            <label class="layui-form-label">名称</label>
            <div class="layui-input-block">
                <input type="text" name="name" lay-verify="required" autocomplete="off" placeholder="请输入名称"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline layui-col-md3" style="margin-left: 20px;">
                <label class="layui-form-label">类别1</label>
                <div class="layui-input-inline" style="width: 95%">
                    <select style="width: 100px;"  name="k1" id="cate1" lay-filter="cate1"   lay-verify="required" lay-search="">
                        <option value="">请选择类别1</option>
                        <c:forEach items="${k1List}" var="kind1">
                            <option value="${kind1.id}">${kind1.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">信息</label>
            <div class="layui-input-block">
                <input type="text" name="info" lay-verify="" autocomplete="off" placeholder=""
                       class="layui-input">
            </div>
        </div>
        <div class="layui-input-block" style="margin-left: 50%;margin-top: 10%;">
            <button class="layui-btn" lay-submit lay-filter="doSubmit">修改</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </form>
</div>
<div style="display: none" id="addWin">
    <form class="layui-form layui-form-pane" id="" lay-filter=""
          style="width: 80%;margin-left: 10%;">
        <div class="layui-form-item" pane>
            <label class="layui-form-label">名称</label>
            <div class="layui-input-block">
                <input type="text" name="name" lay-verify="required" autocomplete="off" placeholder="请输入名称"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline layui-col-md3" style="margin-left: 20px;">
                <label class="layui-form-label">类别1</label>
                <div class="layui-input-inline" style="width: 95%">
                    <select style="width: 100px;"  name="k1" id="cate11" lay-filter="cate1"   lay-verify="required" lay-search="">
                        <option value="">请选择类别1</option>
                        <c:forEach items="${k1List}" var="kind1">
                            <option value="${kind1.id}">${kind1.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">信息</label>
            <div class="layui-input-block">
                <input type="text" name="info" lay-verify="" autocomplete="off" placeholder=""
                       class="layui-input">
            </div>
        </div>
        <div class="layui-input-block" style="margin-left: 50%;margin-top: 10%;">
            <button class="layui-btn" lay-submit lay-filter="doAddSubmit">添加</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
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
        var windowIndex;
        var windowAddIndex;
        var upload = layui.upload;
        $("#kind2").addClass("layui-this");
        $("#kindNav").addClass("layui-nav-itemed");

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
            elem: '#kind2Table'
            , url: '${APP_PATH}/kind2Table' //数据接口
            , height: 550
            , page: true //开启分页
            ,toolbar:'#toolBar'
            , cols: [[ //表头
                {field: 'id', title: 'ID', sort: true}
                , {field: 'name', title: '名称'}
                , {field: 'k1', title: '种类1Id'}
                , {field: 'info', title: '信息', width: 200}
                , {fixed: 'right', title: '操作', toolbar: '#barOption', width: 180}
            ]]
        });

        //监听行工具事件
        table.on('tool(kind2Table)', function (obj) {
            var data = obj.data;
            console.log(obj);
            if (obj.event === 'del') {
                layer.confirm('真的删除该商品吗', function (index) {
                    $.post("${APP_PATH}/delKind2", data, function (result) {
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
            } else if (obj.event === 'edit') {
                updateWindow(data);
            }
        });

        //打开修改窗口
        function updateWindow(data) {
            console.log(data);
            windowIndex = layer.open({
                type: 1,
                title: "修改商品",
                area: ['600px', '400px'],
                content: $("#updateWin"),
                success: function () {
                    $("#Img11").attr("src","${APP_PATH}"+data.showImgPath);
                    form.val("updateForm", data);
                }
            })
        }
        //修改表单的提交
        form.on('submit(doSubmit)', function(data){
            $.post("${APP_PATH}/updateKind2",data.field,function (result) {
                if(result.code==100){
                    layer.alert(result.msg,{icon:1});
                    //关闭窗口
                    layer.close(windowIndex);
                    //刷新表格
                    tableIns.reload();
                }else{
                    layer.alert(result.msg,{icon:2});
                }
            },"json");
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });
        //添加表单
        form.on('submit(doAddSubmit)', function(data){
            $.post("${APP_PATH}/addKind2",data.field,function (result) {
                if(result.code==100){
                    layer.alert(result.msg,{icon:1});
                    //关闭窗口
                    layer.close(windowAddIndex);
                    //刷新表格
                    tableIns.reload();
                }else{
                    layer.alert(result.msg,{icon:2});
                }
            },"json");
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });


        //搜索
        var searchReload = $('#searchReload');
        $(document).on('click', '#searchBtn', function() {
            //执行重载
            tableIns.reload({
                url: '${APP_PATH}/kind2TableSearch'//后台做模糊搜索接口路径
                , method: 'get'
                ,where:{
                    name:searchReload.val()
                },
                page:{
                    curr:1
                }
            })
        });
        //添加
        $(document).on('click', '#addBtn', function() {
            addWindow();
        });

        function addWindow() {
            windowAddIndex = layer.open({
                type: 1,
                title: "添加",
                area: ['600px', '400px'],
                content: $("#addWin"),
                success: function () {
                }
            })
        }


        //搜索三级联动
        form.on("select(cate6)", function () {
            var cate6 = $("#cate6").val();
            //执行重载
            tableIns.reload({
                url: '${APP_PATH}/productKind3Search'//后台做搜索接口路径
                , method: 'get'
                ,where:{
                    k3:cate6
                },
                page:{
                    curr:1
                }
            })
        });


    });

</script>

<script type="text/html" id="toolBar">
    搜索商品名称：
    <div class="layui-inline">
        <input type="text" class="layui-input" name="name" id="searchReload" autocomplete="off">
    </div>
    <button type="button" class="layui-btn" id="searchBtn" data-type="reload">搜索</button>
    <button type="button" class="layui-btn" id="addBtn" data-type="reload">添加</button>
</script>

<script type="text/html" id="imgtmp">
    <img src="${APP_PATH}{{d.showImgPath}}" height="500px;" width="500px;">
</script>
<script type="text/html" id="barOption">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
</body>
</html>

