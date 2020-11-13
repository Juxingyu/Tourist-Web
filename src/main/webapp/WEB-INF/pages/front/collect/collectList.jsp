<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>我的收藏</title>
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
                <span>我的收藏</span>
            </div>
            <!-- Nav tabs -->
            <ul class="jx_tabs" role="tablist">
                <li role="presentation" class="active">
                    <span></span>
                    <a href="#popularity" aria-controls="popularity" role="tab" data-toggle="tab">景点收藏</a>
                </li>
                <li role="presentation">
                    <span></span>
                    <a href="#newest" aria-controls="newest" role="tab" data-toggle="tab">攻略收藏</a>
                </li>
                <li role="presentation">
                    <span></span>
                    <a href="#theme" aria-controls="theme" role="tab" data-toggle="tab">游记收藏</a>
                </li>
            </ul>
        </div>
        <div class="jx_content">
            <!-- Tab panes -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="popularity">
                    <div class="row">
                        <c:choose>
                            <c:when test="${empty requestScope.allSce}">
                                <h1 style="font-size: 30px;text-align: center;line-height: 460px">
                                    <strong>您还有收藏任何景点信息</strong></h1>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${requestScope.allSce}" var="sce">
                                    <div class="col-md-3">
                                        <a href="javascript:window.parent.location.href='${pageContext.request.contextPath  }/front/scenery/sceInfo?sceId=${sce.sceId}'">
                                            <div class="img" style="max-width: 275px; height: 183px; width: 275px;">
                                                <img src="${pageContext.request.contextPath }/static/images/${sce.secPic}"
                                                     alt="${sce.secName}" width="100px"/>
                                            </div>
                                            <div class="has_border">
                                                <h3>${sce.secDesc}</h3>
                                                <div class="price">
                                                    收藏<em>共</em><strong>${sce.praseCount}</strong><em>次</em>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane" id="newest">
                    <div class="row">
                        <c:choose>
                            <c:when test="${empty requestScope.allStra}">
                                <h1 style="font-size: 30px;text-align: center;line-height: 460px">
                                    <strong>您还有收藏任何攻略信息</strong></h1>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${requestScope.allStra}" var="stra">
                                    <div class="col-md-3">
                                        <a href="javascript:window.parent.location.href='${pageContext.request.contextPath  }/front/strategy/straInfo?straId=${stra.straId}'">
                                            <div class="img" style="max-width: 275px; height: 183px; width: 275px;">
                                                <img src="${pageContext.request.contextPath }/static/images/${stra.straPic}"
                                                     alt="${stra.straId}"/>
                                            </div>
                                            <div class="has_border">
                                                <h3>${stra.straTitle}</h3>
                                                <div class="price">
                                                    收藏<em>共</em><strong>${stra.praseCount}</strong><em>次</em>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane" id="theme">
                    <div class="row">
                        <c:choose>
                            <c:when test="${empty requestScope.allNotes}">
                                <h1 style="font-size: 30px;text-align: center;line-height: 460px">
                                    <strong>您还有收藏任何游记信息</strong></h1>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${requestScope.allNotes}" var="notes">
                                    <div class="col-md-3">
                                        <a href="javascript:window.parent.location.href='${pageContext.request.contextPath  }/front/notes/notesInfo?notesId=${notes.notesId}'">
                                            <div class="img" style="max-width: 275px; height: 183px; width: 275px;">
                                                <img src="${pageContext.request.contextPath }/static/images/${notes.notesPic}"
                                                     alt="${notes.notesId}" width="100px"/>
                                            </div>
                                            <div class="has_border">
                                                <h3>${notes.notesTitle}</h3>
                                                <div class="price">
                                                    收藏<em>共</em><strong>${notes.praseCount}</strong><em>次</em>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
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
