<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<title>My JSP 'searchResult.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
<link href="bootstrap/css/custom.css" rel="stylesheet" type="text/css">
<script src="jquery/jquery.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script>
	$(document).ready(function() {
		$("[data-content]").click(function() {
			var value = $(this).data("content");
			$.post("/SpringMVC/sendEmail", {
				content : value
			}, function(data, status) {
				alert("Data: " + data + "\nStatus: " + status);
			});
		});
	});
</script>
</head>

<body>

	<div class="container">
		<div class="row">
			<div class="col-md-3 col-md-offset-9">
				<ul class="nav nav-pills">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#">SVN</a></li>
					<li><a href="#">iOS</a></li>
					<li><a href="#">VB.Net</a></li>
					<li><a href="#">Java</a></li>
					<li><a href="#">PHP</a></li>
				</ul>
			</div>
		</div>
		<div class="row">
			<div>你好,${userBean.getUserName()}</div>
			<c:forEach items="${pagination.getResultList()}" var="temp">
				<div class="panel panel-success">
					<div class="panel-heading">
						<span class="glyphicon glyphicon-book"></span>
						<c:out value="《${temp['content']}》" />
						<span class="glyphicon glyphicon-user"></span>
						<c:out value="作者: ${temp['author']}" />
						<button data-content="${temp['content']}" type="button" class="btn btn-primary">
						<span class="glyphicon glyphicon-envelope"></span>发送到邮箱</button>
					</div>
					<div class="panel-body">
						<span class="label label-pill label-info">内容简介:</span></h3>
						<c:out value="${temp['description']}" />
					</div>
				</div>
			</c:forEach>
		</div>
		<div class=text-center>
			<ul class="pagination">
				<c:forEach var="i" begin="1" end="${pagination.getTotalPages()}">
					<li class="${pagination.getPage()==i?'active':''}"><a
						href="<c:url value='/searchListPage/${pagination.getContent()}/${i}'/>">${i}</a>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html>
