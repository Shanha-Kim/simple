<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/w3.css" >
<script type="text/javascript" src="/js/jquery-3.4.1.min.js" ></script>
<style>
</style>
<%
	// 세션에 dooly 를 기억해보자
	session.setAttribute("SID", "dooly");
%>
</head>
<body>
	<div class="w3-col m3"><p></p></div>
	<div class="w3-col m6 w3-center">
		<h2 class="w3-col w3-lime w3-card w3-margin-bottom">Redirect Test start</h2>
		<div class="w3-col w3-button w3-blue" id="btn">target1.jsp</div>
	</div>
</body>
<script type="text/javascript">
	document.getElementById('btn').onclick = function(){
		location.href = 'target1.jsp';
	};
</script>
</html>