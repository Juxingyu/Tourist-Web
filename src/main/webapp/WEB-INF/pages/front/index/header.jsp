<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>header</title>
    <script src="${pageContext.request.contextPath }/static/back_1/js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $(".navli").click(function () {
                // $(this).addClass("nav-active");
                $(this).toggleClass("nav-active");
            });
        });

        function sou() {
            var s = $("#optionId").val();
            var val = $("#sousuoheader").val();
            window.parent.location.href = s + val;
        }
    </script>
    <style type="text/css">
        .login .btn {
            padding: 0;
            color: red;
            background-color: #FFFFFF;
            font-size: 13px;
        }

        .dropdown {
            float: right;
            margin: 7px 117px 0 0;
        }
    </style>
</head>
<body>
<!--引入头部-->
<%--<div id="header">--%>
<%--头部--%>
<div class="top_banner">
    <img src="${pageContext.request.contextPath }/static/front/images/top_banner.jpg" alt="">
</div>
<div class="shortcut">
    <c:choose>
        <c:when test="${sessionScope.users.petName!=null}">
            <!-- 登录状态 -->
            <div class="login">
                <span>欢迎回来，</span>
                <div class="dropdown">
                    <button type="button" class="btn dropdown-toggle" id="dropdownMenu1"
                            data-toggle="dropdown">${sessionScope.users.petName}
                    </button>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                        <li role="presentation">
                            <a role="menuitem" tabindex="-1"
                               href="${pageContext.request.contextPath }/front/collect/toCollect">我的收藏</a>
                        </li>
                        <li role="presentation">
                            <a role="menuitem" tabindex="-1"
                               href="${pageContext.request.contextPath }/front/notes/notesListByUserId">我的游记</a>
                        </li>
                        <li role="presentation">
                            <a role="menuitem" tabindex="-1"
                               href="${pageContext.request.contextPath }/users/toFrontUpdatePW">修改密码</a>
                        </li>
                        <li role="presentation">
                            <a role="menuitem" tabindex="-1"
                               href="${pageContext.request.contextPath }/users/usersInfo">个人信息</a>
                        </li>
                    </ul>
                </div>
                <a style="float: left; position: absolute; right: 401px;top: 128px; font-size: 12px;color: #0f0f10"
                   onclick="javascript:if(confirm('您确定要退出吗？')){window.parent.location.href='${pageContext.request.contextPath  }/front/login/logout'}">退出</a>
            </div>
        </c:when>
        <c:otherwise>
            <!-- 未登录状态 -->
            <div class="login_out">
                <a href="${pageContext.request.contextPath }/front/login/tologin">登录</a>
                <a href="${pageContext.request.contextPath }/front/login/toRegister">免费注册</a>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<div class="header_wrap">
    <div class="topbar">
        <div class="logo">
            <a href="${pageContext.request.contextPath }/front/index/index"
               style="font-size: 30px;line-height: 55px;color: #337AB7"><strong>爱途旅游分享网</strong></a>
        </div>
        <div class="search">
            <select class="dropdown sele" id="optionId" name="scenictypeId">
                <option class="opt" value="${pageContext.request.contextPath  }/front/scenery/seachByName?secName=">
                    旅游景点
                </option>
                <option class="opt" value="${pageContext.request.contextPath  }/front/strategy/seachByName?straTitle=">
                    旅游攻略
                </option>
                <option class="opt" value="${pageContext.request.contextPath  }/front/notes/seachByName?notesName=">
                    旅游游记
                </option>
            </select>
            <input name="" type="text" id="sousuoheader" placeholder="请输入名称" class="search_input" autocomplete="off">
            <a href="javascript:sou();"
               class="search-button">搜索</a>
        </div>
        <div class="hottel">
            <div class="hot_pic">
                <img src="${pageContext.request.contextPath }/static/front/images/hot_tel.jpg" alt="客服热线">
            </div>
            <div class="hot_tel">
                <p class="hot_time">客服热线(9:00-6:00)</p>
                <p class="hot_num">400-000-0000</p>
            </div>
        </div>
    </div>
</div>
<%--头部结束--%>
<%--导航开始--%>
<!-- 首页导航 -->
<div class="navitem">
    <ul class="nav">
        <li>
            <a href="${pageContext.request.contextPath }/front/index/index">首页</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath }/front/scenery/sceList">发现好景点</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath }/front/strategy/strategyList">发现好攻略</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath }/front/food/foodList">发现好美食</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath }/front/notes/notesList">发现好游记</a>
        </li>
        <c:forEach items="${sessionScope.users.funsList }" var="fun">
            <li><a
                    href="${pageContext.request.contextPath }${fun.funUrl}">${fun.funName}</a></li>
        </c:forEach>
    </ul>
</div>
<%--导航结束--%>
<%--</div>--%>

</body>
</html>
