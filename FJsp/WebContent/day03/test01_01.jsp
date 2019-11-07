<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Form Tag test</title>
<link rel="stylesheet" href="/css/w3.css" >
<script type="text/javascript" src="/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#btn2').click(function(){
			// 입력값 읽고
			var sid = $('#id1').val();
			var spw = $('#pw1').val();
			
			// 입력값 넣어주고
			$('#id2').val(sid);
			$('#pw2').val(spw);
			alert($('#id2').val() + ' - ' + $('#pw2').val() );
		});
		
		
	});
</script>
</head>
<body>
	<div class="w3-col m3"><p></p></div>
	<div  class="w3-container  w3-col w3-half">
		<form method="GET" action="submit-target.jsp">
			<div class="w3-content" ><span class="w3-col m2 w3-right-align">I D :</span><input type="text" name="id" class="w3-col m10"></div>
			<div class="w3-content" ><span class="w3-col m2 w3-right-align">P W :</span><input type="password" name="pw" class="w3-col m10"></div>
			<div class="w3-content" ><span class="w3-col m2 w3-right-align">MSG :</span><div class="w3-rest" name="d1" id="d1">fdjksa;jdfipsa</div></div>
			<input type="submit" value="btn1">
		</form>
		<hr>
		<form method="GET" action="submit-target.jsp">
			<div class="w3-content" ><span class="w3-col m2 w3-right-align">I D :</span><input type="text" name="id" id="id1"></div>
			<div class="w3-content" ><span class="w3-col m2 w3-right-align">P W :</span><input type="password" name="pw" id="pw1"></div>
			<input type="button" value="btn2" id="btn2">
		</form>
		<hr><br>
		<form method="GET" action="submit-target.jsp">
			<input type="hidden" name="id" id="id2" value="taeeun">
			<input type="hidden" name="pw" id="pw2" value="9999">
			<input type="submit" value="btn3">
		</form>
	</div>
</body>

</html>