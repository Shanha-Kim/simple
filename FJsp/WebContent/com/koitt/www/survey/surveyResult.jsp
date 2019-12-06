<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문 결과 페이지</title>
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
		<h4 class="w3-center w3-red w3-card w3-padding">[ ${DATA.title} ]<br>- 결과 페이지 -</h4>
		<div class="w3-col"> <!-- 설문데이터 -->
			<c:forEach var="data" items="${LIST1}" varStatus="num">
			<div class="w3-col"> <!-- 문항데이터 -->
				<h5>${num.count}. ${data.body}</h5>
				<c:forEach var="vo" items="${LIST2.get(num.index)}" varStatus="cnt">
				<!-- 보기데이터 -->
				<div class="w3-col m1"><p></p></div>
				<div class="w3-col m11 w3-border-bottom">
					<div class="w3-col m4">
						<h6>${cnt.count} - ${vo.ebody}</h6>
					</div>
					<div class="w3-col m8">
						<div class="w3-orange" style="width: ${vo.per}%">
							<c:if test="${vo.per gt 0}">
								<p class="w3-center"><small>${vo.per} %</small></p>
							</c:if>
						</div>
					</div>
				</div>
				</c:forEach>
			</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>