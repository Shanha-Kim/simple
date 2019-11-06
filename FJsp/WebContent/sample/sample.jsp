<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sample Test</title>
<script type="text/javascript">
	location.href = '../?id=aaa&pw=1234';
</script>
</head>
<body>
	<center>
		<h3>Sample Test</h3>
	</center>
	
	<% for(int i = 0 ; i < 5 ; i++ ){ %>
		<h3><%=i+1 %> 번째 태그</h3>
	<% } %>
</body>
</html>