<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <script type="text/javascript" src="${APP_PATH}/static/js/jquery-3.5.0.js"></script>
    <script type="text/javascript" src="${APP_PATH}/static/layui/layui.js"></script>
    <script type="text/javascript" src="${APP_PATH}/static/js/echarts.js"></script>
    <link href="${APP_PATH}/static/layui/css/layui.css" rel="stylesheet" type="text/css"/>
</head>
<style>
    #Composition{
        BORDER-RIGHT: #fff 2px inset;
        PADDING-RIGHT: 5px;
        BORDER-TOP: #fff 2px inset;
        PADDING-LEFT: 5px;
        color: #000000;
        BACKGROUND: white;
        PADDING-BOTTOM: 5px;
        OVERFLOW: auto;
        BORDER-LEFT: #fff 2px inset;
        PADDING-TOP: 5px;
        BORDER-BOTTOM: #fff 2px inset;
    }

    .div1{
        width: 80%;height: 100px;margin-top: 10px;
    }
    .div2{
        float: left;width:50px;height: 50px;margin-top: 20px;
    }
    .div3{
        border-radius:10px;word-break: break-all;white-space: normal;width: 70%;height: 90%;border: 1px solid #9eb3bc;float: left;margin-left: 10px;font-size: 20px;
    }
</style>
<body style="background-color: #cae4e7">
<div class="layui-row">
    <div class="layui-col-md8" ID="Composition" style="border: 1px solid #6ab0e7;margin-left: 15%;margin-top: 5%;height: 500px;border-radius:10px">

    </div>
    <div class="layui-form-item" style="margin-right: 0%;margin-left: 10%;margin-top: 500px;width: 65%;">
        <div class="layui-input-block">
            <input type="text" ID="text"    placeholder="请输入" autocomplete="off" class="layui-input">
            <button class="layui-btn" onclick="send()" style="margin-left: 100%;margin-top: -7%">发送</button>
        </div>
    </div>
</div>
<%--<div id="message"></div>--%>
<script type="text/javascript">
    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        websocket = new WebSocket(
            "ws://localhost:8081/AgriculturalSales/websocketTest/admin");
        console.log("link success")
    } else {
        alert('Not support websocket')
    }
    //连接发生错误的回调方法
    websocket.onerror = function() {
        setMessageInnerHTML("<hr>\n" +
            "        <div class=\"div4\"> <span style=\"font-size: 5px;\">连接错误！</span></div>\n" +
            "        <hr>");
    };
    //连接成功建立的回调方法
    websocket.onopen = function(event) {
        setMessageInnerHTML("<hr>\n" +
            "        <div class=\"div4\"> <span style=\"font-size: 5px;\">连接成功！</span></div>\n" +
            "        <hr>");
    }
    console.log("-----")
    //接收到消息的回调方法
    websocket.onmessage = function(event) {
        setMessageInnerHTML("<div class=\"div1\">\n" +
            "            <div class=\"div2\"><img src=\"${APP_PATH}/static/images/icon/yonghu.JPG\" width=\"100%\" height=\"100%\" style=\"border-radius: 40px;\" ></div>\n" +
            "            <div id=\"message\" class=\"div3\">\n" +
            "                "+event.data+"\n" +
            "            </div>\n" +
            "        </div>");
    }
    //连接关闭的回调方法
    websocket.onclose = function() {
        setMessageInnerHTML("close");
    }
    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function() {
        websocket.close();
    }
    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
        document.getElementById('Composition').innerHTML += innerHTML + '<br/>';
    }
    //关闭连接
    function closeWebSocket() {
        websocket.close();
    }
    //发送消息
    function send() {
        var message = document.getElementById('text').value;
        document.getElementById('Composition').innerHTML += "<div class=\"div1\">\n" +
            "            <div class=\"div2\"><img src=\"${APP_PATH}/static/images/icon/kefu.JPG\" width=\"100%\" height=\"100%\" style=\"border-radius: 40px;\" ></div>\n" +
            "            <div id=\"message\" class=\"div3\">\n" +
            "                "+message+"\n" +
            "            </div>\n" +
            "        </div>";
        websocket.send(message);
        $("#text").val("");
    }
</script>
</body>
</html>
