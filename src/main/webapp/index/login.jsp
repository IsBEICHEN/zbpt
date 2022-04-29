<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath() + "/";
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<html lang="zh-cn">

<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>众包平台</title>
		<link rel="shortcut icon" href="<%=basePath%>resource/login/img/favicon.ico" type="image/x-icon" />
		<link rel="stylesheet" href="<%=basePath%>resource/login/layui/css/layui.css">
		<link rel="stylesheet" href="<%=basePath%>resource/login/css/login.css">
		<script src="<%=basePath%>resource/ht/js/jquery.js"></script>
		 <!-- 引入artDailog支持的库 -->
    <link rel="stylesheet" href="<%=basePath %>resource/admin/artDialog/css/ui-dialog.css">
    <script language="JavaScript" src="<%=basePath %>resource/admin/artDialog/dist/dialog-plus.js"></script>
	</head>
 	 <script type="text/javascript">
		$(function(){
			$("#account").focus();
		});
		function login(){
			var account=$.trim($("#account").val());
			var password=$.trim($("#password").val());
			var code=$.trim($("#code").val());
			var role=$.trim($("#role").val());
			if(account.length==0){
				alert("账号不能为空");
				$("#account").focus();
				return false;
			}
			if(password.length==0){
				alert("密码不能为空");
				$("#password").focus();
				return false;
			}
			return true;
		}
	</script>
	<script type="text/javascript">
		function sel(){
          var d = top.dialog({
        	 	title:' ',
                width:600,
                height:500,
                url:'<%=basePath %>index/admin/user/password.jsp',//可以是一个访问路径Action|Servlet等或者jsp页面资源
                onclose: function () {
                if (this.returnValue="success") {
                    window.location.href= window.location.href;
                }
            }
          });
           d.showModal();
        }
	</script>
	<script type="text/javascript">
		function add(){
          var d = top.dialog({
        	 	title:' ',
                width:600,
                height:600,
                url:'<%=basePath %>index/yh/myinfo/add.jsp',//可以是一个访问路径Action|Servlet等或者jsp页面资源
                onclose: function () {
                if (this.returnValue="success") {
                    window.location.href= window.location.href;
                }
            }
          });
           d.showModal();
        }
	</script>
	<%
		
			String error = (String)request.getAttribute("message");
			error = error == null?"":error;
			String account = (String)request.getParameter("account");
			account = account == null?"":account;
		%>
	<body>
		<div class="layui-carousel video_mask" id="login_carousel">
			<div carousel-item>
				<div class="carousel_div1"></div>
				<div class="carousel_div2"></div>
				<div class="carousel_div3"></div>
			</div>
			<div class="login layui-anim layui-anim-up">
				<fieldset class="layui-elem-field layui-field-title">
					<legend class="text-white">众包平台</legend>
				</fieldset>
				<form action="<%=basePath %>login" method="post" >
				<div class="layui-form" action="<%=basePath %>login" method="post">
					<div class="layui-form-item">
						<input type="text"  name="account" id="account" value="<%=account %>" lay-verify="required|account" maxlength="20" placeholder="请输入账号" autofocus="autofocus" class="layui-input">
					</div>
					<div class="layui-form-item">
						<input type="password" name="password" id="password" lay-verify="required" maxlength="20" placeholder="请输入密码" autocomplete="off" class="layui-input">
					</div>
					<div class="layui-form-item form_code">
						<font size = "5" color = "red" ><%=error %></font>
						  <a href="javascript:void(0)" onclick="add()">
						 <font color="white">注册</font>&nbsp;&nbsp;
						 </a>
						  <a href="javascript:void(0)" onclick="sel()">
						 <font color="white">忘记密码？</font>
						 </a>
					</div>
					<button  type="submit" class="login_btn layui-btn layui-btn-radius layui-btn-normal" lay-submit lay-filter="login">登录</button>
				</div>
				</form>       
			</div>
		</div>
		<script type="text/javascript" src="<%=basePath%>resource/login/js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>resource/login/layui/layui.all.js"></script>
		<script type="text/javascript" src="<%=basePath%>resource/login/js/login.js"></script>
	</body>

</html>