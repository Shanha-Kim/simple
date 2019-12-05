<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*, com.koitt.www.vo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문 참여 페이지</title>
<link rel="stylesheet" href="/css/w3.css" >
<script type="text/javascript" src="/js/jquery-3.4.1.min.js" ></script>
<style>
</style>
<script type="text/javascript">
</script>
</head>
<body>
	<div class="w3-col m3"><p></p></div>
	<div class="w3-half">
		<h4 class="w3-center w3-pink w3-card w3-padding">[ ${TITLE} ]<br>- 참여 페이지 -</h4>
		<div class="w3-col w3-padding">
			<form method="post" action="/survey/surveyProc.cls" id="frm">
			<c:forEach var="data" items="${LIST}" varStatus="status">
				<div class="w3-col"><h5>${status.count}. ${data.body}</h5></div>
				<div class="w3-col m2"><p></p></div>
				<div class="w3-col m10">
				<c:forEach var="vo" items="${data.list}">
					<div class="w3-col">
						<input type="radio" class="w3-col m1 w3-radio" name="${data.qno}" value="${vo.seno}">
						<h6 class="w3-col m11"> ${vo.ebody}</h6>
					</div>
				</c:forEach> 
				</div>
			</c:forEach>
			</form>
			<div class="w3-col w3-center">
				<div class="w3-button w3-red btn">취소</div>
				<div class="w3-button w3-blue btn">제출</div>
			</div>
		</div>
	</div>
</body>
</html>