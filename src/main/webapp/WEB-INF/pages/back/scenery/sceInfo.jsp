<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="${pageContext.request.contextPath }/static/css/index.css" rel="stylesheet" type="text/css"/>
    <title>修改景点信息</title>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery.min.js"></script>
    <c:if test="${requestScope.i>0}">
        <script type="text/javascript">
            alert("修改成功！")
            window.location.href = "${pageContext.request.contextPath }/scenery/sceneryList";
        </script>
    </c:if>
</head>
<body style="background:#efeeec;">
<div class="pagebody6" style="padding:0px;">
    <form action="${pageContext.request.contextPath }/scenery/updateScenery" enctype="multipart/form-data"
          method="post">
        <h1 style="font-size:30px;"><span style="margin-left:130px">修改景点信息</span></h1>
        <div hidden="hidden">
            <input type="text" id="sceId" name="sceId" value="${requestScope.sceById.sceId}" readonly="readonly"/>
        </div>
        <div class="margin1 padding3"><span>景点类别</span>
            <input type="text" id="scenictpyeName" class="xinzeng" name="scenictpyeName"
                   value="${requestScope.sceById.scenictpyeName}">
            <%--<select class="xinzeng" id="scenictypeId" name="scenictypeId">
                <option value="${requestScope.scenery.scenictpyeName}"></option>
            </select>--%>
        </div>
        <div class="margin1 padding3"><span>景点名称</span>
            <input type="text" class="xinzeng" id="secName" name="secName" value="${requestScope.sceById.secName}"
                   readonly="readonly"/>
        </div>
        <div class="margin1 padding3"><span>地理位置</span>
            <input type="text" id="secSite" class="xinzeng" name="secSite" value="${requestScope.sceById.secSite}"/>
        </div>
        <div class="margin1 padding3"><span>景点级别</span>
            <input type="text" id="secLevel" class="xinzeng" name="secLevel" value="${requestScope.sceById.secLevel}"/>
        </div>
        <div class="margin1 padding3"><span>开放时间</span>
            <input type="text" class="xinzeng" name="openTime" value="${requestScope.sceById.openTime}"/>
        </div>
        <div class="margin1 padding3">
            <span class="padding4">景点简介</span>
            <textarea id="secDesc" name="secDesc" class="miaoshu">${requestScope.sceById.secDesc}</textarea>
        </div>
        <input type="submit" style="margin-left: 73px" class="tianjia" value="修改景点信息"/>
    </form>
</div>
</body>
</html>
