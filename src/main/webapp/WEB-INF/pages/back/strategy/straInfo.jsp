<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="${pageContext.request.contextPath }/static/css/index.css" rel="stylesheet" type="text/css"/>
    <title>修改攻略信息</title>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery.min.js"></script>
    <c:if test="${requestScope.i>0}">
        <script type="text/javascript">
            alert("修改成功！")
            window.location.href = "${pageContext.request.contextPath }/strategy/strategyList";
        </script>
    </c:if>
</head>
<body style="background:#efeeec;">
<div class="pagebody6" style="padding:0px;">
    <form action="${pageContext.request.contextPath }/strategy/updateStrategy" enctype="multipart/form-data"
          method="post">
        <h1 style="font-size:30px;"><span style="margin-left:130px">修改攻略信息</span></h1>
        <div hidden="hidden">
            <input type="text" id="straId" name="straId" value="${requestScope.straById.straId}" readonly="readonly"/>
        </div>
        <div class="margin1 padding3"><span>攻略类别</span>
            <input type="text" id="stratypeName" class="xinzeng" name="stratypeName"
                   value="${requestScope.straById.stratypeName}">
        </div>
        <div class="margin1 padding3"><span>攻略标题</span>
            <input type="text" class="xinzeng" id="straTitle" name="straTitle"
                   value="${requestScope.straById.straTitle}"
                   readonly="readonly"/>
        </div>
        <div class="margin1 padding3"><span>收藏数&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <input type="text" id="praseCount" class="xinzeng" name="praseCount"
                   value="${requestScope.straById.praseCount}"/>
        </div>
        <div class="margin1 padding3">
            <span class="padding4">攻略内容</span>
            <textarea id="secDesc" name="secDesc" class="miaoshu">${requestScope.straById.straContext}</textarea>
        </div>
        <input type="submit" style="margin-left: 73px" class="tianjia" value="修改攻略信息"/>
    </form>
</div>
</body>
</html>
