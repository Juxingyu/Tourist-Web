<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>用户列表</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <link href="${pageContext.request.contextPath }/static/back_1/semantic/dist/semantic.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/static/back_1/plugins/ionicons/css/ionicons.min.css"
          rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/static/back_1/css/main.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/static/back_1/plugins/pacejs/pace.css" rel="stylesheet"/>
</head>

<body>
<div class="row" style="height: 900px">
    <div class="sixteen wide column">
        <div class="ui segments">
            <div class="ui segment">
                <div id="data_table_wrapper" class="ui grid dataTables_wrapper">
                    <table id="data_table"
                           class="ui blue compact selectable striped celled table tablet stackable dataTable no-footer"
                           cellspacing="0" width="100%" role="grid" aria-describedby="data_table_info"
                           style="font-size: 1.5em;">
                        <thead>
                        <tr role="row">
                            <th>序号</th>
                            <th>登录名</th>
                            <th>昵称</th>
                            <th>头像</th>
                            <th>邮箱</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <c:forEach items="${requestScope.usersList }" var="aa" varStatus="statu">
                            <tr>
                                <td>${statu.count}</td>
                                <td>${aa.loginName }</td>
                                <td>${aa.petName }</td>
                                <td style="padding: 0px;margin: 0px">
                                    <img src="${pageContext.request.contextPath }/static/images/${aa.pic }"
                                         width="42px"/></td>
                                <td>
                                        ${aa.email}
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${aa.status eq 0 }">已删除</c:when>
                                        <c:when test="${aa.status eq 1 }">正常</c:when>
                                        <c:otherwise>待定</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${aa.status eq 0 }">
                                            <a href="javascript:changeStatu('${aa.userId }',1,'${aa.petName}')"
                                               class="btn btn-success">恢复</a></c:when>
                                        <c:when test="${aa.status eq 1 }">
                                            <a href="javascript:changeStatu('${aa.userId }',0,'${aa.petName}')"
                                               class="ui negative button">删除</a></c:when>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">
    function changeStatu(userId, statu, petName) {
        var info = statu == 1 ? "恢复" : "删除";
        var b = window.confirm("确定" + info + "-" + petName + "-用户吗？");
        if (b) {
            $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath }/users/updateUsersStatus",
                    data: "userId=" + userId + "&status=" + statu,
                    success: function (data) {
                        alert(info + "成功！");
                        window.location.reload();
                    }, error: function () {
                        alert("出现了异常")
                    }
                }
            );
        }
    }
</script>
<!--jquery-->
<script src="${pageContext.request.contextPath }/static/back_1/js/jquery-2.1.4.min.js"></script>
<!--jquery-->
<!--semantic-->
<script src="${pageContext.request.contextPath }/static/back_1/semantic/dist/semantic.min.js"></script>
<!--semantic-->
<script src="${pageContext.request.contextPath }/static/back_1/plugins/cookie/js.cookie.js"></script>
<script src="${pageContext.request.contextPath }/static/back_1/plugins/nicescrool/jquery.nicescroll.min.js"></script>

<script data-pace-options='{ "ajax": false }'
        src="${pageContext.request.contextPath }/static/back_1/plugins/pacejs/pace.js"></script>
<script src="${pageContext.request.contextPath }/static/back_1/plugins/datatable/jquery.dataTables.js"></script>
<script src="${pageContext.request.contextPath }/static/back_1/js/customjs/custom-datatable.js"></script>
<script src="${pageContext.request.contextPath }/static/back_1/js/main.js"></script>
</body>
</html>