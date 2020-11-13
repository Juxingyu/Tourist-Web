<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link href="${pageContext.request.contextPath }/static/css/index.css" rel="stylesheet" type="text/css"/>
    <title>个人信息详情</title>
</head>
<body style="background:#efeeec;">
<div class="pagebody6" style="padding:0px;">
    <%--<form action="${pageContext.request.contextPath }/scenery/insertScenery" enctype="multipart/form-data"--%>
    <%--method="post">--%>
    <h1 style="font-size:30px;"><span style="margin-left:150px">个人信息</span></h1>
    <div class="margin1 padding3"><span>登录名称：</span>
        <input type="text" id="loginName" class="xinzeng" name="loginName" value="${requestScope.admin.loginName}"
               disabled/>
    </div>
    <div class="margin1 padding3"><span>昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</span>
        <input class="xinzeng" type="text" name="petName" id="petName" value="${requestScope.admin.petName}"/>
    </div>
    <div class="margin1 padding3"><span>身&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;份：</span>
        <input class="xinzeng" type="text" name="petName" value="管理员" disabled="disabled"/>
    </div>
    <div class="margin1 padding3"><span>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：</span>
        <input class="xinzeng" type="text" name="email" id="email" value="${requestScope.admin.email}" disabled/>
    </div>
    <div class="margin1 padding3"><span>注册时间：</span>
        <input class="xinzeng" type="text" name="update" id="update"
               value="<fmt:formatDate value="${requestScope.admin.update}"                        pattern="yyyy-MM-dd HH:mm:ss"/>"
               disabled/>
    </div>
    <div class="margin1 padding3">
        <span class="padding4">个人简介：</span>
        <textarea id="secDesc" name="secDesc" class="miaoshu"></textarea>
    </div>
    <%--<input type="submit" class="tianjia" value="修改信息"/>--%>
    <%--</form>--%>
</div>
</body>
</html>
