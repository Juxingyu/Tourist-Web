<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="${pageContext.request.contextPath }/static/css/index.css" rel="stylesheet" type="text/css"/>
    <title>修改美食信息</title>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery.min.js"></script>
    <c:if test="${requestScope.i>0}">
        <script type="text/javascript">
            alert("修改成功！")
            window.location.href = "${pageContext.request.contextPath }/food/foodList";
        </script>
    </c:if>
</head>
<body style="background:#efeeec;">
<div class="pagebody6" style="padding:0px;">
    <form action="${pageContext.request.contextPath }/food/updateFood"
          method="post">
        <h1 style="font-size:30px;"><span style="margin-left:130px">修改美食信息</span></h1>
        <div hidden="hidden">
            <input type="text" name="id" value="${requestScope.foodById.id}" readonly="readonly"/>
        </div>
        <div class="margin1 padding3"><span>美食名称</span>
            <input type="text" class="xinzeng" id="foodName" name="foodName" value="${requestScope.foodById.foodName}"
                   readonly="readonly"/>
        </div>
        <div class="margin1 padding3"><span>地理位置</span>
            <input type="text" id="address" class="xinzeng" name="address" value="${requestScope.foodById.address}"/>
        </div>
        <div class="margin1 padding3"><span>参考价格</span>
            <input type="text" id="price" class="xinzeng" name="price" value="${requestScope.foodById.price}"/>
        </div>
        <%--<div class="margin1 padding3">--%>
        <%--<span class="padding4">景点简介</span>--%>
        <%--<textarea id="secDesc" name="secDesc" class="miaoshu">${requestScope.sceById.secDesc}</textarea>--%>
        <%--</div>--%>
        <input type="submit" style="margin-left: 73px" class="tianjia" value="修改美食信息"/>
    </form>
</div>
</body>
</html>
