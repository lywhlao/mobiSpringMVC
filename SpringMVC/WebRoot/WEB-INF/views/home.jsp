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

<title>欢迎来到kindle乐园</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
<link href="bootstrap/css/custom.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
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
		getRecommandContent();
	});
</script>

<script type="text/javascript">
	/*获得推荐内容 */
   function getRecommandContent(){
	var userName = "${userBean.getUserName()}";
	   $.ajax({
		headers : {
			Accept : "application/json"
		},
		url : "/SpringMVC/getRecommendContent",
		type : "POST",
		dataType : "json",
		success : function(response) {
			if(response.resultCode==0){
			    var ul=$("#recommend-ul");
				var list=response.data;
				for(item in list){
				     var $li_item=$("<a class='list-group-item text-center list-item' data-original-title='hi' data-trigger='hover'  rel='popover'></a>");
				     $li_item.text(list[item].contentDest);
				     $li_item.attr("href",list[item].url);
				     $li_item.attr("data-content",list[item].description.substr(0,200)+"...");
				     $li_item.attr("data-original-title",list[item].contentDest+"--"+list[item].author);
					 if(item<4){
						 $li_item.attr("data-placement","left");
					 }else{
					     $li_item.attr("data-placement","top");
					 }
					 ul.append($li_item);
					 $(".list-group-item").popover({trigger: "hover"});
				}
				$(".list-group-item").bind("click",function(){
				    var contentName=$(this).attr("data-original-title").split("--")[0];
					recordDownload(contentName)
				});
			}else{
			   

			}
		},
		error : function(xhr, ajaxOptions, thrownError) {
			  alert(xhr.status+thrownError);
		}
	});
	}
	//记录下载记录
	function recordDownload(value){
	$.post("/SpringMVC/recordDownload", {
				content : value
			}, function(data, status) {
				alert("Data: " + data + "\nStatus: " + status);
			});
	}
	
</script>

</head>

<body>
	<br>
	<br>
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
					<c:if test="${empty userBean.getUserName()}">
						<div class="col-md-3 col-md-offset-2">
							<a id="user_login" href="/SpringMVC/login" class="btn btn-success btn-lg"
							role="button">登录</a> 
							<a id="user_register" href="/SpringMVC/register" class="btn btn-info btn-lg" role="button">注册</a>
						</div>
					</c:if>
				</div>
				
			</div>
		</div>
	</div>
	<br>
	<br>
	<div class="container">
		<div class="row">
			<div class="col-sm-4">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">随便看看</h3>
					</div>
					<div class="panel-body">这是一个基本的面板</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="panel panel-danger">
					<div class="panel-heading">
						<h3 class="panel-title">热门下载</h3>
					</div>
					<div class="panel-body">这是一个基本的面板</div>
				</div>
			</div>
			<div class="col-sm-4">
				<ul class="list-group" id="recommend-ul">
				 	<li id="li_test" class="list-group-item list-group-item-success text-center list-title">猜你喜欢</li>
				</ul>
			</div>
		</div>
	</div>
	<footer class="container-fluid text-center">
  	<p>本站所有资源均来自互联网！！！</p>
		</footer>
</body>
</html>
