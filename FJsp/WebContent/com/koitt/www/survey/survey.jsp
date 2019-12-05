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
	<div class="w3-col m3"><p></p></div>
	<div class="w3-half">
		<h3 class="w3-padding w3-lime">설문 조사 리스트</h3>
		<div class="w3-col w3-margin-top">
			
			<div class="w3-col w3-card w3-round w3-margin-bottom w3-padding">
				<c:forEach var="data" items="${LIST}">
					<h3 class="w3-col m9">${data.title}</h3>
					<c:if test="${data.check eq 'Y'}">
						<div class="w3-col m2 w3-button w3-right btn w3-blue" id="${data.sno}">결 과</div>
					</c:if>
					<c:if test="${data.check eq 'N'}">
						<div class="w3-col m2 w3-button w3-right btn w3-amber" id="${data.sno}">참 여</div>
					</c:if>
				</c:forEach>
			</div>
			
		</div>
	</div>
</body>
</html>