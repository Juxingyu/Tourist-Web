<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>游记列表展示</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/static/front/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/front/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/front/css/index.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/front/css/route-detail.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/front/css/search.css">
    <script src="${pageContext.request.contextPath }/static/front/js/jquery-3.3.1.js"></script>
</head>
<body>
<%--引入头文件--%>
<%@ include file="/WEB-INF/pages/front/index/header.jsp" %>

<%--主体内容--%>
<section class="hemai_jx">
    <div class="jx_top">
        <div class="jx_tit">
            <img src="${pageContext.request.contextPath }/static/front/images/icon_6.jpg" alt="">
            <span>发现好游记</span>
        </div>
        <%--<div class="sousuo" style="float: right;">
            <input name="sousuo" id="sousuo" type="text" placeholder="请输入游记名称" class="search_input"
                   value="">
            <a href="javascript:window.parent.location.href='${pageContext.request.contextPath  }/front/notes/seachByName?notesName='+document.getElementById('sousuo').value+'';"
               class="search-button">搜索</a>
        </div>--%>
    </div>
    <div class="heima_gn">
        <div class="guone_r_notes">
            <div class="row">
                <c:forEach items="${requestScope.NotesList}" var="notes">
                    <div class="col-md-3">
                        <a href="javascript:window.parent.location.href='${pageContext.request.contextPath  }/front/notes/notesInfo?notesId=${notes.notesId}'">

                            <div class="img" style="max-width: 275px; height: 183px; width: 275px;">
                                <img src="${pageContext.request.contextPath }/static/images/${notes.notesPic}"
                                     alt="${notes.notesId}">
                            </div>
                            <div class="has_border">
                                <h3>${notes.notesTitle}</h3>
                                <div class="price" style="padding-bottom: 7px;color: #1C76ED">
                                    <h3 style="font-size: 12px; color: #1C76ED; margin: 0">
                                        <span>${notes.notesLocation}</span><span
                                            style="float: right"><fmt:formatDate
                                            value="${notes.updateDate}"
                                            pattern="yyyy-MM-dd"/></span>
                                    </h3>

                                </div>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </div>


            <%--分页--%>
            <div class="page_num_inf">
                <i></i> 共 <span>${requestScope.page.pages}</span>页<span>${requestScope.page.total}</span>条
            </div>
            <div class="pageNum">
                <ul>
                    <li><a href="${pageContext.request.contextPath }/front/notes/notesList?pageNo=1">首页</a>
                    </li>
                    <c:choose>
                        <c:when test="${requestScope.page.hasPreviousPage}">
                            <li class="threeword"><a
                                    href="${pageContext.request.contextPath }/front/notes/notesList?pageNo=${page.prePage}">上一页</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="threeword" style="pointer-events:none;background-color:#F7F7F6"><a>上一页</a></li>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach begin="1" end="${requestScope.page.pages}" var="pageSize">
                        <c:choose>
                            <c:when test="${pageSize==page.pageNum}">
                                <li>
                                    <a href="${pageContext.request.contextPath }/front/notes/notesList?pageNo=${pageSize}&pageSize=8"
                                       style="background: #C30D23 none repeat scroll 0% 0%;color:#ffffff;">${pageSize}</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="${pageContext.request.contextPath }/front/notes/notesList?pageNo=${pageSize}&pageSize=8">${pageSize}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${requestScope.page.hasNextPage}">
                            <li class="threeword"><a
                                    href="${pageContext.request.contextPath }/front/notes/notesList?pageNo=${page.nextPage}">下一页</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="threeword" style="pointer-events:none;background-color:#F7F7F6"><a>下一页</a></li>
                        </c:otherwise>
                    </c:choose>
                    <li class="threeword">
                        <a href="${pageContext.request.contextPath }/front/notes/notesList?pageNo=${page.pages}">末页</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</section>


<%--引入尾部--%>
<%@ include file="/WEB-INF/pages/front/index/footer.jsp" %>

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${pageContext.request.contextPath }/static/front/js/bootstrap.min.js"></script>
<!--导入布局js，共享header和footer-->
<script type="text/javascript" src="${pageContext.request.contextPath }/static/front/js/include.js"></script>
</body>
</html>
