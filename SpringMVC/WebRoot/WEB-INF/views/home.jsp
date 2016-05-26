<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.net.URI"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'home.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
<link href="bootstrap/css/custom.css" rel="stylesheet" type="text/css">
<script src="jquery/jquery.js"></script>
<script src="jquery/jquery.validate.js"></script>
<script src="jquery/jquery.metadata.js"></script>
<script src="jquery/jquery.validate.messages_cn.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script>
	$(document).ready(function() {
		$("#searchForm").validate({
			rules : {
				content : {
					required : true,
				}
			},
			messages : {
				content : {
					required : "请输入需要搜索的内容"
				}
			}
		});
	});
</script>

</head>

<body>

   <div class="jumbotron vertical-center">
	<div class="container">
		<div class="row">
			<div class="col-md-9 col-md-offset-3">
				<div class="row">
					<div class="col-md-3 col-md-offset-2">
						<h1>Kindle</h1>
					</div>
				</div>
				<form class="form-horizontal" role="form" id="searchForm"
					method="post" action="/SpringMVC/search">
					<div class="form-group">
						<div class="col-md-7">
							<input type="text" class="form-control" id="content"
								name="content" placeholder="请输入书名">
						</div>
						<div class="col-md-1">
							<button type="submit" class="btn btn-default">开始搜索</button>
						</div>
					</div>
				</form>
				<div class="row">
					<div class="col-md-3 col-md-offset-2">
						<a href="/SpringMVC/login" class="btn btn-success btn-lg"
							role="button">登录</a> <a href="/SpringMVC/register"
							class="btn btn-info btn-lg" role="button">注册</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
