<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <link href="${pageContext.request.contextPath }/static/css/index.css" rel="stylesheet" type="text/css"/>
    <title>修改密码</title>
    <script src="${pageContext.request.contextPath }/static/js/jquery.min.js" type="text/javascript"></script>
    <script>
        function changePwd() {
            var oldPwd = $("#oldPwd").val();
            var newPwd = $("#newPwd").val();
            var confirmPwd = $("#confirmPwd").val();
            if (newPwd == confirmPwd) {
                $.ajax({
                    url: "${pageContext.request.contextPath }/back/admin/changePwd",    //请求的url地址
                    async: true,//请求是否异步，默认为异步，这也是ajax重要特性
                    data: {"oldPwd": oldPwd, "newPwd": newPwd},    //参数值
                    type: "post",   //请求方式
                    success: function (data) {
                        if (data == "3") {
                            alert("原密码输入错误！");
                        }
                        if (data == "2") {
                            alert("对不起，密码更改失败！");
                        }
                        if (data == "1") {
                            alert("恭喜你，密码修改成功！请进行重新登录");
                            window.parent.location.href = '${pageContext.request.contextPath }/back/login/logout';
                        }
                    },
                });
            } else {
                alert("新密码和确认密码不一致！");
            }
        }
    </script>
</head>
<body style="background:#efeeec;">
<div class="pagebody6" style="padding:0px;">
    <h1 style="font-size:30px;"><span style="margin-left:150px">修改密码</span></h1>
    <div class="margin1 padding3"><span>原密码：</span>
        <input class="xinzeng" type="password" name="oldPwd" id="oldPwd" style="margin-left: 14px"/>
    </div>
    <div class="margin1 padding3"><span>新密码：</span>
        <input class="xinzeng" type="password" name="newPwd" id="newPwd" style="margin-left: 14px"/>
    </div>
    <div class="margin1 padding3"><span>确认密码：</span>
        <input class="xinzeng" type="password" name="confirmPwd" id="confirmPwd"/>
    </div>
    <input type="button" class="tianjia" value="修改密码" onclick="changePwd()"/>
</div>
</body>
</html>

