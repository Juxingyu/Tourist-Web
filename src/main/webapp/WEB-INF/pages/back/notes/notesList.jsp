<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>游记审核列表</title>
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
<div class="row" style="height: 2600px">
    <div class="sixteen wide column">
        <div class="ui segments">
            <div class="ui segment">
                <div id="data_table_wrapper" class="ui grid dataTables_wrapper">
                    <table id="data_table"
                           class="ui blue compact selectable striped celled table tablet stackable dataTable no-footer"
                           cellspacing="0" width="100%" role="grid" aria-describedby="data_table_info"
                           style="font-size: 14px;text-align: center">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>游记标题</th>
                            <th>作者</th>
                            <th>游记图片</th>
                            <th>游记内容</th>
                            <th>状态</th>
                            <th>推荐</th>
                            <th>时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <c:forEach items="${requestScope.notesList }" var="aa" varStatus="statu">
                            <tr id="id_${statu.index}" style="height: 30px;font-size: 14px ">
                                <td>${statu.count}</td>
                                <td>${aa.notesTitle }</td>
                                <td>${aa.petName}</td>
                                <td style="padding: 0;height: 100%"><img
                                        src="${pageContext.request.contextPath }/static/images/${aa.notesPic }"
                                        width="180px"/></td>
                                <td style="text-align: left;font-size: 10px">
                                        ${aa.notesContent}
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${aa.notesStatus eq 0 }"><span
                                                style="color: #FF0000">已删除</span></c:when>
                                        <c:when test="${aa.notesStatus eq 1 }"><span
                                                style="color: #0ea432">审核通过</span></c:when>
                                        <c:when test="${aa.notesStatus eq 2 }"><span
                                                style="color: #000000">未审核</span></c:when>
                                        <c:otherwise>未审核</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${aa.refer eq 0 }">
                                            <span>否</span>
                                        </c:when>
                                        <c:when test="${aa.refer eq 1 }">
                                            <span style="color: red">是</span>
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td style="font-size: 10px">
                                    <p>
                                        <fmt:formatDate value="${aa.updateDate}"
                                                        pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </p>
                                </td>
                                <td style="text-align: left">
                                    <%--<a href="${pageContext.request.contextPath }/notes/findNotesByNotesId?notesId=${aa.notesId}--%>
                   <%--&notesPic=${aa.notesPic}&notestypeId=${aa.notestypeId}&userId=${aa.userId}"--%>
                                       <%--class="ui blue button">查看</a><br>--%>
                                    <c:choose>
                                        <c:when test="${aa.notesStatus eq 0 }">
                                            <a href="javascript:changeStatu('${aa.notesId }','${aa.userId}',2,'${aa.notesTitle}')"
                                               class="btn btn-warning">恢复</a><br></c:when>
                                        <c:when test="${aa.notesStatus eq 1 }">
                                            <a href="javascript:changeStatu('${aa.notesId }','${aa.userId}',0,'${aa.notesTitle}')"
                                               class="ui red button">删除</a><br>
                                            <c:if test="${aa.refer eq 0 }">
                                                <a href="javascript:refer('${aa.notesId }','${aa.userId}',1,'${aa.notesTitle}')"
                                                   class="ui green button">推荐</a><br>
                                            </c:if>
                                        </c:when>
                                        <c:when test="${aa.notesStatus eq 2 }">
                                            <a href="javascript:changeStatu('${aa.notesId }','${aa.userId}',1,'${aa.notesTitle}')"
                                               class="ui green button">审核通过</a> <br><a
                                                href="javascript:changeStatu('${aa.notesId }','${aa.userId}',0,'${aa.notesTitle}')"
                                                class="ui red button">删除</a>
                                        </c:when>
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
    function changeStatu(notesId, userId, statu, notesTitle) {
        if (statu == 2) {
            var info = "恢复";
        } else if (statu == 0) {
            var info = "删除";
        } else if (statu == 1) {
            var info = "审核通过";
        }
        var b = window.confirm("确定" + info + userId + "的" + notesTitle + "这篇游记吗？");
        if (b) {
            $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath }/notes/updateNotesStatus",
                    data: "notesId=" + notesId + "&notesStatus=" + statu,
                    success: function (data) {
                        // alert(info + "成功！");
                        window.location.reload();
                    }, error: function () {
                        alert("出现了异常")
                    }
                }
            );
        }
    };

    function refer(notesId, userId, statu, notesTitle) {
        var b = window.confirm("确定" + "推荐" + userId + "的" + notesTitle + "这篇游记吗？");
        if (b) {
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath }/notes/referNotes",
                data: "notesId=" + notesId + "&refer=" + statu,
                success: function (data) {
                    // alert("推荐成功！");
                    window.location.reload();
                }, error: function () {
                    alert("出现了异常")
                }
            });
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