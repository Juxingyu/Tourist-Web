<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>公告列表</title>
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
        </div>
        <div class="xinxi clearfix">

            <!--旅游线路、结果展示-->
            <div class="leftttt">

                <div class="header">
                    <span>最新资讯</span>
                </div>
                <ul>
                    <c:forEach items="${requestScope.newsList}" var="news" begin="1">
                        <li>
                            <div class="img"><img
                                    src="${pageContext.request.contextPath }/static/images/${news.newsPic}"
                                    alt="" style="width: 150px">
                            </div>
                            <div class="text1">
                                <p>【${news.newsTitle}】</p>
                                <br/>
                                <p>${news.newsContext}</p>
                            </div>
                            <div class="price">
                                <p class="price_num">
                                    <span><fmt:formatDate value="${news.updateDate}"
                                                          pattern="yyyy-MM-dd"/></span>
                                </p>
                                <p>
                                    <a href="${pageContext.request.contextPath }/front/news/newsInfo?newsId=${news.newsId}">查看详情</a>
                                </p>
                            </div>
                        </li>
                    </c:forEach>
                </ul>

                <!--数据分页-->
                <div class="page_num_inf">
                    <i></i> 共 <span>${requestScope.page.pages}</span>页<span>${requestScope.page.total}</span>条
                </div>
                <div class="pageNum">
                    <ul>
                        <li><a href="${pageContext.request.contextPath }/front/news/newsList?pageNo=1">首页</a>
                        </li>
                        <c:choose>
                            <c:when test="${requestScope.page.hasPreviousPage}">
                                <li class="threeword"><a
                                        href="${pageContext.request.contextPath }/front/news/newsList?pageNo=${page.prePage}">上一页</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="threeword" style="pointer-events:none;background-color:#F7F7F6"><a>上一页</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach begin="1" end="${requestScope.page.pages}" var="pageSize">
                            <c:choose>
                                <c:when test="${pageSize==page.pageNum}">
                                    <li>
                                        <a href="${pageContext.request.contextPath }/front/news/newsList?pageNo=${pageSize}&pageSize=6"
                                           style="background: #C30D23 none repeat scroll 0% 0%;color:#ffffff;">${pageSize}</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li>
                                        <a href="${pageContext.request.contextPath }/front/news/newsList?pageNo=${pageSize}&pageSize=6">${pageSize}</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${requestScope.page.hasNextPage}">
                                <li class="threeword"><a
                                        href="${pageContext.request.contextPath }/front/news/newsList?pageNo=${page.nextPage}">下一页</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="threeword" style="pointer-events:none;background-color:#F7F7F6"><a>下一页</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                        <li class="threeword">
                            <a href="${pageContext.request.contextPath }/front/news/newsList?pageNo=${page.pages}">末页</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>


<%--引入尾部--%>
<%@ include file="/WEB-INF/pages/front/index/footer.jsp" %>

<script src="${pageContext.request.contextPath }/static/front/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath }/static/front/js/jquery.min.js"></script>
<!--导入布局js，共享header和footer-->
<script type="text/javascript" src="${pageContext.request.contextPath }/static/front/js/include.js"></script>
</body>
</html>
