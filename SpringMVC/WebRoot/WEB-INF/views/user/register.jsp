<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'register.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script src="jquery/jquery.js"></script>
<script src="jquery/jquery.validate.js"></script>
<script src="jquery/jquery.metadata.js"></script>
<script src="jquery/jquery.validate.messages_cn.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#registerForm").validate({meta:"validate"});
});
</script>
  </head>
  
  <body>
   <form id="registerForm" action="/SpringMVC/register" method="post">
    账号:<input type="text" name="userName" class="{validate:{required:true,minlength:6,messages:{required:'请输入账户',minlength:'长度至少6位'}}}"><br/>
     密码:<input type="password" name="password" class="{validate:{required:true,minlength:6,messages:{required:'请输入密码',minlength:'长度至少6位'}}}"><br/>
      邮箱:<input type="text" name="email" class="{validate:{required:true,email:true,messages:{required:'请输入kindle邮箱,以便发送电子书到您的kindle中'}}}"><br/>
      <input type="submit" value="注册" class="submit"><br/>
   </form>
  </body>
</html>
