<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CLS Project FileBoard</title>
<link rel="stylesheet" href="/css/w3.css" >
<script type="text/javascript" src="/js/jquery-3.4.1.min.js" ></script>
<style>

</style>
<script type="text/javascript">
	$(function(){
		$('#hbtn').click(function(){
			$(location).attr('href', '/main');
		});
		$('#login').click(function(){
			$(location).attr('href', '/member/login.cls');
		});
		
		$('#wbtn').click(function(){
			location.href = '/board/boardWrite.cls';
		});
	});
</script>
</head>
<body>
	<div class="w3-col m3"><p></p></div>
	<div class="w3-col m6 w3-center">
		<h2 class="w3-col w3-padding w3-blue">파일 업로드 게시판</h2>
		
		<div class="w3-content w3-padding w3-margin-bottom">
			<div class="w3-button w3-small w3-orange w3-left" id="hbtn">Home</div>
			<c:if test="${empty SID}">
				<div class="w3-button w3-small w3-red w3-left" id="login">로그인</div>
			</c:if>
			<c:if test="${not empty SID}">
				<div class="w3-button w3-small w3-blue w3-right" id="wbtn">글쓰기</div>
			</c:if>
		</div>
		
		<div class="w3-col w3-padding w3-margin-bottom">
			<div class="w3-row w3-border"> <!-- 글번호 | 작성자 | 작성일 | 타이틀 -->
				<div class="w3-col m2 w3-border-right">글번호</div>
				<div class="w3-col m2 w3-border-right">작성자</div>
				<div class="w3-col m2 w3-border-right">작성일</div>
				<div class="w3-col m6">글제목</div>
			</div>
			
	<c:forEach var="data" items="${LIST}">
			<div class="w3-row w3-border" id="${data.bno}">
				<div class="w3-col m2 w3-border-right">${data.bno}</div>
				<div class="w3-col m2 w3-border-right">${data.id}</div>
				<div class="w3-col m2 w3-border-right">${data.wdate}</div>
				<div class="w3-col m6">${data.title}</div>
			</div>
	</c:forEach>
		</div>
	</div>
</body>
</html>