<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>游记评论管理列表</title>
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
                            <td>序号</td>
                            <td>评论者</td>
                            <td>评论内容</td>
                            <td>点赞数量</td>
                            <td>时间</td>
                            <td>操作</td>
                        </tr>
                        </thead>
                        <c:forEach items="${requestScope.notesComment }" var="aa" varStatus="statu">
                            <tr>
                                <td>${statu.count}</td>
                                <td>${aa.users.petName }</td>
                                <td>${aa.commentContent }</td>
                                <td>
                                        ${aa.praseCount}
                                </td>
                                <td>
                                    <p>
                                        <fmt:formatDate value="${aa.updateDate}"
                                                        pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </p>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${aa.status eq 1 }">
                                            <a href="javascript:changeStatu('${aa.users.petName }','${aa.commentId}',0,'${aa.commentContent }')"
                                               class="ui red button">删除</a></c:when>
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
    function changeStatu(petName, commentId, statu, commentContent) {
        var info = statu == 1 ? "恢复" : "删除";
        var b = window.confirm("确定" + info + "-" + petName + "的" + commentContent + "-评论吗？");
        if (b) {
            $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath }/comment/updateStatus",
                    data: "commentId=" + commentId + "&status=" + statu,
                    success: function (data) {
                        // alert(info + "成功！");
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