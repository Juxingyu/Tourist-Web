<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>忘记密码</title>
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
    <div class="login-box" style="height: 400px">
        <div class="title">
            <img src="${pageContext.request.contextPath }/static/front/images/login_logo.png" alt="">
            <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;忘记密码</span>
        </div>
        <%--验证用户名、验证码--%>
        <div class="login_inner">
            <!--登录错误提示消息-->
            <%--<div id="errorMsg" class="alert alert-danger"></div>--%>
            <div style="text-align: center">
                <span id="errorMsg" style="color: red;"></span>
            </div>
            <form id="loginForm" action="${pageContext.request.contextPath }/front/login/login" method="post"
                  accept-charset="utf-8">
                <input type="hidden" name="action" value="login"/>
                <input name="username" type="text" placeholder="请输入账号" autocomplete="off" id="loginName" value=""
                       style="margin-top: 40px">
                <input name="email" type="text" placeholder="请输入账号绑定邮箱" autocomplete="off" id="email" value="">
                <div class="verify" id="verify">
                    <input name="check" type="text" placeholder="请输入验证码" autocomplete="off" id="validateCode" value="">
                    <div style="height: 60px;width: 30px;float:left;margin-left: 7px"><img
                            src="${pageContext.request.contextPath }/login/Kaptcha.jpg" alt="验证码" id="imageValidate"
                            style="width: 60px;height: 27px; line-height: 27px;"
                            onclick="refreshCode()"></div>
                    <script type="text/javascript">
                        //图片点击事件
                        function refreshCode() {
                            document.getElementById('imageValidate').src = '${pageContext.request.contextPath }/login/Kaptcha.jpg?'
                                + Math.random();
                        }
                    </script>
                </div>
                <div class="submit_btn">
                    <button type="button" id="nextt" onclick="next()">下一步</button>
                    <button type="button" id="sendVolidatee" onclick="sendVolidate()">获取新密码</button>
                    <button type="button" id="newPass" onclick="tologin()">去登陆</button>
                </div>
            </form>
        </div>
    </div>
</section>

<!--引入尾部-->
<%@ include file="/WEB-INF/pages/front/index/footer.jsp" %>
<!--引入尾部 End-->

<script type="text/javascript">
    $(function () {
        $("#sendVolidatee").hide();
        $("#newPass").hide();
        $("#check").hide();
    });

    function next() {
        //取到登录账号
        var loginName = $("#loginName").val();
        var email = $("#email").val();
        //取到验证码
        var validateCode = $("#validateCode").val();

        if (loginName == '' || email == '' || validateCode == "") {
            alert("用户名、邮箱、验证码不能为空");
        } else {
            $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath }/front/login/checkFrom",
                    data: {
                        'loginName': loginName,
                        'email': email,
                        'volidate': validateCode
                    },
                    success: function (data) {
                        if (data == 1) {
                            $("#errorMsg").html("用户信息有误！");
                        } else if (data == 2) {
                            $("#errorMsg").html("验证码错误！");
                        } else if (data == 3) {
                            //执行下一步
                            $("#errorMsg").html("")
                            $("#nextt").hide();
                            $("#verify").hide();
                            $("#sendVolidatee").show();
                            $("#check").show();
                        }
                    }
                }
            );
        }
    }

    function sendVolidate() {
        var email = $("#email").val();
        $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath }/front/login/sendEmail",
                data: {
                    'email': email
                },
                success: function (data) {
                    if (data == 1) {
                        $("#errorMsg").html("新密码已发送到您的邮箱！");
                        $("#nextt").hide();
                        $("#verify").hide();
                        $("#sendVolidatee").hide();
                        $("#newPass").show();
                    } else if (data == 2) {
                        $("#errorMsg").html("发送失败！");
                    }
                }
            }
        );
    }

    function tologin() {
        window.location.href = "${pageContext.request.contextPath }/front/login/tologin";
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
