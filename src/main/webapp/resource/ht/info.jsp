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
    <title>网站信息</title>  
    <link rel="stylesheet" href="<%=basePath%>resource/ht/css/pintuer.css">
    <link rel="stylesheet" href="<%=basePath%>resource/ht/css/admin.css">
    <script src="<%=basePath%>resource/ht/js/jquery.js"></script>
    <script src="<%=basePath%>resource/ht/js/pintuer.js"></script>  
</head>
<body>
<img src="<%=basePath %>resource/ht/b.jpg" alt="" width="100%" />
</body></html>