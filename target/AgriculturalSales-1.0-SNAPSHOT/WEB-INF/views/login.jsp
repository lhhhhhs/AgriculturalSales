<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>登录</title>
    <script src="${APP_PATH}/static/js/jquery-3.5.0.js"></script>
    <link rel="stylesheet" href="https://www.jq22.com/jquery/bootstrap-4.2.1.css">
    <script src="https://www.jq22.com/jquery/bootstrap-4.2.1.js"></script>
    <link href="${APP_PATH}/static/css/font-awesome.min.css" rel="stylesheet">
    <script src="${APP_PATH}/static/js/yz.js"></script>
    <link rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/login.css"/>
    <style>
        .icon {
            font-family: FontAwesome;
        }
    </style>
</head>

<body>
<div class="shadow mb-5 bg-light rounded login-top">
    <div class="head">
        <img src="images/logo.png"><span><h1>·</h1>登录</span>
    </div>
</div>
<div class="login-box">

    <div class="slogan">
        欢迎光临<br><span class="yellow">&nbsp;&nbsp;&nbsp;茂名特产</span>
    </div>
    <!-- 登录 -->
    <div class="login">
        <div class="login-logo">
            <img src="images/logo1.png" class="img-fluid" alt="logo">
        </div>

        <div class="login-form">
            <form method="POST" action="${APP_PATH}/login">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                            <span class="input-group-text">
                                <span class="fa fa-user-circle-o text-success"></span>
                            </span>
                    </div>
                    <input type="text" class="form-control" name="loginPhone" id="loginPhone" placeholder="手机号码">
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                            <span class="input-group-text">
                                <span class="fa fa-unlock text-success"></span>
                            </span>
                    </div>
                    <input type="password" class="form-control" name="loginPwd" id="loginPwd" placeholder="密码">
                </div>
                <input type="submit" id="loginBtn" class="btn btn-success btn-block" value="登录"/>
            </form>
        </div>
        <div class="login-regist">
            没有帐号？<span id="ToRegist">立即注册</span>
            <c:if test="${not empty requestScope.msg}">
                <p id="msg" style="color:red; margin-left: 20%">${requestScope.msg}</p>
            </c:if>
        </div>
    </div>
    <!-- 注册 -->
    <div class="regist">
        <div class="regist-title">
            手机注册<span><img src="images/logo.png" height="40px"></span>
        </div>
        <div class="regist-form">
            <form method="POST" id="registerForm">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                            <span class="input-group-text">
                                <span class="fa fa-mobile text-success"></span>
                            </span>
                    </div>
                    <input type="text" class="form-control" name="phone" id="phone" placeholder="手机号码">
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                            <span class="input-group-text">
                                <span class="fa fa-unlock-alt text-success"></span>
                            </span>
                    </div>
                    <input type="password" class="form-control" name="pwd" id="pwd" placeholder="密码">
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                            <span class="input-group-text">
                                <span class="fa fa-unlock-alt text-success"></span>
                            </span>
                    </div>
                    <input type="password" class="form-control" name="checkPwd" id="checkpwd" placeholder="确认密码">
                </div>
                <div class="input-group mb-3">
                    <input type="text" class="form-control" placeholder="手机验证码" id="code" style="width:50px"
                           name="code">
                    <div class="input-group-append">
                        <input class="input-group-text text-success" readonly id="btn" name="btn" style="width: 140px"
                               value="发送验证码"/>
                    </div>
                </div>

                <button type="button" id="registerBtn" class="btn btn-success btn-block">注册</button>
            </form>
        </div>

        <div class="login-login">
            <span id="phoneLogin">手机注册</span>
            <span id="ToLogin">返回登录</span>
        </div>
        <p id="PhoneMsg" style="color:red; margin-left: 20%"></p>
    </div>
</div>
<div class="login-footer">
    <div class="container">
        <div class="row">
            <div class="col text-center login-nav">
                <ul>
                    <li><a href="#">首页</a>
                        <div class="border"></div>
                    </li>
                    <li><a href="#">关于我们</a>
                        <div class="border"></div>
                    </li>
                    <li><a href="#">企业招聘</a>
                        <div class="border"></div>
                    </li>
                    <li><a href="#">合作专区</a>
                        <div class="border"></div>
                    </li>
                    <li><a href="#">联系我们</a>
                        <div class="border"></div>
                    </li>
                    <li><a href="#">意见反馈</a></li>
                    <a href="${APP_PATH}/test">asdfsdfsfd</a>
                </ul>
                <div class="foot">
                </div>
            </div>
        </div>
    </div>
</div>


<script>
    // setTimeout('changeState()', 1000)
    var InterValObj; //timer变量，控制时间
    var count = 60; //间隔函数，1秒执行
    var curCount;//当前剩余秒数
    var sms = "";


    $(function () {
        //注册登录切换
        //登录
        $('#ToRegist').click(function () {
            //console.log('登录')
            $('.regist').css('display', 'block');
            $('.login').css('display', 'none')
        });
        //注册
        $('#ToLogin').click(function () {
            //console.log('注册')
            $('.regist').css('display', 'none');
            $('.login').css('display', 'block')
        })
    });


    function sendMessage() {
        var phone = $("#phone").val();
        var str = "";
        if (phone != "") {
            if (!(/^1[3456789]\d{9}$/.test(phone))) {
                str = "手机号错误";
                $("#PhoneMsg").text(str);

            } else {

                if (checkedPwd()) {
                    str = "";
                    $("#PhoneMsg").text(str);
                    curCount = count;
                    $("#btn").attr("disabled", "true");
                    $("#btn").val(curCount + "秒后可重新发送");
                    realTimeClData = setInterval(SetRemainTime, 1000);
                    // ajax发送验证码请求
                    $.ajax({
                        url: "${APP_PATH}/sendSms?phone=" + $("#phone").val(),
                        type: "POST",
                        //允许ajax携带cookie
                        xhrFields: {
                            withCredentials: true // 发送Ajax时，Request header中会带上 Cookie 信息。
                        },
                        crossDomain: true, // 发送Ajax时，Request header 中会包含跨域的额外信息，但不会含cookie（作用不明，不会影响请求头的携带）
                        success: function (code) {
                            sms = code;
                            console.log(code);
                            if (sms.code == 100) {
                                $("#PhoneMsg").text("验证码发送成功！");
                            } else {
                                $("#PhoneMsg").text("验证码发送失败！");
                            }
                        }
                    });

                }


            }
        } else {
            str = "手机号不能为空";
            $("#PhoneMsg").text(str);
        }

    }

    //timer处理函数
    function SetRemainTime() {
        if (curCount == 0) {
            clearInterval(realTimeClData);//停止计时器
            $("#btn").removeAttr("disabled");//启用按钮
            $("#btn").val("重新发送验证码");
        } else {
            curCount--;
            $("#btn").val(curCount + "秒后可重新发送");
        }
    }


    //验证两次密码是否一致并且规定密码格式
    function checkedPwd() {
        var pwd = $("#pwd").val();
        var checkpwd = $("#checkpwd").val();
        if (pwd == "" || checkpwd == "") {
            $("#PhoneMsg").text("请填写密码");
            return false;
        }
        if (!(/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$/.test(pwd))) {
            $("#PhoneMsg").text("密码由数字和字母组成，且长度要在8-16位之间。");
            return false;
        }
        if (pwd == checkpwd) {
            return true;
        } else {
            $("#PhoneMsg").text("两次密码不一致");
            return false;
        }
    };


    $("#btn").click(function () {
        sendMessage();
    });

    $("#registerBtn").click(function () {
        var phone = $("#phone").val();
        var str = "";
        if (phone != "") {
            if (!(/^1[3456789]\d{9}$/.test(phone))) {
                str = "手机号错误";
                $("#PhoneMsg").text(str);

            } else {
                //验证密码
                if (checkedPwd()) {
                    //验证验证码
                    var code = $("#code").val();
                    $.ajax({
                        url: "${APP_PATH}/checkCode?code=" + code,
                        type: "POST",
                        // 允许ajax携带cookie
                        xhrFields: {
                            withCredentials: true // 发送Ajax时，Request header中会带上 Cookie 信息。
                        },
                        crossDomain: true, // 发送Ajax时，Request header 中会包含跨域的额外信息，但不会含cookie（作用不明，不会影响请求头的携带）
                        success: function (ret) {
                            if (true) {
                                //发送ajax请求完成注册
                                $.ajax({
                                    url: "${APP_PATH}/saveUser",
                                    type: "POST",
                                    data: $("#registerForm").serialize(),
                                    // 允许ajax携带cookie
                                    xhrFields: {
                                        withCredentials: true // 发送Ajax时，Request header中会带上 Cookie 信息。
                                    },
                                    crossDomain: true, // 发送Ajax时，Request header 中会包含跨域的额外信息，但不会含cookie（作用不明，不会影响请求头的携带）
                                    success: function (ret) {
                                        if (ret.code == 100) {
                                            $("#PhoneMsg").text("");
                                        } else{
                                            $("#PhoneMsg").text(ret.extend.msg);
                                        }
                                    }
                                });
                            }else {
                                $("#PhoneMsg").text("验证码错误！");
                            }
                        }
                    });



                }


            }
        } else {
            str = "手机号不能为空";
            $("#PhoneMsg").text(str);
        }
    })


</script>
</body>

</html>

