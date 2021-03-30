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
        <div class="layui-row">
            <div class="layui-col-md6" style="margin: 20px;">
                <div class="layui-upload" style="margin: 20px;">
                    <button type="button" class="layui-btn" id="btn1">上传活动图片1</button>
                    <div class="layui-upload-list">
                        <img class="layui-upload-img" width="500px" src="${APP_PATH}${img1}" height="300px;" id="Img1">
                        <p id="Img1Text"></p>
                    </div>
                </div>
            </div>
            <div class="layui-col-md6" style="margin-top: 20px;margin-left: -80px; ">
                <div class="layui-upload" style="margin: 20px;">
                    <button type="button" class="layui-btn" id="btn2">上传活动图片2</button>
                    <div class="layui-upload-list">
                        <img class="layui-upload-img" width="500px" src="${APP_PATH}${img2}" height="300px;" id="Img2">
                        <p id="Img2Text"></p>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-row">
            <div class="layui-col-md6" style="margin: 20px;">
                <div class="layui-upload" style="margin: 20px;">
                    <button type="button" class="layui-btn" id="btn3">上传活动图片3</button>
                    <div class="layui-upload-list">
                        <img class="layui-upload-img" width="500px" src="${APP_PATH}${img3}" height="300px;" id="Img3">
                        <p id="Img3Text"></p>
                    </div>
                </div>
            </div>
            <div class="layui-col-md6" style="margin-top: 20px;margin-left: -80px; ">
                <div class="layui-upload" style="margin: 20px;">
                    <button type="button" class="layui-btn" id="btn4">上传活动图片4</button>
                    <div class="layui-upload-list">
                        <img class="layui-upload-img" width="500px" src="${APP_PATH}${img4}" height="300px;" id="Img4">
                        <p id="Img4Text"></p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © maomingtechan.com
    </div>
</div>
<script>
    layui.use(['form', 'element', 'layer', 'carousel', 'jquery', 'table', 'laydate', 'layedit','upload'], function () {
        var element = layui.element;
        var layer = layui.layer;
        var carousel = layui.carousel;
        var $ = layui.jquery;
        var table = layui.table;
        var form = layui.form;
        var laydate = layui.laydate;
        var layedit = layui.layedit;
        var windowUpdateIndex;
        var upload = layui.upload;
        $("#other").addClass("layui-this");

        //图片上传
        var uploadInst = upload.render({
            elem: '#btn1'
            ,url: '${APP_PATH}/editImg1' //改成您自己的上传接口
            ,before: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#Img1').attr('src', result); //图片链接（base64）
                });
            }
            ,done: function(res){
                console.log(res);
                if(res.code == 100){
                    return layer.msg('上传成功');
                }
            }
            ,error: function(){
                //演示失败状态，并实现重传
                var demoText = $('#Img1Text');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function(){
                    uploadInst.upload();
                });
            }
        });
        //图片上传
        var uploadInst = upload.render({
            elem: '#btn2'
            ,url: '${APP_PATH}/editImg2' //改成您自己的上传接口
            ,before: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#Img2').attr('src', result); //图片链接（base64）
                });
            }
            ,done: function(res){
                console.log(res);
                if(res.code == 100){
                    return layer.msg('上传成功');
                }
            }
            ,error: function(){
                //演示失败状态，并实现重传
                var demoText = $('#Img2Text');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function(){
                    uploadInst.upload();
                });
            }
        });
        //图片上传
        var uploadInst = upload.render({
            elem: '#btn3'
            ,url: '${APP_PATH}/editImg3' //改成您自己的上传接口
            ,before: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#Img3').attr('src', result); //图片链接（base64）
                });
            }
            ,done: function(res){
                console.log(res);
                if(res.code == 100){
                    return layer.msg('上传成功');
                }
            }
            ,error: function(){
                //演示失败状态，并实现重传
                var demoText = $('#Img3Text');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function(){
                    uploadInst.upload();
                });
            }
        });
        //图片上传
        var uploadInst = upload.render({
            elem: '#btn4'
            ,url: '${APP_PATH}/editImg4' //改成您自己的上传接口
            ,before: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#Img4').attr('src', result); //图片链接（base64）
                });
            }
            ,done: function(res){
                console.log(res);
                if(res.code == 100){
                    return layer.msg('上传成功');
                }
            }
            ,error: function(){
                //演示失败状态，并实现重传
                var demoText = $('#Img4Text');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function(){
                    uploadInst.upload();
                });
            }
        });



    });



</script>
</body>
</html>

