<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath() + "/";
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>无标题文档</title>
	<link href="<%=basePath %>resource/admin/css/style.css" rel="stylesheet" type="text/css" />
	 <link href="<%=basePath %>resource/admin/css/style.css" rel="stylesheet" type="text/css"/>
 	<script language="JavaScript" src="<%=basePath %>resource/admin/js/jquery.js"></script>
	<script type="text/javascript">
		function add(id) {
		 $.ajax({
		        type:"get",
		        url:"<%=basePath%>comm/addXk",
		        data:{"sId":id},
		        success:function(data) {
		        if(data.fail!=null){
			         alert(data.fail);
			        }else{
			        alert(data.flag);
			        window.location.href="<%=basePath%>comm/selXk"
			        }
		         }
		    }); 
		}
	</script>
</head>
		

<body>
<form action="comm/selCou" method="post" rel="page">
<div class="place">
	<span>位置：</span>
	<ul class="placeul">
		<li><a href="#">任务管理</a></li>
		<li><a href="#">查看任务信息</a></li>
	</ul>
</div>
<div class="formbody">
	<div class="formtitle" style="cursor: pointer;" id="formtitle"><span>条件查询</span></div>
	<ul class="forminfo" style="display: none;" id="forminfo">
		<li>
			<label>任务名称</label>
			<input name="kname"  type="text" class="dfinput" placeholder="请输入任务名称" />
		</li>
		<li>
			<label>&nbsp;</label>
			<input name="" type="submit" class="btn" value="查询"/>
			<input name="" type="reset" class="btn" value="重置"/>
		</li>
	</ul>
</div>
		<table class="imgtable"border = "5" >
			<thead>
			<tr>
				<th>id</th>
				<th>任务名称</th>
				<th>发布人</th>
				<th>完成时间</th>
				<th>预算成本</th>
				<th>质量</th>
				<th>位置</th>
				<th>任务介绍</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody>
		<c:if test="${!empty courseList}">
			<c:forEach items="${courseList}" var="list" varStatus="vs">
			<tr>
				<td>${vs.index+1}</td>
				<td>${list.kname}</td>
				<td>${list.account}</td>
				<td>${list.keshi}</td>
				<td>${list.xuefen}</td>
				<td>${list.zl}</td>
				<td>${list.wz}</td>
				<td>${list.content}</td>
				<td>
					<a href = "comm/selXk?cid=${list.id}"><font color="red">${list.state}</font></a>
				</td>
				<td>
					<c:if test="${list.state=='未接取'}">
						<a href = "javascript:void(0)" onclick="add('${list.id}')"><font color="blue">接取任务</font></a>
					</c:if>
					<c:if test="${list.state!='未接取'}">
					暂无操作
					</c:if>
				</td>	
			</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty courseList}">
			<tr>
				<td colspan="8" align="center">
					<strong><font color="red">暂时没有数据，请添加</font></strong>
				</td>
			</tr>
		</c:if>
		</tbody>
		</table>
		<div class="pagin">
    	<div class="message">共<i class="blue">${pageInfo.total}</i>条记录，
    	当前显示第&nbsp;<i class="blue">${pageInfo.pageNum}&nbsp;</i>页，
    	共&nbsp;<i class="blue"> ${countPage}&nbsp;</i>页&nbsp;&nbsp;
    	</div>
        <ul class="paginList">
        <li class="paginItem"><a href="comm/selCou?page=1">首页</a></li>
        <li class="paginItem"><a href="comm/selCou?page=${pageInfo.hasPreviousPage==false?1:pageInfo.prePage}">上一页</a></li>
        <li class="paginItem"><a href="comm/selCou?page=${pageInfo.hasNextPage==false?pageInfo.pages:pageInfo.nextPage}">下一页</a></li>
        <li class="paginItem"><a href="comm/selCou?page=${pageInfo.pages}">尾页</a></li>
        </ul>
    </div>
</form>

<script type="text/javascript" src="<%=basePath %>resource/admin/jQuery/jquery.js"></script>
<script type="text/javascript">
	/**
	 * 这个使用的单选框选择判断
	 */
	  function deleteUser(){
		alert("删除成功");
		}
	function toUpdate(){
		var id = $("input[name='id']:checked").val();
		if(!id){
			alert("请选择要操作的记录");
			return false;
		}else{
			alert( "您操作的值为："+$("input[name='id']:checked").val())
		}
	}
	$(function(){
		$('.tablelist tbody tr:odd').addClass('odd');
		
		$("#formtitle").click(function(){
			$("#forminfo").slideToggle("slow");
		});
	});
</script>
<script type="text/javascript">
	$(function(){
		$('.imgtable tbody tr:odd').addClass('odd');
	});
    
</script>

</body>

</html>

