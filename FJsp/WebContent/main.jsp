<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%-- <%@ page %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1st Test</title>

<script type="text/javascript">
	var sid = '<%= request.getParameter("id") %>';
	var spw = '<%= request.getParameter("pw") %>';
	
	alert(sid + ' - ' + spw);
</script>
</head>
<body>
	<center>
		<h1>Welcome My Home!!!</h1>
	</center>
</body>
</html>