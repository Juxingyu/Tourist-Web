<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>景点详情</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/static/front/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/front/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/front/css/route-detail.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/front/css/search.css">
    <script src="${pageContext.request.contextPath }/static/front/js/jquery-3.3.1.js"></script>
    <style type="text/css">
        p {
            line-height: 40px;
        }
    </style>
</head>
<body>
<%--引入头文件--%>
<%@ include file="/WEB-INF/pages/front/index/header.jsp" %>


<div class="page_one">
    <div class="contant">
        <div class="crumbs">
        </div>
        <div class="xinxi clearfix">
            <!--结果展示-->
            <div class="leftttt">

                <div class="header">
                    <span>${requestScope.sceById.secName}</span>
                </div>
                <div style="text-align: center;font-size: small">
                    开放时间：${sceById.openTime}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;景点级别：${sceById.secLevel}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发布时间：<span><fmt:formatDate
                        value="${sceById.updateDate}"
                        pattern="yyyy-MM-dd HH:mm:ss"/>
                </div>
                <br><br>
                <div class="img" align="center"><img
                        src="${pageContext.request.contextPath }/static/images/${sceById.secPic}"
                        alt="" style="width: 50%;">
                </div>
                <br><br>
                <div class="text1">
                    <p>${sceById.secDesc}</p>
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
