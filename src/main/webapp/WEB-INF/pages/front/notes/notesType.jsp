<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>游记类别</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/static/front/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/front/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/front/css/index.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/front/css/route-detail.css">
</head>
<body>
<%--引入头文件--%>
<%@ include file="/WEB-INF/pages/front/index/header.jsp" %>

<div class="bread_box">
    <a style="color: #0c7cb5">当前位置：</a>
    <a href="${pageContext.request.contextPath }/front/notes/notesList">发现好游记</a>
    <span> &gt;&gt;</span>
    <a href="${pageContext.request.contextPath }/front/notes/notesType?notestypeId=${requestScope.notestype.notestypeId}"
       style="color: #0c7cb5">${requestScope.notestype.notestypeName}</a>
</div>

<section class="hemai_jx">
    <div class="jx_top" style="margin-top: 10px">
        <div class="jx_tit">
            <img src="${pageContext.request.contextPath }/static/front/images/icon_6.jpg" alt="">
            <span>${requestScope.notestype.notestypeName}</span>
        </div>
    </div>
    <div class="heima_gn">
        <div class="guone_r_notes">
            <div class="row">
                <c:forEach items="${requestScope.notesByTypeIdList}" var="notes" begin="1" end="6">
                    <div class="col-md-3">
                        <a href="javascript:window.parent.location.href='${pageContext.request.contextPath  }/front/notes/notesInfo?notesId=${notes.notesId}'">
                            <img src="${pageContext.request.contextPath }/static/images/${notes.notesPic}"
                                 alt="${notes.notesId}">
                            <div class="has_border">
                                <h3>${notes.notesContent}</h3>
                                <h3 style="font-size: 12px; color: #1C76ED; float: right;"><span><fmt:formatDate
                                        value="${notes.updateDate}"
                                        pattern="yyyy-MM-dd"/></span></h3>
                                <div class="price">收藏<em>共</em><strong>${notes.praseCount}</strong><em>次</em></div>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</section>


<%@ include file="/WEB-INF/pages/front/index/footer.jsp" %>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${pageContext.request.contextPath }/static/front/js/jquery-3.3.1.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${pageContext.request.contextPath }/static/front/js/bootstrap.min.js"></script>
<!--导入布局js，共享header和footer-->
<script type="text/javascript" src="${pageContext.request.contextPath }/static/front/js/include.js"></script>
</body>
</html>
