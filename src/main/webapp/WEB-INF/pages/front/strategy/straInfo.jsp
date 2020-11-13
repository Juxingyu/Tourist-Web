<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>攻略信息详情</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/static/front/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/front/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/front/css/route-detail.css">
    <link href="${pageContext.request.contextPath }/static/front/css/comment.css" rel="stylesheet"
          type="text/css">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${pageContext.request.contextPath }/static/front/js/jquery-3.3.1.js"></script>
    <script type="text/javascript">
        // 提交评论
        function sendComment() {
            var v = document.getElementById("input-38");
            var comcontent = v.value;
            var straId =${requestScope.strategy.straId};
            $.ajax({
                url: "${pageContext.request.contextPath }/front/comment/insertStraComment",    //请求的url地址
                async: true,//请求是否异步，默认为异步，这也是ajax重要特性
                data: {'commentContent': comcontent, 'straId': straId},    //参数值
                type: "post",  //请求方式
                success: function (data) {
                    if (data === "1") {
                        alert("评论失败！");
                    }
                    if (data === "0") {
                        alert("发表成功");
                        window.location.href
                            = "${pageContext.request.contextPath }/front/strategy/straInfo?straId=" + straId;
                    }
                },
            });
        }

        //收藏
        function collect() {
            var straId =${requestScope.strategy.straId};
            $.ajax({
                url: "${pageContext.request.contextPath }/front/collect/addStraCollect",    //请求的url地址
                async: true,//请求是否异步，默认为异步，这也是ajax重要特性
                data: {'straId': straId},    //参数值
                type: "post",  //请求方式
                success: function (data) {
                    if (data === "1") {
                        alert("收藏失败！请重试");
                    }
                    if (data === "0") {
                        alert("收藏成功");
                        window.location.href
                            = "${pageContext.request.contextPath }/front/strategy/straInfo?straId=" + straId;
                    }
                },
            });
        }
    </script>
</head>
<body>
<%--引入头文件--%>
<%@ include file="/WEB-INF/pages/front/index/header.jsp" %>

<div class="bread_box">
    <a href="${pageContext.request.contextPath }/front/strategy/strategyList">发现好攻略</a>
    <span> &gt;</span>
    <a href="${pageContext.request.contextPath }/front/strategy/straType?stratypeId=${requestScope.strategytype.stratypeId}">${requestScope.strategytype.stratypeName}</a><span> &gt;</span>
    <a href="#" style="color: #0c7cb5">${requestScope.strategy.straTitle}</a>
</div>
<div class="prosum_box">
    <div class="prosum_left">
        <img src="${pageContext.request.contextPath }/static/images/${requestScope.strategy.straPic}" alt="">
    </div>
    <div class="prosum_right">
        <p class="pros_title" style="border-bottom: 4px solid #046DB6">${requestScope.strategy.straTitle}</p>
        <div class="pros_other">
            <div id="secDesc" style="display: inline-block;
                *display: inline;
                *zoom: 1;
                width: 410px;
                height:156px;
                /*line-height: 80px;*/
                font-size: 16px;
                overflow: hidden;
                /*-ms-text-overflow: ellipsis;*/
                /*text-overflow: ellipsis;*/
                /*white-space: nowrap;*/
                "><span style="color: #8c8c8c">攻略详情：</span>${requestScope.strategy.straContext}</div>
            <br>
            ...<a
                href="${pageContext.request.contextPath }/front/strategy/toStraDail?straId=${requestScope.strategy.straId}">[查看更多]</a>
            <br><br>
        </div>


        <%--收藏--%>
        <div class="pros_price">
            <p class="collect">
                <%--为收藏过--%>
                <c:choose>
                    <c:when test="${requestScope.collectByUIdAndStraId}"><%--已经收藏--%>
                        <a class="btn already" disabled="disabled"><i
                                class="glyphicon glyphicon-heart-empty"></i>已收藏</a>
                        <span>已收藏${requestScope.strategy.praseCount}次</span>
                    </c:when>

                    <c:otherwise>
                        <c:choose>
                            <c:when test="${empty sessionScope.users}">
                                <span>已收藏${requestScope.strategy.praseCount}次</span>
                            </c:when>
                            <c:otherwise>
                                <a class="btn" onclick="collect()"><i
                                        class="glyphicon glyphicon-heart-empty"></i>点击收藏</a>
                                <span>已收藏${requestScope.strategy.praseCount}次</span>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
            </p>
        </div>
    </div>
</div>


<%--评论--%>
<div class="you_need_konw">
    <span>评论区</span>
    <div class="notice">
        <!---评论区------>
        <div class="row">
            <div class="col-md-9">
                <c:choose>
                    <c:when test="${empty requestScope.straComment}">
                        <h3>暂无评论</h3>
                    </c:when>
                    <c:otherwise>
                        <ul class="media-list">
                            <c:forEach items="${requestScope.straComment}" var="stra">
                                <c:forEach items="${requestScope.usersCom}" var="users">
                                    <li class="media">
                                        <div class="media-left">
                                            <a href="#">
                                                <img class="media-object img-circle"
                                                     src="${pageContext.request.contextPath }/static/images/${users.pic}"
                                                     alt="广丰">
                                            </a>
                                        </div>
                                        <div class="media-body">
                                            <h4 class="media-heading">${users.petName}<span style="float: right"
                                                                                            class="ds-time"
                                                                                            datetime="${stra.updateDate}"
                                                                                            title="2016年1月28日 下午8:13:29"><fmt:formatDate
                                                    value="${stra.updateDate}"
                                                    pattern="yyyy-MM-dd HH:mm:ss"/></span></h4>

                                            <p>${stra.commentContent}</p>

                                            <div class="ds-comment-footer">
                                            </div>
                                            <hr/>
                                        </div>
                                    </li>
                                </c:forEach>
                                <hr/>
                            </c:forEach>
                        </ul>
                        <hr/>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${empty sessionScope.users}">
                    </c:when>
                    <c:otherwise>
                        <div class="form-group">
        <span class="input input--isao">
        <input placeholder="评论内容" class="input__field input__field--isao" type="text" id="input-38"
               value="">
        <label class="input__label input__label--isao" for="input-38">
        <span class="input__label-content input__label-content--isao"></span>
        </label>
        <a id="addcommentbutton" type="button" class="btn btn-primary btn-block ladda-button" title=""
           data-style="expand-right" data-original-title="评论" onclick="sendComment()">
        <span class="ladda-label">
        提交评论
        </span>
        </a>
        </span>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="col-md-3"></div>
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
