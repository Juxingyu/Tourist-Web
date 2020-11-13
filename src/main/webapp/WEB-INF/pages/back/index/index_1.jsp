<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <title>爱途旅游分享网</title>
    <link rel="icon" href="${pageContext.request.contextPath }/static/back_1/img/favicon.ico"/>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name=viewport content="width=device-width, initial-scale=1"/>
    <link href="${pageContext.request.contextPath }/static/back_1/semantic/dist/semantic.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/static/back_1/plugins/ionicons/css/ionicons.min.css"
          rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/static/back_1/css/main.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/static/back_1/plugins/datepicker/css/bootstrap-datepicker.min.css"
          rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/static/back_1/plugins/weather-icons/css/weather-icons-wind.min.css"
          rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/static/back_1/plugins/weather-icons/css/weather-icons.min.css"
          rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/static/back_1/plugins/chartist/chartist.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/static/back_1/css/chat-page.css" rel="stylesheet"/>
    <link rel="shortcut icon" href="${pageContext.request.contextPath }/static/back_1/img/favicon.ico"/>
</head>

<body>
<div id="contextWrap">
    <!--sidebar 左侧导航-->
    <div class="ui sidebar vertical left menu overlay  borderless visible sidemenu inverted  grey"
         style="-webkit-transition-duration: 0.1s; transition-duration: 0.1s;" data-color="grey">
        <a class="item logo" href="${pageContext.request.contextPath }/back/index/index"
           style="font-size: 20px;color: #0f0f10;"><strong>爱途旅游分享网</strong>
        </a>

        <c:forEach items="${sessionScope.admin.funsList }" var="fun">
            <div class="ui accordion inverted">
                <a class="title item">
                    <i class="ion-speedometer titleIcon icon"></i> ${fun.funName }<i class="dropdown icon"></i>
                </a>

                <div class="content">
                    <c:forEach items="${fun.subList}" var="subFun">
                        <a class="item" href="${pageContext.request.contextPath }${subFun.funUrl }"
                           target="content"> ${subFun.funName }
                        </a>
                    </c:forEach>
                </div>

            </div>
        </c:forEach>

        <c:forEach items="${sessionScope.users.funsList }" var="fun">
            <div class="ui dropdown item displaynone scrolling">
                <span>${fun.funName }</span>
                <i class="ion-speedometer icon"></i>

                <div class="menu">
                    <div class="header">
                            ${fun.funName }
                    </div>
                    <div class="ui divider"></div>
                    <c:forEach items="${fun.subList}" var="subFun">
                        <a class="item" href="${pageContext.request.contextPath }${subFun.funUrl }" target="content">
                                ${subFun.funName }
                        </a>
                    </c:forEach>
                </div>
            </div>
        </c:forEach>
    </div>

    <div class="pusher">
        <!--navbar 右侧上部-->
        <div class="navslide navwrap">
            <div class="ui menu icon borderless grid" data-color="inverted white">
                <a class="item labeled openbtn">
                    <i class="ion-navicon-round big icon"></i>
                </a>
                <a class="item labeled expandit" onclick="toggleFullScreen(document.body)">
                    <i class="ion-arrow-expand big icon"></i>
                </a>
                <div class="item ui colhidden">
                    <div class="ui icon input">
                        <input type="text" placeholder="Search...">
                        <i class="search icon"></i>
                    </div>
                </div>
                <div class="right menu colhidden">

                    <div class="ui dropdown item">
                        <i class="ion-wrench large icon"></i>
                        <i class="dropdown icon"></i>
                        <div class="menu">
                            <a class="item" href="${pageContext.request.contextPath }/back/admin/toUpdatePW"
                               target="content">修改密码</a>
                            <a class="item" href="${pageContext.request.contextPath }/back/admin/toChangeEmail"
                               target="content">变更邮箱</a>
                            <div class="ui divider"></div>
                            <a class="item"
                               onclick="javascript:if(confirm('您确定要退出吗？')){window.parent.location.href='${pageContext.request.contextPath  }/back/login/logout'}"
                               target="content">退出</a>
                        </div>
                    </div>

                    <div class="ui dropdown item">
                        <img class="ui mini circular image"
                             src="${pageContext.request.contextPath }/static/back_1/img/avatar/people/enid.png"
                             alt="label-image" id="pic"/>
                        <div class="menu">
                            <a class="item" href="${pageContext.request.contextPath }/back/admin/adminInfo"
                               target="content">个人信息</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="mainWrap navslide">
            <iframe name="content" id="content" src="${pageContext.request.contextPath }/back/admin/adminInfo"
                    width="100%" height="100%"
                    scrolling="no" frameborder="0">
            </iframe>
        </div>
    </div>
</div>

<!--jquery-->
<script src="${pageContext.request.contextPath }/static/back_1/js/jquery-2.1.4.min.js"></script>
<!--jquery-->
<!--semantic-->
<script src="${pageContext.request.contextPath }/static/back_1/semantic/dist/semantic.min.js"></script>
<!--semantic-->
<!--counter number-->
<script src="${pageContext.request.contextPath }/static/back_1/plugins/counterup/jquery.counterup.min.js"></script>
<script src="${pageContext.request.contextPath }/static/back_1/plugins/counterup/waypoints.min.js"></script>
<!--counter number-->
<!--flot chart-->
<script src="${pageContext.request.contextPath }/static/back_1/plugins/flot/jquery.flot.js"></script>
<script src="${pageContext.request.contextPath }/static/back_1/plugins/flot/jquery.flot.resize.min.js"></script>
<script src="${pageContext.request.contextPath }/static/back_1/plugins/flot/jquery.flot.tooltip.min.js"></script>
<script src="${pageContext.request.contextPath }/static/back_1/plugins/flot/curvedLines.js"></script>
<script src="${pageContext.request.contextPath }/static/back_1/plugins/cookie/js.cookie.js"></script>
<!--flot chart-->
<!--chartjs chart-->
<script src="${pageContext.request.contextPath }/static/back_1/plugins/chartjs/chart.min.js"></script>
<!--chartjs chart-->
<script src="${pageContext.request.contextPath }/static/back_1/plugins/nicescrool/jquery.nicescroll.min.js"></script>
<script data-pace-options='{ "ajax": false }'
        src="${pageContext.request.contextPath }/static/back_1/plugins/pacejs/pace.js"></script>
<script src="${pageContext.request.contextPath }/static/back_1/plugins/chartist/chartist.min.js"></script>
<script src="${pageContext.request.contextPath }/static/back_1/js/dashboard2.js"></script>
<script src="${pageContext.request.contextPath }/static/back_1/js/main.js"></script>
</body>
</html>
