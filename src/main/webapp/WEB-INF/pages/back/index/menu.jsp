<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
         contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>修配连汽配市场</title>
    <link href="${pageContext.request.contextPath }/static/css/index.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery.min.js"></script>
    <!--tab切换-->
    <script src="${pageContext.request.contextPath }/static/back/js/jquery.tabs.js"></script>
    <script>
        function Goto(src) {
            window.top.document.getElementById('body').src = src;

        }
    </script>
</head>

<body>
<div class="top3">
    <!--导航-->
    <div class="wid">
        <div class="top3_left">
            <span>修配连</span>汽配市场
        </div>
        <div class="top3_right">
            <!--导航-->
            <c:if test="${not empty sessionScope.users.funsList}">
                <ul id="test">
                    <c:forEach items="${sessionScope.users.funsList }" var="fun">
                        <ul id="ul">
                            <li>
                                <h4>
                                    <a class="list-group-item active" data-toggle="collapse"
                                       data-parent="#ul" href="#hide${fun.funName }">${fun.funName }</a>
                                </h4>
                                <div id="hide${fun.funName }" class="in">
                                    <ul>
                                        <c:forEach items="${fun.subList}" var="subFun">
                                            <li><a
                                                    href="${pageContext.request.contextPath }${subFun.funUrl }"
                                                    target="content" class="list-group-item">${subFun.funName }</a>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </li>
                        </ul>
                    </c:forEach>
                </ul>
            </c:if>
            <div class="clear"></div>
        </div>
    </div>
    <div class="clear"></div>
</div>
<!--导航-->
</body>
</html>
