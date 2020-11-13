<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>游记详细内容展示</title>
    <link href="${pageContext.request.contextPath }/static/css/index.css" rel="stylesheet" type="text/css"/>
</head>
<body class="bg_color3 wid" style="margin-top:-60px;">
<div class="pagebody8">
    <c:if test="${not empty requestScope.notes}">
        <div id="suoshu">标题：<input type="text" id="notesTitle" name="notesTitle"
                                   value="${requestScope.notes.notesTitle}"/>
        </div>
    </c:if>
    <c:if test="${not empty  requestScope.notestype}">
        <div id="suoshu">类别：${requestScope.notestype.notestypeName}</div>
    </c:if>
    <c:if test="${not empty  requestScope.pic}">
        <div id="suoshu">图片：<img src="${requestScope.pic.picUrl}" alt="" id="myImage"/></div>
    </c:if>
    <div id="suoshu"><span>内容描述：</span></div>
    <textarea placeholder="" cols="50" rows="3" style="resize: none;margin-left:80px;margin-top:-20px;"
              disabled="disabled">${requestScope.notes.notesContent}
    </textarea>
</div>
<!-- </div> -->
</body>
</html>
