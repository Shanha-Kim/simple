<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Target2</title>
<link rel="stylesheet" href="/css/w3.css" >
<script type="text/javascript" src="/js/jquery-3.4.1.min.js" ></script>
<style>
</style>
<script type="text/javascript">
</script>
</head>
<body>
	<div class="w3-col m3"><p></p></div>
	<div class="w3-col m6 w3-center">
		<h2 class="w3-amber w3-card">Target2</h2>
		<div class="w3-container w3-card">
			<h3 class="w3-col m4">Session ID : </h3>
			<h3 class="w3-col m8"><%= session.getAttribute("SID") %></h3>
		</div>
	</div>
</body>
</html>