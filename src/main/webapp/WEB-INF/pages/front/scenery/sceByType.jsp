<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>风景类别</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/static/front/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/front/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/front/css/index.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/front/css/route-detail.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/front/css/search.css">
</head>
<body>
<%--引入头文件--%>
<%@ include file="/WEB-INF/pages/front/index/header.jsp" %>

<div class="bread_box" style="font-size: 17px;margin-top: 10px">
    <a style="color: #0c7cb5">当前位置：</a>
    <a href="${pageContext.request.contextPath }/front/scenery/sceList">发现好景点</a>
    <span> &gt;&gt;</span>
    <a href="${pageContext.request.contextPath }/front/scenery/sceType?scenictypeId=${requestScope.scenictype.scenictypeId}"
       style="color: #0c7cb5">${requestScope.scenictype.scenictpyeName}</a>
</div>

<section class="hemai_jx">
    <div class="jx_top" style="margin-top: 10px">
        <div class="jx_tit">
            <img src="${pageContext.request.contextPath }/static/front/images/icon_6.jpg" alt="">
            <span>${requestScope.scenictype.scenictpyeName}</span>
        </div>
    </div>
    <div class="heima_gn">
        <div class="guone_r_notes">
            <div class="row">
                <c:choose>
                    <c:when test="${empty requestScope.sceByTypeList}">
                        <h1 style="font-size: 30px;text-align: center;line-height: 300px"><strong>该类型下无相关景点</strong>
                        </h1>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${requestScope.sceByTypeList}" var="sce">
                            <div class="col-md-3">
                                <a href="javascript:window.parent.location.href='${pageContext.request.contextPath  }/front/scenery/sceInfo?sceId=${sce.sceId}'">
                                    <div class="img" style="max-width: 275px; height: 183px; width: 275px;">
                                        <img src="${pageContext.request.contextPath }/static/images/${sce.secPic}"
                                             alt="${sce.sceId}">
                                    </div>
                                    <div class="has_border">
                                        <h3>${sce.secName}</h3>
                                        <h3 style="font-size: 12px; color: #1C76ED; float: right;">${sce.secSite}</h3>
                                        <h3 style="font-size: 11px">${sce.secDesc}</h3>
                                        <div class="price">收藏<em>共</em><strong>${sce.praseCount}</strong><em>次</em>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <%--分页--%>
        <div class="page_num_inf">
            <i></i> 共 <span>${requestScope.page.pages}</span>页<span>${requestScope.page.total}</span>条
        </div>
        <div class="pageNum">
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath }/front/scenery/sceType?scenictypeId=${requestScope.scenictype.scenictypeId}&pageNo=1">首页</a>
                </li>
                <c:choose>
                    <c:when test="${requestScope.page.hasPreviousPage}">
                        <li class="threeword">
                            <a href="${pageContext.request.contextPath }/front/scenery/sceType?scenictypeId=${requestScope.scenictype.scenictypeId}&pageNo=${page.prePage}">上一页</a>
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
                                <a href="${pageContext.request.contextPath }/front/scenery/sceType?scenictypeId=${requestScope.scenictype.scenictypeId}&pageNo=${pageSize}&pageSize=8"
                                   style="background: #C30D23 none repeat scroll 0% 0%;color:#ffffff;">${pageSize}</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <a href="${pageContext.request.contextPath }/front/scenery/sceType?scenictypeId=${requestScope.scenictype.scenictypeId}&pageNo=${pageSize}&pageSize=8">${pageSize}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:choose>
                    <c:when test="${requestScope.page.hasNextPage}">
                        <li class="threeword"><a
                                href="${pageContext.request.contextPath }/front/scenery/sceType?scenictypeId=${requestScope.scenictype.scenictypeId}&pageNo=${page.nextPage}">下一页</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="threeword" style="pointer-events:none;background-color:#F7F7F6"><a>下一页</a></li>
                    </c:otherwise>
                </c:choose>
                <li class="threeword">
                    <a href="${pageContext.request.contextPath }/front/scenery/sceType?scenictypeId=${requestScope.scenictype.scenictypeId}&pageNo=${page.pages}">末页</a>
                </li>
            </ul>
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
