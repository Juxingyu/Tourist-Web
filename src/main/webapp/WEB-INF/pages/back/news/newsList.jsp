<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>资讯列表</title>
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
<div class="row" style="height: 1500px">
    <div class="sixteen wide column">
        <div class="ui segments">
            <div class="ui segment">
                <div id="data_table_wrapper" class="ui grid dataTables_wrapper">
                    <table id="data_table"
                           class="ui blue compact selectable striped celled table tablet stackable dataTable no-footer"
                           cellspacing="0" width="100%"
                           style="font-size: 14px;text-align: center">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>新闻标题</th>
                            <th>新闻图片</th>
                            <th>新闻内容</th>
                            <th>时间</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <c:forEach items="${requestScope.newsList }" var="aa" varStatus="statu">
                            <tr>
                                <td>${statu.count}</td>
                                <td>${aa.newsTitle }</td>
                                <td style="padding: 0;height: 100%"><img
                                        src="${pageContext.request.contextPath }/static/images/${aa.newsPic }"
                                        width="180px"/></td>
                                <td style="text-align: left;font-size: 10px">${aa.newsContext}</td>
                                <td>
                                    <p>
                                        <fmt:formatDate value="${aa.updateDate}"
                                                        pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </p>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${aa.status eq 0 }">已删除</c:when>
                                        <c:when test="${aa.status eq 1 }">正常</c:when>
                                        <c:otherwise>待定</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <a href="javascript:window.location.href='${pageContext.request.contextPath }/news/newsInfo?newsId=${aa.newsId}'"
                                       class="ui blue button">修改</a>
                                    <c:choose>

                                        <c:when test="${aa.status eq 0 }">
                                            <a href="javascript:changeStatu('${aa.newsId }',1,'${aa.newsTitle}')"
                                               class="btn btn-success">恢复</a></c:when>
                                        <c:when test="${aa.status eq 1 }">
                                            <a href="javascript:changeStatu('${aa.newsId }',0,'${aa.newsTitle}')"
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
    function changeStatu(newsId, statu, newsTitle) {
        var info = statu == 1 ? "恢复" : "删除";
        var b = window.confirm("确定" + info + "-" + newsTitle + "-公告吗？");
        if (b) {
            $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath }/news/updateStatus",
                    data: "newsId=" + newsId + "&status=" + statu,
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