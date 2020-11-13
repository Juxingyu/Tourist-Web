<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>个人信息</title>
    <link href="${pageContext.request.contextPath }/static/front/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/front/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/front/css/login.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/front/css/index.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/front/css/route-detail.css">
    <!--导入angularJS文件-->
    <script src="${pageContext.request.contextPath }/static/front/js/angular.min.js"></script>
    <!--导入jquery-->
    <script src="${pageContext.request.contextPath }/static/front/js/jquery-3.3.1.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/pages/front/index/header.jsp" %>

<section id="login_wrap">
    <%--<div class="fullscreen-bg"
         style="background: url(${pageContext.request.contextPath }/static/front/images/login_bg.png);height: 532px;">

    </div>--%>
    <%--<div class="fullscreen-bg"--%>
    <%--style="height: 532px;">--%>
    <%--</div>--%>

    <div class="bread_box" style="width: 1150px;">
        <a style="color: #0c7cb5">当前位置：</a>
        <%-- <a href="${pageContext.request.contextPath }/front/scenery/sceList">发现好景点</a>
         <span> &gt;&gt;</span>--%>
        <a href="#" style="color: #0c7cb5">个人信息</a>
    </div>
    <div class="prosum_box" style="text-align: center;width: 700px">
        <table class="table">
            <tbody>
            <tr>
                <td style="text-align: right">登录名：</td>
                <td style="text-align: center">${requestScope.users.loginName}</td>
            </tr>
            <tr>
                <td style="text-align: right">昵称：</td>
                <td style="text-align: center">
                    <input type="text" style="font-size: 14px;text-align: center; border: 1px solid #F4F4F4"
                           value="${requestScope.users.petName}"></td>
            </tr>
            <tr style="row-span: 2">
                <td style="text-align: right;">头像：</td>
                <td style="text-align: center">
                    <div style="text-align: center"><img
                            src="${pageContext.request.contextPath }/static/images/${requestScope.users.pic}"
                            alt="" width="100px" style="margin: 0 auto"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td style="text-align: right">邮箱：</td>
                <td style="text-align: center">
                    <input type="text" style="font-size: 14px;border: 1px solid #F4F4F4"
                           value="${requestScope.users.email}"></td>
            </tr>
            <tr>
                <td style="text-align: right">注册时间：</td>
                <td style="text-align: center"><span class="ds-time" datetime="${requestScope.users.regDate}"
                                                     title="2016年1月28日 下午8:13:29"><fmt:formatDate
                        value="${requestScope.users.regDate}"
                        pattern="yyyy-MM-dd HH:mm:ss"/></span></td>
            </tr>
            </tbody>
        </table>
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
