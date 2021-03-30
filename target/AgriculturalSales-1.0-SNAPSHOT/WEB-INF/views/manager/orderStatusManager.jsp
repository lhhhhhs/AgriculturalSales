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
        <table id="orderTable" lay-filter="orderTable"></table>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © maomingtechan.com
    </div>
</div>

<div style="display: none" id="addWin">
    <div class="layui-row" style="margin-left: 20%;margin-top: 10px;">
        <div class="layui-col-md12">收件人：<span id="name"></span></div>
    </div>
    <div class="layui-row" style="margin-left: 20%;margin-top: 10px;">
        <div class="layui-col-md12">电话：<span id="phone"></span></div>
    </div>
    <div class="layui-row" style="margin-left: 20%;margin-top: 10%;">
        <div class="layui-col-md3">省：<span id="province"></span></div>
        <div class="layui-col-md3">市：<span id="city"></span></div>
        <div class="layui-col-md3">区/县：<span id="area"></span></div>
    </div>
    <div class="layui-row" style="margin-left: 20%;margin-top: 10px;">
        <div class="layui-col-md12">详细地址：<span id="details"></span></div>
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
        $("#orderStatusManager").addClass("layui-this");
        $("#orderNav").addClass("layui-nav-itemed");


        //table实例
        var tableIns = table.render({
            elem: '#orderTable'
            , url: '${APP_PATH}/orderTable' //数据接口
            , height: 550
            , page: true //开启分页
            , toolbar: '#toolBar'
            , cols: [[ //表头
                {field: 'id', title: 'ID', sort: true}
                ,{field: 'creatTime', title: '创建时间',sort: true, minWidth: 100,templet:function (d) {
                        return dateToStr(d.creatTime);
                    }}
                , {field: 'totalPrice', sort: true, title: '总价'}
                , {
                    field: 'status', title: '当前状态', templet: function (item) {
                        if (item.status == '1') {
                            return "未付款";
                        } else if (item.status == '2') {
                            return "已付款，代发货";
                        } else if (item.status == '3') {
                            return "已发货";
                        } else if (item.status == '4') {
                            return "待评价";
                        } else if (item.status == '5') {
                            return "已完成";
                        } else {
                            return "";
                        }
                    }
                }
                , {field: 'uid', title: '用户id'}
                , {fixed: 'right', title: '操作', toolbar: '#barOption', width: 180}
            ]]
        });

        //监听行工具事件
        table.on('tool(orderTable)', function (obj) {
            var data = obj.data;
            console.log(obj);
            if (obj.event === 'send') {
                layer.confirm('真的发货吗', function (index) {
                    $.post("${APP_PATH}/delivery", {orderId:data.id}, function (result) {
                        console.log(result);
                        if (result.code == 100) {
                            layer.alert(result.msg, {icon: 1});
                            //刷新表格
                            tableIns.reload();
                        } else {
                            layer.alert(result.extend.msg, {icon: 2});
                        }
                    }, "json");
                });
            } else if (obj.event === 'add') {
                addWindow(data);
            }
        });


        //打开地址窗口
        function addWindow(data) {
            console.log(data);
            windowAddIndex = layer.open({
                type: 1,
                title: "地址",
                area: ['480px', '300px'],
                content: $("#addWin"),
                success: function () {
                    $.post("${APP_PATH}/orderAdd", {addressId: data.addressId}, function (result) {
                        var add = result.extend.add;
                        // console.log(add);
                        // console.log(result);
                        $("#name").html(add.consignee);
                        $("#phone").html(add.phone);
                        $("#province").html(add.province);
                        $("#city").html(add.city);
                        $("#area").html(add.area);
                        $("#details").html(add.detailed);
                    }, "json");
                }
            })
        }

        //搜索
        var searchReload = $('#searchReload');
        $(document).on('click', '#searchBtn', function() {
            //执行重载
            tableIns.reload({
                url: '${APP_PATH}/orderSearch'//后台做模糊搜索接口路径
                , method: 'get'
                ,where:{
                    id:searchReload.val()
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
        var h = date.hour;
        var m = date.minute;
        var s = date.second;
        var str = y + "-" + M + "-" + d + " " + h + ":" + m + ":" + s;
        console.log(str);
        return str;
    }


</script>

<script type="text/html" id="toolBar">
    搜索订单号：
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
<script type="text/html" id="barOption">
    <a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="add">地址</a>
    <a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="send">发货</a>
</script>
</body>
</html>

