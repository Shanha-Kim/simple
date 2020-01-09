<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Submit Target Page</title>
<link rel="stylesheet" href="/css/w3.css" >
<script type="text/javascript" src="/js/jquery-3.4.1.min.js" ></script>
<style>
</style>
<!-- 
	submit-target.jsp 에서 버튼을 클릭하면
	아이디와 비밀번호를 이페이지로 전달하도록 처리한다.
		
		1. get 방식으로 넘기기
		
		2. POST 방식으로 넘기기
 -->
<script type="text/javascript">
	$(function(){
		$('#id').text('<%= request.getParameter("id") %>'); 
		$('#pw').text('<%= request.getParameter("pw") %>'); 
	});
</script>
</head>
<body>
	<div class="w3-col m3"><p></p></div>
	<div class="w3-col w3-half w3-margin-top">
		<h1 class="w3-container w3-col w3-orange w3-card w3-round2 w3-center">Parameter 받기</h1>
		<div class="w3-container w3-col w3-card w3-round-large">
			<div>
			<h3 class="w3-col m3 w3-right-align">I D : </h3>
			<h3 class="w3-col m8" id="id"></h3>
			</div>
			<div>
			<h3 class="w3-col m3 w3-right-align">P W : </h3>
			<h3 class="w3-col m8" id="pw" ></h3>
			</div>
		</div>
	</div>
</body>
</html>