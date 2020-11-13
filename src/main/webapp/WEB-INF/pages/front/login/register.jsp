<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <title>注册</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/front/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/front/css/register.css">
    <!--导入jquery-->
    <script src="${pageContext.request.contextPath }/static/front/js/jquery-3.3.1.js"></script>
    <script type="text/javascript">
        var countdown = 60;//全局变量倒计时
        // $(function () {
        //     $("#updapwd").disabled();
        // });
        var boo;

        function sendcode() {
            var email = $("#email").val();
            $.ajax({
                url: "${pageContext.request.contextPath }/front/login/checkEmail",    //请求的url地址
                async: true,//请求是否异步，默认为异步，这也是ajax重要特性
                data: {"email": email},    //参数值
                type: "post",  //请求方式
                success: function (data) {
                    if (data === "0") {
                        alert("该邮箱已经存在！");
                    }
                    if (data === "1") {
                        alert("发送成功");
                        boo = "2";
                        var obj = $("#getmsg");
                        settime(obj); //倒计时开始
                    }
                },
            });
        }

        /**
         * 获取验证码倒计时
         */
        function settime(obj) { //发送验证码倒计时
            if (countdown == 0) {
                obj.attr('disabled', false);
                //obj.removeattr("disabled");
                obj.val("免费获取验证码");
                countdown = 60;
                return;
            } else {
                obj.attr('disabled', true);
                obj.val("重新发送(" + countdown + ")");
                countdown--;
            }
            setTimeout(function () {
                    settime(obj)
                }
                , 1000)
        }

        /*校验验证码*/
        function changemsg() {
            if (boo != "2") {
                alert("请先等待接收验证码！");
                return;
            }
            var dymsg = $("#dymsg").val();
            var loginName = $("#loginName").val();
            var petName = $("#petName").val();
            var pass = $("#pass").val();
            var email = $("#email").val();
            var sex = $("#sex").val();
            $.ajax({
                url: "${pageContext.request.contextPath }/front/login/register",    //请求的url地址
                async: true,//请求是否异步，默认为异步，这也是ajax重要特性
                data: {
                    'volidate': dymsg, 'loginName': loginName,
                    'petName': petName,
                    'pass': pass,
                    'email': email,
                    // 'sex': sex,
                },
                //参数值
                type: "post",  //请求方式
                success:

                    function (data) {
                        if (data == "0") {
                            $("#msg").html("验证码错误");
                            alert("验证码错误！");
                        }
                        if (data == "1") {
                            $("#msg").html("注册失败");
                            alert("注册失败");
                        }
                        if (data == "2") {
                            alert("注册成功");
                            window.location.href = "${pageContext.request.contextPath }/front/login/tologin";
                        }
                    }

                ,
            })
            ;
        }

    </script>
</head>
<body>
<!--引入头部-->
<%@ include file="/WEB-INF/pages/front/index/header.jsp" %>
<!-- 头部 end -->
<div class="rg_layout">
    <div class="rg_form clearfix">
        <div class="rg_form_left">
            <p>新用户注册</p>
            <p>USER REGISTER</p>
        </div>
        <div class="rg_form_center">

            <!--注册表单-->
            <form id="registerForm">
                <!--提交处理请求的标识符-->
                <input type="hidden" name="action" value="register">
                <table style="margin-top: 25px;">
                    <tr>
                        <td class="td_left">
                            <label for="loginName">用户名</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="loginName" name="loginName" placeholder="请输入用户名" value="">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="petName">昵称</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="petName" name="petName" placeholder="请输入昵称" value="">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="pass">密码</label>
                        </td>
                        <td class="td_right">
                            <input type="password" id="pass" name="pass" placeholder="请输入密码" value="">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="email">Email</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="email" name="email" placeholder="请输入Email" value="">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="sex">性别</label>
                        </td>
                        <td class="td_right gender">
                            <input type="radio" id="sex" name="sex" value="男" checked> 男
                            <input type="radio" name="sex" value="女"> 女
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="dymsg">验证码</label>
                        </td>
                        <td class="td_right check">
                            <input type="text" id="dymsg" name="dymsg" value=""
                                   placeholder="请输入验证码" class="check">
                            <input type="button" name="getmsg" id="getmsg" class="submita" onclick="sendcode()"
                                   value="点击获取"/>
                        </td>

                    </tr>
                    <tr>
                        <td>
                            <span id="msg" style="color: red;"></span>
                        </td>
                    </tr>

                    <tr>
                        <td class="td_left">
                        </td>
                        <td class="td_right check">
                            <input type="button" onclick="changemsg()" class="submit" value="注册">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div class="rg_form_right">
            <p>
                已有账号？
                <a href="${pageContext.request.contextPath }/front/login/tologin">立即登录</a>
            </p>
        </div>
    </div>
</div>


<!--引入尾部-->
<%@ include file="/WEB-INF/pages/front/index/footer.jsp" %>


<!--导入布局js，共享header和footer-->
<script type="text/javascript" src="${pageContext.request.contextPath }/static/front/js/include.js"></script>
</body>
</html>
