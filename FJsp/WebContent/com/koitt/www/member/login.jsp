<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/w3.css" >
<script type="text/javascript" src="/js/jquery-3.4.1.min.js" ></script>
<style>
</style>
<script type="text/javascript">
</script>
</head>
<body>
	<!-- 로그인 폼 페이지 -->
	<form method="POST" action="/member/loginProc.nop" id="frm"  >
		<div>
			I D : <input type="text" id="id" name="id">
		</div>
		<div>
			PW : <input type="text" id="pw" name="pw">
		</div>
	</form>
</body>
</html>