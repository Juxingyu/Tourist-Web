<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="${pageContext.request.contextPath }/static/css/index.css" rel="stylesheet" type="text/css"/>
    <title>修改资讯信息</title>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery.min.js"></script>
    <c:if test="${requestScope.i>0}">
        <script type="text/javascript">
            alert("修改成功！")
            window.location.href = "${pageContext.request.contextPath }/news/newsList";
        </script>
    </c:if>
</head>
<body style="background:#efeeec;">
<div class="pagebody6" style="padding:0px;">
    <form action="${pageContext.request.contextPath }/news/updateNews"
          method="post">
        <h1 style="font-size:30px;"><span style="margin-left:130px">修改资讯信息</span></h1>
        <div hidden="hidden">
            <input type="text" id="newsId" name="newsId" value="${requestScope.newsById.newsId}" readonly="readonly"/>
        </div>
        <div class="margin1 padding3"><span>资讯标题</span>
            <input type="text" class="xinzeng" id="newsTitle" name="newsTitle"
                   value="${requestScope.newsById.newsTitle}"
            />
        </div>
        <div class="margin1 padding3">
            <span class="padding4">资讯内容</span>
            <textarea id="newsContext" name="newsContext"
                      class="miaoshu">${requestScope.newsById.newsContext}</textarea>
        </div>
        <input type="submit" style="margin-left: 73px" class="tianjia" value="修改资讯信息"/>
    </form>
</div>
</body>
</html>
