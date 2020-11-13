<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="${pageContext.request.contextPath }/static/css/index.css" rel="stylesheet" type="text/css"/>
    <title>添加攻略类型</title>
</head>
<body style="background:#efeeec;">
<div class="pagebody6" style="padding:0px;">
    <form action="${pageContext.request.contextPath }/strategy/insertStraType" method="get">
        <h1 style="font-size:30px;"><span style="margin-left:150px">新增攻略类别</span></h1>
        <div class="margin1 padding3"><span>攻略类型</span>
            <input type="text" class="xinzeng" id="stratypeName" name="stratypeName"/>
        </div>
        <input type="submit" class="tianjia" value="添加攻略类型"/>
    </form>
</div>
</body>
</html>
