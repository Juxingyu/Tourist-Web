<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>首页</title>
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath }/static/front/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/front/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/front/css/index.css">
</head>
<body>
<%@ include file="header.jsp" %>

<!-- 旅游 start-->
<section id="content">
    <!-- 精选start-->
    <section class="hemai_jx">
        <div class="jx_top">
            <div class="jx_tit">
                <img src="${pageContext.request.contextPath }/static/front/images/icon_5.jpg" alt="">
                <span>网站精选</span>
            </div>
            <!-- Nav tabs -->
            <ul class="jx_tabs" role="tablist">
                <li role="presentation" class="active">
                    <span></span>
                    <a href="#popularity" aria-controls="popularity" role="tab" data-toggle="tab">人气景点</a>
                </li>
                <li role="presentation">
                    <span></span>
                    <a href="#newest" aria-controls="newest" role="tab" data-toggle="tab">最新攻略</a>
                </li>
                <li role="presentation">
                    <span></span>
                    <a href="#theme" aria-controls="theme" role="tab" data-toggle="tab">主题游记</a>
                </li>
            </ul>
        </div>
        <div class="jx_content">
            <!-- Tab panes -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="popularity">
                    <div class="row">
                        <c:forEach items="${requestScope.sceReferList}" begin="1" end="4" var="sce">
                            <div class="col-md-3">
                                <a href="javascript:window.parent.location.href='${pageContext.request.contextPath  }/front/scenery/sceInfo?sceId=${sce.sceId}'">
                                    <div class="img" style="max-width: 275px; height: 183px; width: 275px;">
                                        <img src="${pageContext.request.contextPath }/static/images/${sce.secPic}"
                                             alt="" width="100px"/>
                                    </div>

                                    <div class="has_border" style="color: #0f0f10">
                                        <div style="margin: 10px 0 5px 0;">
                                                ${sce.secName}
                                            <span style="font-size: 12px; color: #1C76ED; float: right;margin-right: 10px">${sce.secLevel}</span>
                                        </div>

                                        <div style="font-size: 6px;height: 28px;display: inline-block;
                *display: inline;
                *zoom: 1;overflow: hidden;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${sce.secDesc}</div>
                                        <div class="price" style="padding-bottom: 7px">
                                            <h3 style="font-size: 12px; color: #1C76ED; margin: 0">
                                                <span>${sce.secSite}</span><span
                                                    style="float: right">${sce.openTime}</span>
                                            </h3>
                                                <%--<h3 style="font-size: 12px; color: #1C76ED; "></h3>--%>
                                                <%--收藏<em>共</em><strong>${sce.praseCount}</strong><em>次</em>--%>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane" id="newest">
                    <div class="row">
                        <c:forEach items="${requestScope.straReferList}" begin="1" end="4" var="stra">
                            <div class="col-md-3">
                                <a href="javascript:window.parent.location.href='${pageContext.request.contextPath  }/front/strategy/straInfo?straId=${stra.straId}'">
                                    <div class="img" style="max-width: 275px; height: 183px; width: 275px;">
                                        <img src="${pageContext.request.contextPath }/static/images/${stra.straPic}"
                                             alt="${stra.straId}"/></div>
                                    <div class="has_border">
                                        <h3>${stra.straTitle}</h3>
                                        <h3 style="font-size: 12px; color: #1C76ED; float: right;"><fmt:formatDate
                                                value="${stra.updateDate}"
                                                pattern="yyyy-MM-dd"/></h3>
                                        <div class="price">收藏<em>共</em><strong>${stra.praseCount}</strong><em>次</em>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane" id="theme">
                    <div class="row">
                        <c:forEach items="${requestScope.notesReferList}" begin="1" end="4" var="notes">
                            <div class="col-md-3">
                                <a href="javascript:window.parent.location.href='${pageContext.request.contextPath  }/front/notes/notesInfo?notesId=${notes.notesId}'">
                                    <div class="img" style="max-width: 275px; height: 183px; width: 275px;">
                                        <img src="${pageContext.request.contextPath }/static/images/${notes.notesPic}"
                                             alt="${notes.notesId}" width="100px"/></div>
                                    <div class="has_border">
                                        <h3>${notes.notesTitle}</h3>
                                        <h3 href="${pageContext.request.contextPath }/users/findUsersById?userId=${notes.userId}">发布人：${notes.petName}</h3>
                                        <div class="price">收藏<em>共</em><strong>${notes.praseCount}</strong><em>次</em>
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
    <!-- 精选end-->

    <!-- 美食专栏 start-->
    <section class="hemai_jx">
        <div class="jx_top">
            <div class="jx_tit">
                <img src="${pageContext.request.contextPath }/static/front/images/icon_6.jpg" alt="">
                <span>美食专栏</span>
            </div>
        </div>
        <div class="heima_gn">
            <div class="guone_r_notes">
                <div class="row">
                    <c:forEach items="${requestScope.foodList}" begin="1" end="8" var="food">
                        <div class="col-md-3">
                            <a href="javascript:window.parent.location.href='${pageContext.request.contextPath  }/front/food/foodInfo?id=${food.id}'">
                                <div class="img" style="max-width: 275px; height: 183px; width: 275px;">
                                    <img src="${pageContext.request.contextPath }/static/images/${food.pic}"
                                         alt=""></div>
                                <div class="has_border">
                                    <h3>${food.foodName}</h3>
                                    <h3 style="font-size: 12px; color: #1C76ED; float: right;">${food.address}</h3>
                                    <div class="price">美食价<em>￥</em><strong>${food.price}</strong><em
                                            style="color: #3F3F3F">仅供参考</em></div>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </section>
    <!-- 美食专栏 end-->


</section>
<!-- 旅游 end-->

<!--导入底部-->
<%@ include file="footer.jsp" %>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${pageContext.request.contextPath }/static/front/js/jquery-3.3.1.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${pageContext.request.contextPath }/static/front/js/bootstrap.min.js"></script>
<%--<!--导入布局js，共享header和footer-->--%>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/front/js/include.js"></script>
</body>
</html>