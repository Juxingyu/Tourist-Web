<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="${pageContext.request.contextPath }/static/css/index.css" rel="stylesheet" type="text/css"/>
    <title>添加景点</title>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery.min.js"></script>
    <c:if test="${requestScope.i>0}">
        <script type="text/javascript">
            alert("添加成功！")
            window.location.href = "${pageContext.request.contextPath }/food/toInsertFood";
        </script>
    </c:if>
</head>
<body style="background:#efeeec;">
<div class="pagebody6" style="padding:0px;">
    <form action="${pageContext.request.contextPath }/food/insertFood" enctype="multipart/form-data"
          method="post">
        <h1 style="font-size:30px;"><span style="margin-left:150px">添加美食</span></h1>
        <%--<div class="margin1 padding3"><span>景点类别</span>--%>
        <%--<select class="xinzeng" id="scenictypeId" name="scenictypeId">--%>
        <%--<option value="-1">请选择</option>--%>
        <%--<c:forEach items="${requestScope.scenicTypeList}" var="cp">--%>
        <%--<option value="${cp.scenictypeId}">${cp.scenictpyeName}</option>--%>
        <%--</c:forEach>--%>
        <%--</select>--%>
        <%--</div>--%>
        <div class="margin1 padding3"><span>美食名称</span>
            <input type="text" id="foodName" class="xinzeng" name="foodName"/>
        </div>
        <div class="margin1 padding3"><span>美食图片</span>
            <input class="xinzeng" type="file" name="file" value="选择上传"/>
        </div>
        <div class="margin1 padding3">
            <span class="padding4">美食描述</span>
            <textarea id="foodDesc" name="foodDesc" class="miaoshu"></textarea>
        </div>
        <div class="margin1 padding3"><span>参考价格</span>
            <input class="xinzeng" type="text" name="price" value=""/>
        </div>
        <div class="margin1 padding3"><span>位&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;置</span>
            <input class="xinzeng" type="text" name="address" value=""/>
        </div>

        <input type="submit" style="margin-left: 73px" class="tianjia" value="添加美食"/>
    </form>
</div>

</body>
</html>
