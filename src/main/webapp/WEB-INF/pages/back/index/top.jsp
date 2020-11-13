<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>修配连汽配市场</title>
    <link href="${pageContext.request.contextPath  }/static/css/index_c.css" rel="stylesheet" type="text/css"/>
    <!--弹出页-->
    <script type="text/javascript" src="${pageContext.request.contextPath  }/static/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath  }/static/js/layer/layer.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        //弹出一个iframe  模态框
        function mygsS() {
            var url = "${pageContext.request.contextPath  }/index/toChangePassword";
            $.layer({
                type: 2,
                title: false,//关闭按钮
                shadeClose: false,
                shade: [0.6, '#000'],//遮罩
                border: [0, 0, '#ccc'],//边框
                area: ['650px', '450px'],//宽高
                iframe: {
                    src: '' + url + ''
                },
                shift: ["top"], //从下面动画弹出
                end: function () {
                    window.location.reload();
                }
            });
        }
    </script>
</head>

<body>
<div class="top2">
    <div class="wid">
        <ul>
            <li class="usename">${sessionScope.users.loginName}&nbsp;&nbsp;&nbsp;&nbsp;${sessionScope.users.petName}</li>

            <li class="shezhi"
                onclick="javascript:window.top.document.getElementById('body').src ='${pageContext.request.contextPath  }/index/toChangePassword'">
                <a onclick="mygs();" href="javascript:">个人设置</a></li>

            <li>${now}</li>
            <li></li>
            <li class="tuichu"
                onclick="javascript:if(confirm('您确定要退出吗？')){window.parent.location.href='${pageContext.request.contextPath  }/login/loginout'}">
                <a
                        href="javascript:;">退出</a></li>
        </ul>
        <div class="clear"></div>
    </div>
</div>
</body>
</html>
