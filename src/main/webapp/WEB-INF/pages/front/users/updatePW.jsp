<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改密码</title>
    <link href="${pageContext.request.contextPath }/static/front/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/front/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/front/css/login.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/front/css/index.css">
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
    <div class="login-box" style="height: 350px">
        <div class="title">
            <img src="${pageContext.request.contextPath }/static/front/images/login_logo.png" alt="">
            <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改密码</span>
        </div>
        <div class="login_inner">
            <!--登录错误提示消息-->
            <%--<div id="errorMsg" class="alert alert-danger"></div>--%>
            <form id="loginForm" method="post" accept-charset="utf-8">
                <input type="hidden" name="action" value="login"/>
                <input name="username" type="password" placeholder="请输入新密码" autocomplete="off" id="newPass"
                       style="margin-top: 40px">
                <input name="password" type="password" placeholder="请确认密码" autocomplete="off" id="enterPass">
                <div class="submit_btn">
                    <button type="button" onclick="update()">确认修改</button>
                </div>
            </form>
        </div>
    </div>
</section>

<!--引入尾部-->
<%@ include file="/WEB-INF/pages/front/index/footer.jsp" %>
<!--引入尾部 End-->


<script type="text/javascript">
    function update() {
        var newPass = $("#newPass").val();
        var enterPass = $("#enterPass").val();
        if (newPass == '' || enterPass == "") {
            alert("密码不能为空");
        } else if (newPass != enterPass) {
            alert("两次密码不一致！");
        } else {
            $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath }/users/updatePW",
                    data: {
                        'newPass': newPass
                    },
                    success: function (data) {
                        if (data == 1) {
                            alert("修改成功！即将重新登录");
                            window.location.href = "${pageContext.request.contextPath }/front/login/logout";
                        } else if (data == 2) {
                            alert("修改失败！")
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
