<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="${pageContext.request.contextPath }/static/css/index.css" rel="stylesheet" type="text/css"/>
    <title>添加景点类型</title>
</head>
<body style="background:#efeeec;">
<div class="pagebody6" style="padding:0px;">
    <form action="${pageContext.request.contextPath }/scenery/insertSceType" method="get">
        <h1 style="font-size:30px;"><span style="margin-left:150px">新增景点类别</span></h1>
        <div class="margin1 padding3"><span>景点类别</span>
            <input type="text" class="xinzeng" id="scenictpyeName" name="scenictpyeName"/>
        </div>
        <input type="submit" class="tianjia" value="添加景点类型"/>
    </form>
</div>
</body>
</html>
