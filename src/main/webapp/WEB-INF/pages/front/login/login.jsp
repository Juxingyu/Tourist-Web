<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>爱途旅游分享网-登录</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/front/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/front/css/login.css">
    <!--导入angularJS文件-->
    <script src="${pageContext.request.contextPath }/static/front/js/angular.min.js"></script>
    <!--导入jquery-->
    <script src="${pageContext.request.contextPath }/static/front/js/jquery-3.3.1.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/pages/front/index/header.jsp" %>

<section id="login_wrap">
    <div class="fullscreen-bg"
         style="background: url(${pageContext.request.contextPath }/static/front/images/login_bg.png);height: 532px;">

    </div>
    <div class="login-box">
        <div class="title">
            <img src="${pageContext.request.contextPath }/static/front/images/login_logo.png" alt="">
            <span>登录爱途旅游分享网</span>
        </div>
        <div class="login_inner">
            <!--登录错误提示消息-->
            <%--<div id="errorMsg" class="alert alert-danger"></div>--%>
            <div style="text-align: center">
                <span id="errorMsg" style="color: red;"></span>
            </div>
            <form id="loginForm" action="${pageContext.request.contextPath }/front/login/login" method="post"
                  accept-charset="utf-8">
                <input type="hidden" name="action" value="login"/>
                <input name="username" type="text" placeholder="请输入账号" autocomplete="off" id="loginName"
                       style="margin-top: 40px">
                <input name="password" type="password" placeholder="请输入密码" autocomplete="off" id="pass">
                <div class="verify">
                    <input name="check" type="text" placeholder="请输入验证码" autocomplete="off" id="validateCode">
                    <div style="height: 60px;width: 30px;float:left;margin-left: 7px"><img
                            src="${pageContext.request.contextPath }/login/Kaptcha.jpg" alt="验证码" id="imageValidate"
                            style="width: 60px;height: 27px; line-height: 27px;"
                            onclick="refreshCode()"></div>
                    <span><a href="${pageContext.request.contextPath }/front/login/forgetPassword"
                             style="float: right; margin:5px 16px 0 0; font-size: 13px;color: red">忘记密码</a></span>
                    <script type="text/javascript">
                        //图片点击事件
                        function refreshCode() {
                            document.getElementById('imageValidate').src = '${pageContext.request.contextPath }/login/Kaptcha.jpg?'
                                + Math.random();
                        }
                    </script>
                </div>
                <div class="submit_btn">
                    <button type="button" onclick="login()">登录</button>

                </div>
            </form>
            <div class="reg">没有账户？<a href="${pageContext.request.contextPath }/front/login/toRegister">立即注册</a></div>
        </div>
    </div>
</section>

<!--引入尾部-->
<%@ include file="/WEB-INF/pages/front/index/footer.jsp" %>
<!--引入尾部 End-->


<script type="text/javascript">
    function login() {
        //取到登录账号
        var loginName = $("#loginName").val();
        //取到密码
        var pass = $("#pass").val();
        //取到验证码
        var validateCode = $("#validateCode").val();
        if (loginName == '' || pass == '' || validateCode == "") {
            alert("用户名、密码、验证码不能为空");

        } else {
            $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath }/front/login/login",
                    data: {
                        'loginName': loginName,
                        'pass': pass,
                        'validateCode': validateCode
                    },
                    success: function (data) {
                        if (data == 1) {
                            $("#errorMsg").html("验证码错误");
                        } else if (data == 2) {
                            $("#errorMsg").html("用户信息错误");
                        } else if (data == 3) {
                            window.location.href = "${pageContext.request.contextPath }/front/index/index";
                        }
                    }
                }
            );
        }
    }
</script>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${pageContext.request.contextPath }/static/front/js/jquery-1.11.0.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${pageContext.request.contextPath }/static/front/js/bootstrap.min.js"></script>
<!--导入布局js，共享header和footer-->
<script type="text/javascript" src="${pageContext.request.contextPath }/static/front/js/include.js"></script>
</body>
</html>
