<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="${pageContext.request.contextPath }/static/css/index.css" rel="stylesheet" type="text/css"/>
    <title>添加景点</title>
    <c:if test="${requestScope.i>0}">
        <script type="text/javascript">
            alert("添加成功！")
        </script>
    </c:if>
</head>
<body style="background:#efeeec;">
<div class="pagebody6" style="padding:0px;">
    <form action="${pageContext.request.contextPath }/scenery/insertScenery" enctype="multipart/form-data"
          method="post">
        <h1 style="font-size:30px;"><span style="margin-left:150px">新增景点</span></h1>
        <div class="margin1 padding3"><span>景点类别</span>
            <select class="xinzeng" id="scenictypeId" name="scenictypeId">
                <option value="-1">请选择</option>
                <c:forEach items="${requestScope.scenicTypeList}" var="cp">
                    <option value="${cp.scenictypeId}">${cp.scenictpyeName}</option>
                </c:forEach>
            </select>
        </div>
        <div class="margin1 padding3"><span>景点名称</span>
            <input type="text" id="secName" class="xinzeng" name="secName"/>
        </div>
        <div class="margin1 padding3"><span>地理位置</span>
            <input type="text" id="secSite" class="xinzeng" name="secSite"/>
        </div>
        <div class="margin1 padding3"><span>景点级别</span>
            <input type="text" id="secLevel" class="xinzeng" name="secLevel"/>
        </div>
        <div class="margin1 padding3"><span>开放时间</span>
            <input type="text" id="openTime" class="xinzeng" name="openTime"/>
        </div>
        <div class="margin1 padding3"><span>景点图片</span>
            <input class="xinzeng" type="file" name="file" value="选择上传"/>
        </div>
        <div class="margin1 padding3">
            <span class="padding4">景点简介</span>
            <textarea id="secDesc" name="secDesc" class="miaoshu"></textarea>
        </div>
        <input type="submit" style="margin-left: 73px" class="tianjia" value="添加景点"/>
    </form>
</div>
</body>
</html>
