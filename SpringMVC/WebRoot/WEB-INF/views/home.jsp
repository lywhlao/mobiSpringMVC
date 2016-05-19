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
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="jquery/jquery-1.12.3.min.js"></script>
<script>
$(document).ready(function(){
  $("#btn1").click(function(){
     $.post("/SpringMVC/sendEmail",{
     content:"www.hello",
     url:"www.world",
     author:"laojiaqi",
     description:"descrion"
     },function(data,status){ 
     alert("Data: " + data + "\nStatus: " + status);}
     );
  });
});
</script>
</head>

<body>
<div>你好,${userBean.getUserName()} </div>
<br/>
<button id="btn1" type="button">获得外部的内容</button>
<div id="test"></div>
	<c:forEach items="${list}" var="temp">
		<div>
			<c:out value="${temp}" />
		</div>
	</c:forEach>

	<form method="post" action="/SpringMVC/search">
		输入需要查找的mobi名称<input type="text" name="content" /><br /> <input
			type="radio" name="type" value="mobi" checked="checked" /> mobi<br />
		<input type="radio" name="type" value="torrent" /> torrent<br /> <input
			type="submit" value="开始搜索" /><br />
	</form>
	<a href="/SpringMVC/login" >登录</a>
	<br/>
	<a href="/SpringMVC/register" >注册新用户</a>
</body>
</html>
