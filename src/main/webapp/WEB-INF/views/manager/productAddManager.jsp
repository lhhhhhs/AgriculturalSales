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
            <div class="layui-col-md7" style="margin: 20px;">
                <form class="layui-form layui-form-pane" action="${APP_PATH}/saveProduct" style="width: 80%">
                    <div class="layui-form-item" pane>
                        <label class="layui-form-label">商品名称</label>
                        <div class="layui-input-block">
                            <input type="text" name="name"  lay-verify="required" autocomplete="off" placeholder="请输入名称" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">单价</label>
                        <div class="layui-input-block">
                            <input type="text" name="price"  lay-verify="number|required" autocomplete="off" placeholder="￥" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">库存</label>
                        <div class="layui-input-block">
                            <input type="text" name="stock"  lay-verify="number|required" autocomplete="off" placeholder="库存" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item layui-row">
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
                        <div class="layui-inline layui-col-md3" style="margin-left: 20px;">
                            <label class="layui-form-label">类别2</label>
                            <div class="layui-input-inline " style="width: 95%">
                                <select name="k2" id="cate2"  lay-filter="cate2"  lay-verify="required" lay-search="">
                                    <option value="">请选择类别2</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline layui-col-md3" style="margin-left: 20px;">
                            <label class="layui-form-label">类别3</label>
                            <div class="layui-input-inline" style="width: 95%">
                                <select name="k3" id="cate3"  lay-filter="cate3" lay-verify="required"  lay-search="">
                                    <option value="">请选择类别3</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">简介</label>
                        <div class="layui-input-block">
                            <textarea name="info" lay-verify="required"  placeholder="请输入内容" class="layui-textarea"></textarea>
                        </div>
                    </div>
                    <%-- 图片路径--%>
                    <input type="hidden"  lay-verify="img1" name="imgPath" id="Img1">
                    <input type="hidden"  lay-verify="img2" name="detailsImgPath" id="Img2">
                    <div class="layui-upload">
                        <button type="button" class="layui-btn" id="pImgBtn">上传商品介绍图片</button>
                        <div class="layui-upload-list">
                            <img class="layui-upload-img" width="150px" height="100px;" id="Img11">
                            <p id="Img1Text"></p>
                        </div>
                    </div>
                        <div class="layui-input-block" style="margin-left: 50%;margin-top: -10%;">
                            <input LAY-SUBMIT="" type="submit" class="layui-btn"  value="创建"/>
                            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                        </div>
                </form>
            </div>
            <div class="layui-col-md4" style="margin-top: 20px;margin-left: -80px; ">
                <div class="layui-upload">
                    <button type="button" class="layui-btn" id="pImgDetailsBtn">上传商品详情图片</button>
                    <div class="layui-upload-list">
                        <img class="layui-upload-img" width="500px" height="500px;" id="Img22">
                        <p id="Img2Text"></p>
                    </div>
                </div>
            </div>
        </div>
<%--        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">--%>
<%--            <legend>常规使用：普通图片上传</legend>--%>
<%--        </fieldset>--%>


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
        $("#addProduct").addClass("layui-this");
        $("#productNav").addClass("layui-nav-itemed");


        //自定义验证规则
        form.verify({
            img1: function(value){
                if(value.length == 0){
                    return '请上传商品图片';
                }
            },
            img2: function(value){
                if(value.length == 0){
                    return '请上传详情图片';
                }
            }
            ,content: function(value){
                layedit.sync(editIndex);
            }
        });
        //三级联动
        form.on("select(cate1)",function () {
            var optionstring = "";
            var cate1 = $("#cate1").val();
            <c:forEach items="${k2List}" var="kind2">
            if(cate1==${kind2.k1}) {
                optionstring += "<option  name='cate2' value='${kind2.id}'>${kind2.name}</option>";
            }
            </c:forEach>
            $("#cate2").html("  <option value=''selected>请选择</option>"+optionstring);
            form.render('select');
        });

        //三级联动
        form.on("select(cate2)",function () {
            var optionstring = "";
            var cate2 = $("#cate2").val();
            <c:forEach items="${k3List}" var="kind3">
            if(cate2==${kind3.k2}) {
                optionstring += "<option  name='cate3' value='${kind3.id}'>${kind3.name}</option>";
            }
            </c:forEach>
            $("#cate3").html("  <option value=''selected>请选择</option>"+optionstring);
            form.render('select');
        });

        //介绍图片上传
        var uploadInst = upload.render({
            elem: '#pImgBtn'
            ,url: '${APP_PATH}/uploadImg1' //改成您自己的上传接口
            ,before: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#Img11').attr('src', result); //图片链接（base64）
                });
            }
            ,done: function(res){
                console.log(res);
                if(res.code == 100){
                    $("#Img1").val(res.extend.path);
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
        //详情图片上传
        var uploadInst = upload.render({
            elem: '#pImgDetailsBtn'
            ,url: '${APP_PATH}/uploadImg2' //改成您自己的上传接口
            ,before: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#Img22').attr('src', result); //图片链接（base64）
                });
            }
            ,done: function(res){
                console.log(res);
                if(res.code == 100){
                    $("#Img2").val(res.extend.path);
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



    });



</script>
</body>
</html>

