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
<%
	String sid = request.getParameter("id");
	String spw = request.getParameter("pw");
%>
<script type="text/javascript">
	$(function(){
		$('#id').text('<%= sid %>');
		$('#pw').text('<%= spw %>');
/*		
		// jquery  GET 방식
		$('#btn').click(function(){
			var sid = $('#id').html();
			var spw = $('#pw').text();
			
			$(location).attr('href', 'submit-retarget.jsp?id=' + sid + '&pw=' + spw);
		});
*/
		// 3. form 태그를 이용하는 방법
		$('#btn').click(function(){
		/*
			// 1) GET 방식
			// 데이터 읽어오고
			var sid = $('#id').html();
			var spw = $('#pw').text();
			
			// 인풋태그에 데이터 채워넣고
			$('#tid').val(sid);
			$('#tpw').val(spw);
			
			// 폼태그 넘기고
			$('#frm').submit();
		*/
			// 2. POST 방식
			// 데이터 읽어오고
			var sid = $('#id').html();
			var spw = $('#pw').text();
			
			// 데이터 채우고
			$('#tid1').val(sid);
			$('#tpw1').val(spw);
			
			// 폼태그의 방식을 설정하고
			$('#frm1').attr('method', 'POST');
			// 폼태그의 넘겨질 주소 설정하고
			$('#frm1').attr('action', 'submit-retarget.jsp');
			// 폼태그 넘기고
			$('#frm1').submit();
		});
		
	});
	

</script>
</head>
<body>
	<form method="GET" action="submit-retarget.jsp" id="frm">
		<input type="hidden" id="tid" name="id">
		<input type="hidden" id="tpw" name="pw">
	</form>
	<form id="frm1">
		<input type="hidden" id="tid1" name="id">
		<input type="hidden" id="tpw1" name="pw">
	</form>
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
		<div class="w3-col w3-button w3-blue w3-margin-top w3-card" id="btn">넘기기</div>
	</div>
</body>

<!-- 
<script type="text/javascript">
	// 1. 자바스크립로 전송하기
	
	document.getElementById('btn').onclick = function(){
		var sid = document.getElementById('id').innerHTML;
		var spw = document.getElementById('pw').innerText;
/* 		var spw = document.getElementById('pw').textContent; */
		
		// location 객체를 사용해서 전송한다.
		location.href = 'submit-retarget.jsp?id=' + sid + '&pw=' + spw;
	};
</script>
 -->
</html>