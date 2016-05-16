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
<script type="text/javascript">
	function sendToEmail() {
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				document.getElementById("myDiv").innerHTML = xmlhttp.responseText;
			}
		}
		xmlhttp.open("GET", "/SpringMVC/sendEmail", true);
		xmlhttp.send();
	}
</script>
</head>

<body>
	<c:forEach items="${pagination.getResultList()}" var="temp">
		<div>
			<c:out value="《${temp['content']}》" />
			<c:out value="作者: ${temp['author']}" />
			<a href="<c:url value="${temp['url']}"/>"><c:out
					value="${temp['url']}" /></a> 
			<button type="button" onclick="sendToEmail()">发送到邮箱</button>
			<br>
			<c:out value="内容简介: ${temp['description']}" />
		</div>
		<br />
		<br />
	</c:forEach>
	<c:forEach var="i" begin="1" end="${pagination.getTotalPages()}">
		<a
			href="<c:url value='/searchListPage/${pagination.getContent()}/${i}'/>">${i}</a>
	</c:forEach>
	<div id="myDiv"></div>
</body>
</html>
