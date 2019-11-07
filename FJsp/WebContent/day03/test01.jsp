<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Form Tag test</title>
<link rel="stylesheet" href="/css/w3.css" >
</head>
<body>
	<div class="w3-col m3"><p></p></div>
	<div  class="w3-container w3-col w3-half">
		<form method="GET" action="submit-target.jsp" class="w3-center">
			<div class="w3-content" ><span class="w3-col m2 w3-right-align">I D :</span><input type="text" name="id" class="w3-col m10"></div>
			<div class="w3-content" ><span class="w3-col m2 w3-right-align">P W :</span><input type="password" name="pw" class="w3-col m10"></div>
			<!-- <div class="w3-content" ><span class="w3-col m2">MSG :</span><div class="w3-col w3-rest" name="d1" id="d1">fdjksa;jdfipsa</div></div> -->
			<input type="submit" value="btn1" >
		</form>
		
		<form method="POST" action="submit-target.jsp" class="w3-center">
			<div class="w3-content" ><span class="w3-col m2 w3-right-align">I D :</span><input type="text" name="id" class="w3-col m10"></div>
			<div class="w3-content" ><span class="w3-col m2 w3-right-align">P W :</span><input type="password" name="pw" class="w3-col m10"></div>
			<input type="submit" value="btn1-01">
		</form>
		
		<hr>
		<form method="GET" action="submit-target.jsp" class="w3-center">
			<div class="w3-content" ><span class="w3-col m2 w3-right-align">I D :</span><input type="text" name="id" id="id1" class="w3-col m10"></div>
			<div class="w3-content" ><span class="w3-col m2 w3-right-align">P W :</span><input type="password" name="pw" id="pw1" class="w3-col m10"></div>
			<input type="button" value="btn2" id="btn2"	>
		</form>
		<hr><br>
		<form method="GET" action="submit-target.jsp" class="w3-center">
			<input type="hidden" name="id" id="id2" value="taeeun">
			<input type="hidden" name="pw" id="pw2" value="9999">
			<input type="submit" value="btn3" class="w3-right">
		</form>
	</div>
</body>
<script type="text/javascript">
	var el = document.getElementById('btn2');
	el.onclick = function(){
		// 아이디값 읽고
		var sid = document.getElementById('id1').value;
		var spw = document.getElementById('pw1').value;
		
		document.getElementById('id2').value = sid;
		document.getElementById('pw2').value = spw;
		
		var sid1 = document.getElementById('id2').value;
		var spw1 = document.getElementById('pw2').value;
		
		alert(sid1 + ' - ' + spw1);
	};
</script>
</html>