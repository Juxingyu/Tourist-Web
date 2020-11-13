<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="${pageContext.request.contextPath }/static/css/index.css" rel="stylesheet" type="text/css"/>
    <title>添加攻略</title>
</head>
<body style="background:#efeeec;">
<div class="pagebody6" style="padding:0px;">
    <form action="${pageContext.request.contextPath }/strategy/insertStrategy" enctype="multipart/form-data"
          method="post">
        <h1 style="font-size:30px;margin-left: 100px"><span >添加旅游攻略</span></h1>
        <div class="margin1 padding3"><span>攻略类别</span>
            <select class="xinzeng" id="stratypeId" name="stratypeId">
                <option value="-1">请选择</option>
                <c:forEach items="${requestScope.strategyTypeList}" var="cp">
                    <option value="${cp.stratypeId}">${cp.stratypeName}</option>
                </c:forEach>
            </select>
        </div>
        <div class="margin1 padding3"><span>攻略标题</span>
            <input type="text" id="straTitle" class="xinzeng" name="straTitle"/>
        </div>
        <div class="margin1 padding3"><span>攻略图片</span>
            <input class="xinzeng" type="file" name="file" value="选择上传"/>
        </div>
        <div class="margin1 padding3">
            <span class="padding4">攻略内容</span>
            <textarea id="straContext" name="straContext" class="miaoshu"></textarea>
        </div>
        <input type="submit" class="tianjia" style="margin-left: 65px" value="添加攻略"/>
    </form>
</div>
</body>
</html>
