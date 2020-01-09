<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Target1</title>
<link rel="stylesheet" href="/css/w3.css" >
<script type="text/javascript" src="/js/jquery-3.4.1.min.js" ></script>
<style>
</style>
<%
	response.sendRedirect("target2.jsp");
%>
<script type="text/javascript">
	/*
	// 자바스크립트로 리다이렉트 시키는 방법
	location.href = 'target2.jsp';
	*/
	
	/*
	// jquery 에서 리다이렉트 시키기
	$(function(){
		$(location).attr('href', 'target2.jsp');
	});
	*/
</script>
</head>
<body>
	<div class="w3-col m3"><p></p></div>
	<div class="w3-col m6 w3-center">
		<h2 class="w3-light-blue">Target1</h2>
	</div>
</body>
</html>