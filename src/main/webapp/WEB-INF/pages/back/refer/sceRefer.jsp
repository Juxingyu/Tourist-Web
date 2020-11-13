<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>推荐攻略列表</title>
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
<div class="row" style="height: 1750px">
    <div class="sixteen wide column">
        <div class="ui segments">
            <div class="ui segment">
                <div id="data_table_wrapper" class="ui grid dataTables_wrapper">
                    <table id="data_table"
                           class="ui blue compact selectable striped celled table tablet stackable dataTable no-footer"
                           cellspacing="0" width="100%" role="grid" aria-describedby="data_table_info"
                           style="text-align: center;font-size: 14px">
                        <thead>
                        <tr>
                            <td>序号</td>
                            <td>景点名称</td>
                            <th>景点类型</th>
                            <td>景点图片</td>
                            <td>景点描述</td>
                            <th>景点级别</th>
                            <th>景点位置</th>
                            <td>点赞数量</td>
                            <td>时间</td>
                            <td>操作</td>
                        </tr>
                        </thead>
                        <c:forEach items="${requestScope.sceRefer }" var="aa" varStatus="statu">
                            <tr>
                                <td>${statu.count}</td>
                                <td>${aa.secName }</td>
                                <td>${aa.scenictpyeName }</td>
                                <td><img src="${pageContext.request.contextPath }/static/images/${aa.secPic}"
                                         width="180px"/></td>
                                <td style="text-align: left;font-size: 10px">${aa.secDesc}</td>
                                <td>${aa.secLevel}</td>
                                <td>${aa.secSite}</td>
                                <td>${aa.praseCount}</td>
                                <td>
                                    <p>
                                        <fmt:formatDate value="${aa.updateDate}"
                                                        pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </p>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${aa.status eq 1 }">
                                            <a href="javascript:changeStatu('${aa.sceId }',0,'${aa.secName}')"
                                               class="ui red button">撤销推荐</a></c:when>
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
    function changeStatu(sceId, statu, secName) {
        var b = window.confirm("确定撤销" + "-" + secName + "-景点推荐吗？");
        if (b) {
            $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath }/refer/updateStatus",
                    data: "sceId=" + sceId + "&refer=" + statu,
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