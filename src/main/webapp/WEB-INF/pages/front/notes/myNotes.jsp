<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>我的游记</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/static/front/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/front/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/front/css/index.css">
    <style>
        .tab-content .row > div {
            margin-top: 16px;
        }

        .tab-content {
            margin-bottom: 36px;
        }
    </style>
    <script src="${pageContext.request.contextPath }/static/front/js/jquery-3.3.1.js"></script>
</head>
<body>
<%--引入头文件--%>
<%@ include file="/WEB-INF/pages/front/index/header.jsp" %>

<section id="content">
    <section class="hemai_jx">
        <div class="jx_top">
            <div class="jx_tit">
                <img src="${pageContext.request.contextPath }/static/front/images/icon_5.jpg" alt="">
                <span>我的游记</span>
            </div>
        </div>
        <div class="jx_content">
            <!-- Tab panes -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="popularity">
                    <div class="row">
                        <c:forEach items="${requestScope.notesByUsersId}" var="notes">
                            <div class="col-md-3">
                                <a href="javascript:window.parent.location.href='${pageContext.request.contextPath  }/front/notes/notesInfo?notesId=${notes.notesId}'">
                                    <div class="img" style="max-width: 275px; height: 183px; width: 275px;">
                                        <img src="${pageContext.request.contextPath }/static/images/${notes.notesPic}"
                                             alt="${notes.notesTitle}" width="100px"/>
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
                </div>
            </div>
        </div>
    </section>
</section>

<%--引入尾部--%>
<%@ include file="/WEB-INF/pages/front/index/footer.jsp" %>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${pageContext.request.contextPath }/static/front/js/jquery-3.3.1.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${pageContext.request.contextPath }/static/front/js/bootstrap.min.js"></script>
<!--导入布局js，共享header和footer-->
<script type="text/javascript" src="${pageContext.request.contextPath }/static/front/js/include.js"></script>
</body>
</html>
