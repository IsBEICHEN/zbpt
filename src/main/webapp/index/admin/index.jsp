<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath() + "/";
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>

<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>众包平台</title>  
    <link rel="stylesheet" href="<%=basePath%>resource/ht/css/pintuer.css">
    <link rel="stylesheet" href="<%=basePath%>resource/ht/css/admin.css">
    <script src="<%=basePath%>resource/ht/js/jquery.js"></script>   
    <script type="text/javascript">
		function loginOut(){
			if(window.confirm("您确定要退出系统吗？")){
				window.top.location.href="<%=basePath%>index/login.jsp"
		}
	}
    </script>
</head>
<body style="background-color:#f2f9fd;">
<div class="header bg-main">
  <div class="logo margin-big-left fadein-top">
    <h1><img src="<%=basePath%>resource/ht/images/y.jpg" class="radius-circle rotate-hover" height="50" alt="" />众包平台</h1>
  </div>
  <div class="head-l">
   <a href="##" class="button button-little bg-blue"><span class="icon-wrench"></span> 管理员 : ${sessionScope.account}</a> &nbsp;&nbsp;
  
  <a class="button button-little bg-green" href="<%=basePath%>index/login.jsp" ><span class="icon-home"></span>切换账号</a> &nbsp;&nbsp;
  <a class="button button-little bg-red" href="javascript:void(0)" onclick="loginOut()"><span class="icon-power-off"></span> 退出登录</a> 
  </div>
</div>
<div class="leftnav">
  <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
  <h2><span class="icon-user"></span>系统管理</h2>
  <ul style="display:block">
    <li><a href="<%=basePath%>index/admin/password/updatePassword.jsp" target="right"><span class="icon-caret-right"></span>密码管理</a></li>
  </ul>   
  <h2><span class="icon-pencil-square-o"></span>用户管理</h2>
  <ul>
    <li><a href="<%=basePath%>user/selHc" target="right"><span class="icon-caret-right"></span>用户管理</a></li>
     <li><a href="<%=basePath%>user/selHz" target="right"><span class="icon-caret-right"></span>工人管理</a></li>
     <li><a href="<%=basePath%>comm/selKh" target="right"><span class="icon-caret-right"></span>钱包</a></li> 
  </ul> 
    <h2><span class="icon-pencil-square-o"></span>公告管理</h2>
  <ul>
    <li><a href="<%=basePath%>comm/selLb" target="right"><span class="icon-caret-right"></span>公告信息</a></li> 
  </ul>
    <h2><span class="icon-pencil-square-o"></span>任务管理</h2>
  <ul>
    <li><a href="<%=basePath%>comm/selCou" target="right"><span class="icon-caret-right"></span>任务信息</a></li> 
    <li><a href="<%=basePath%>comm/selXk" target="right"><span class="icon-caret-right"></span>接取任务信息</a></li>
  </ul>  
  <h2><span class="icon-pencil-square-o"></span>评价管理</h2>
  <ul>
    <li><a href="<%=basePath%>comm/selGra" target="right"><span class="icon-caret-right"></span>评价信息</a></li> 
  </ul>
  <h2><span class="icon-pencil-square-o"></span>评估管理</h2>
  <ul>
    <li><a href="<%=basePath%>comm/selXx" target="right"><span class="icon-caret-right"></span>评估信息</a></li> 
  </ul>
   <h2><span class="icon-pencil-square-o"></span>留言管理</h2>
  <ul>
    <li><a href="<%=basePath%>comm/selLy" target="right"><span class="icon-caret-right"></span>留言信息</a></li>               
  </ul> 
</div>
<script type="text/javascript">
$(function(){
  $(".leftnav h2").click(function(){
	  $(this).next().slideToggle(200);	
	  $(this).toggleClass("on"); 
  })
  $(".leftnav ul li a").click(function(){
	    $("#a_leader_txt").text($(this).text());
  		$(".leftnav ul li a").removeClass("on");
		$(this).addClass("on");
  })
});
</script>
<!-- <ul class="bread">
  <li><a href="{:U('Index/info')}" target="right" class="icon-home">当前位置</a></li>
  <li><a href="##" id="a_leader_txt">首页</a></li>
  
</ul> -->
<div class="admin">
  <iframe scrolling="auto" rameborder="0" src="<%=basePath %>resource/ht/info.jsp" name="right" width="100%" height="100%"></iframe>
</div>
</body>
</html>