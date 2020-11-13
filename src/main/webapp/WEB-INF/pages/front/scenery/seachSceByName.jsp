<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>景点搜索结果展示</title>
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

<div class="page_one">
    <div class="contant">
        <div class="crumbs">
            <img src="${pageContext.request.contextPath }/static/front/images/search.png" alt="">
            <p>旅游景点><span>搜索结果</span></p>
        </div>
        <div class="xinxi clearfix">
            <!--搜索结果展示-->
            <div class="left">
                <c:choose>
                    <c:when test="${empty requestScope.sceList}">
                        <h1 style="font-size: 30px;text-align: center;line-height: 460px"><strong>未检测到与输入相关内容</strong>
                        </h1>
                    </c:when>
                    <c:otherwise>
                        <ul>
                            <c:forEach items="${requestScope.sceList}" var="sce">
                                <li>
                                    <div class="img">
                                        <img src="${pageContext.request.contextPath }/static/images/${sce.secPic}"
                                             width="300px" height="170px"
                                             alt="${sce.sceId}">
                                    </div>
                                    <div class="text1">
                                        <p>【${sce.secName}】</p>
                                        <br/>
                                        <p style="line-height: 26px">景点级别：${sce.secLevel}</p>
                                        <p style="line-height: 26px">开放时间：${sce.openTime}</p>
                                        <p style="line-height: 26px">所在地址：${sce.secSite}</p>
                                        <br/>
                                            <%--<p>${sce.secDesc}</p>--%>
                                    </div>
                                    <div class="price">
                                        <p class="price_num" style="font-size: 14px">
                                    <span>
                                        发布时间：<fmt:formatDate value="${sce.updateDate}"
                                                             pattern="yyyy-MM-dd"/>
                                    </span>
                                        </p>
                                        <p>
                                            <a href="${pageContext.request.contextPath }/front/scenery/sceInfo?sceId=${sce.sceId}">查看详情</a>
                                        </p>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:otherwise>
                </c:choose>
                <c:if test="${requestScope.page.pages>1}">
                    <!--数据分页-->
                    <div class="page_num_inf">
                        <i></i> 共 <span>${requestScope.page.pages}</span>页<span>${requestScope.page.total}</span>条
                    </div>

                    <div class="pageNum">
                        <ul>
                            <li><a href="${pageContext.request.contextPath }/front/scenery/seachByName?pageNo=1">首页</a>
                            </li>
                            <c:choose>
                                <c:when test="${requestScope.page.hasPreviousPage}">
                                    <li class="threeword"><a
                                            href="${pageContext.request.contextPath }/front/scenery/seachByName?pageNo=${page.prePage}">上一页</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="threeword" style="pointer-events:none;background-color:#F7F7F6">
                                        <a>上一页</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                            <c:forEach begin="1" end="${requestScope.page.pages}" var="pageSize">
                                <c:choose>
                                    <c:when test="${pageSize==page.pageNum}">
                                        <li>
                                            <a href="${pageContext.request.contextPath }/front/scenery/seachByName?pageNo=${pageSize}&pageSize=6"
                                               style="background: #C30D23 none repeat scroll 0% 0%;color:#ffffff;">${pageSize}</a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li>
                                            <a href="${pageContext.request.contextPath }/front/scenery/seachByName?pageNo=${pageSize}&pageSize=6">${pageSize}</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <c:choose>
                                <c:when test="${requestScope.page.hasNextPage}">
                                    <li class="threeword"><a
                                            href="${pageContext.request.contextPath }/front/scenery/seachByName?pageNo=${page.nextPage}">下一页</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="threeword" style="pointer-events:none;background-color:#F7F7F6">
                                        <a>下一页</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                            <li class="threeword">
                                <a href="${pageContext.request.contextPath }/front/scenery/seachByName?pageNo=${page.pages}">末页</a>
                            </li>
                        </ul>
                    </div>
                </c:if>
            </div>

            <!--相关推荐-->
            <div class="right">
                <div class="top">
                    <div class="hot">HOT</div>
                    <span>景点相关推荐</span>
                </div>
                <ul>
                    <c:forEach items="${requestScope.sceReferList}" var="sce" begin="1" end="5">
                        <li>
                            <a href="javascript:window.parent.location.href='${pageContext.request.contextPath  }/front/scenery/sceInfo?sceId=${sce.sceId}'">
                                <div class="left">
                                    <img src="${pageContext.request.contextPath }/static/images/${sce.secPic}"
                                         alt="" style="width: 100%">
                                </div>
                                <div class="right">
                                    <p>${sce.secName}</p>
                                    <p style="color: #0c7cb5;font-size: 10px">${sce.secSite}</p>
                                    <p style="font-size: 10px">收藏共<span
                                            style="font-size: 10px"><span>${sce.praseCount}</span></span>次
                                    </p>
                                </div>
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>


<%--引入尾部--%>
<%@ include file="/WEB-INF/pages/front/index/footer.jsp" %>

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${pageContext.request.contextPath }/static/front/js/bootstrap.min.js"></script>
<!--导入布局js，共享header和footer-->
<script type="text/javascript" src="${pageContext.request.contextPath }/static/front/js/include.js"></script>
</body>
</html>