<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1st Test</title>
<link rel="stylesheet" href="/css/w3.css" >
<script type="text/javascript" src="/js/jquery-3.4.1.min.js" ></script>
<script type="text/javascript">
	
	$(document).ready(function(){
		$('#login').click(function(){
			$(location).attr('href', '/member/login.cls');
		});
		$('#logout').click(function(){
			$(location).attr('href', '/member/logout.cls');
		});
		
	});
</script>
</head>
<body>
	<div class="w3-col m2"><p></p></div>
	<div class="w3-col m8 w3-center w3-margin-top">
		<h1 class="w3-col w3-blue">Welcome My Home!!!</h1>
		<div class="w3-col">
			<c:if test="${empty SID}">
				<div class="w3-col m2 w3-red w3-button" id="login">로그인</div>
				<div class="w3-col m2 w3-purple w3-button" id="join">회원가입</div>
			</c:if>
			<c:if test="${not empty SID}">
				<div class="w3-col m2 w3-pink w3-button" id="logout">로그아웃</div>
				<div class="w3-col m2 w3-deep-purple w3-button" id="memberInfo">회원정보보기</div>
			</c:if>
		</div>
	</div>
	
	
	<!-- 회원 상세정보 보기 -->
	<div class="w3-modal" id="detail">
		<div class="w3-modal-content">
			<div class="w3-contatiner w3-col w3-white w3-padding">
				<h2 class="w3-col w3-purple w3-padding w3-card">회원 정보</h2>
				<div class="w3-col w3-border-bottom">
					<h5 class="w3-col m3">회원번호 : </h5><h5 class="w3-col m9" id="mno"></h5>
				</div>
				<div class="w3-col w3-border-bottom">
					<h5 class="w3-col m3">아 이 디 : </h5>
					<h5 class="w3-col m9" id="mid"></h5>
				</div>
				<div class="w3-col w3-border-bottom">
					<h5 class="w3-col m3">회원이름 : </h5>
					<h5 class="w3-col m9" id="mname"></h5>
				</div>
				<div class="w3-col w3-border-bottom">
					<h5 class="w3-col m3">이 메 일 : </h5>
					<h5 class="w3-col m9" id="mmail"></h5>
				</div>
				<div class="w3-col w3-border-bottom">
					<h5 class="w3-col m3">전화번호 : </h5>
					<h5 class="w3-col m9" id="mtel"></h5>
				</div>
				<div class="w3-col w3-border-bottom w3-margin-bottom">
					<h5 class="w3-col m3">가 입 일 : </h5>
					<h5 class="w3-col m9" id="mdate"></h5>
				</div>
       			<span id="btn2" class="w3-button w3-display-topright w3-margin-right w3-margin-top">&times;</span>
			</div>
		</div>
	</div>
</body>
</html>