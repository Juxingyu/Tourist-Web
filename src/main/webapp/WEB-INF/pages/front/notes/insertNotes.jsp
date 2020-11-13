<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>爱途旅游网-新增游记</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/static/front/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/front/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/front/css/insertNotes.css">
    <!--导入angularJS文件-->
    <script src="${pageContext.request.contextPath }/static/front/js/angular.min.js"></script>
    <!--导入jquery-->
    <script src="${pageContext.request.contextPath }/static/front/js/jquery-3.3.1.js"></script>
    <style type="text/css">
        .login_inner input {
            height: 30px;
            font-size: 14px;
            line-height: 24px;
        }

        .margin1.padding3 {
            height: 40px;
        }

        .margin1.padding3 > span {
            line-height: 40px;
        }
    </style>
</head>
<body>
<%@ include file="/WEB-INF/pages/front/index/header.jsp" %>

<section id="login_wrap">
    <div class="fullscreen-bg"
         style="background: url(${pageContext.request.contextPath }/static/front/images/login_bg.png);height: 532px;">

    </div>
    <div class="login-box">
        <div class="title">
            <span>新增游记</span>
        </div>
        <div class="login_inner">
            <form id="loginForm" action="${pageContext.request.contextPath }/front/notes/insertNotes"
                  enctype="multipart/form-data" method="post" accept-charset="utf-8">
                <div class="margin1 padding3"><span style="line-height: 20px">游记类别</span>
                    <select class="xinzeng" id="notestypeId" name="notestypeId" style="height: 30px">
                        <option value="-1">请选择</option>
                        <c:forEach items="${requestScope.allNotesType}" var="cp">
                            <option value="${cp.notestypeId}">${cp.notestypeName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="margin1 padding3"><span>游记标题</span>
                    <input type="text" id="notesTitle" class="xinzeng" name="notesTitle"/>
                </div>
                <div class="margin1 padding3"><span>游记图片</span>
                    <input class="xinzeng" type="file" id="notesPic" name="file" value="选择上传"/>
                </div>
                <div class="margin1 padding3"><span>游记位置</span>
                    <input type="text" id="notesLocation" class="xinzeng" name="notesLocation"/>
                </div>
                <div class="margin1 padding3">
                    <span class="padding4">游记内容</span>
                    <textarea id="notesContent" name="notesContent" class="miaoshu"></textarea>
                </div>
                <br><br><br>
                <div class="submit_btn">
                    <button type="submit">提交</button>
                </div>
            </form>
        </div>
    </div>
</section>

<!--引入尾部-->
<%@ include file="/WEB-INF/pages/front/index/footer.jsp" %>
<!--引入尾部 End-->


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${pageContext.request.contextPath }/static/front/js/jquery-1.11.0.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${pageContext.request.contextPath }/static/front/js/bootstrap.min.js"></script>
<!--导入布局js，共享header和footer-->
<script type="text/javascript" src="${pageContext.request.contextPath }/static/front/js/include.js"></script>
</body>
</html>