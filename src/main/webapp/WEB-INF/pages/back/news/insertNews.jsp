<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="${pageContext.request.contextPath }/static/css/index.css" rel="stylesheet" type="text/css"/>
    <title>添加新闻资讯</title>
</head>
<body style="background:#efeeec;">
<div class="pagebody6" style="padding:0px;">
    <form action="${pageContext.request.contextPath }/news/insertNews" enctype="multipart/form-data"
          method="post">
        <h1 style="font-size:30px;"><span style="margin-left:150px">添加新闻资讯</span></h1>
        <div class="margin1 padding3"><span>资讯标题</span>
            <input type="text" id="newsTitle" class="xinzeng" name="newsTitle"/>
        </div>
        <%--<div class="margin1 padding3"><span>新闻图片</span>--%>
        <%--<input class="xinzeng" type="file" name="newsPic" id="newsPic" value="选择上传"/>--%>
        <%--</div>--%>
        <div class="margin1 padding3">
            <span class="padding4">资讯内容</span>
            <textarea id="newsContext" name="newsContext" class="miaoshu"></textarea>
        </div>
        <input type="submit" class="tianjia" value="添加资讯"/>
    </form>
</div>
</body>
</html>
