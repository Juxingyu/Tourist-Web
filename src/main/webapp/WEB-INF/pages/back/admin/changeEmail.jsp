<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <link href="${pageContext.request.contextPath }/static/css/index.css" rel="stylesheet" type="text/css"/>
    <title>变更邮箱</title>
    <script src="${pageContext.request.contextPath }/static/js/jquery.min.js" type="text/javascript"></script>
    <script>
        $(function () {
            $("#changeEmail").hide();
            $("#chemail").hide();
        });

        //回车事件
        function enter(e) {
            var email = $("#email").val();
            var evt = window.event || e;
            if (evt.keyCode == 13) {
                //回车后要干的业务代码
                alert("huiche");
                sendEmail();
            }
        }

        function changeEmail() {
            var email = $("#email").val();
            var validate = $("#validate").val();
            $.ajax({
                url: "${pageContext.request.contextPath }/back/admin/changeEmail",
                async: true,//请求是否异步，默认为异步，这也是ajax重要特性
                data: {"email": email, "validate": validate},    //参数值
                type: "post",   //请求方式
                // processData: false,   // jQuery不要去处理发送的数据
                // contentType: false,
                success: function (data) {
                    if (data == "0") {
                        alert("随机码错误！");
                    }
                    if (data == "1") {
                        alert("修改成功");
                        window.location.reload();
                    }
                    if (data == "2") {
                        alert("修改失败！稍后重试");
                    }
                }
            });
        }

        function sendEmail() {
            var email = $("#email").val();
            $.ajax({
                url: "${pageContext.request.contextPath }/back/admin/checkEmail",
                async: true,//请求是否异步，默认为异步，这也是ajax重要特性
                data: {"email": email},    //参数值
                type: "post",   //请求方式
                success: function (data) {
                    if (data == "0") {
                        alert("该邮箱已被注册使用！");
                    }
                    if (data == "1") {
                        alert("允许更改邮箱！随机码已发送");
                        $("#changeEmail").show();
                        $("#chemail").show();
                        $("#sendemail").hide();
                    }
                },
            });
        }
    </script>
</head>
<body style="background:#efeeec;">
<div class="pagebody6" style="padding:0px;">
    <h1 style="font-size:30px;"><span style="margin-left:150px">变更邮箱</span></h1>
    <div class="margin1 padding3"><span>邮箱账号：</span>
        <input class="xinzeng" type="text" name="email" id="email" onkeydown="enter(event)"/>
    </div>
    <div class="margin1 padding3" id="changeEmail"><span>验证码：</span>
        <input class="xinzeng" type="text" name="validate" id="validate"/>
    </div>
    <input type="button" id="chemail" class="tianjia" value="变更邮箱" onclick="changeEmail()"/>
    <input type="button" id="sendemail" class="tianjia" value="发送验证" onclick="sendEmail()"/>
</div>
</body>
</html>

