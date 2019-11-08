<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="DB.*, sql.*, vo.*, java.util.*, java.sql.*" %>
<!DOCTYPE html>
<html>
<%
	MyJDBC db = new MyJDBC();
	Connection con = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = EmpSQL.getSQL(EmpSQL.SEL_ALL);
	ArrayList<EmpVO> list = new ArrayList<EmpVO>();
	try{
		con = db.getCON();
		stmt = db.getSTMT(con);
		rs = stmt.executeQuery(sql);
		while(rs.next()){
			EmpVO vo = new EmpVO();
			vo.setEno(rs.getInt("empno"));
			vo.setEname(rs.getString("ename"));
			vo.setJob(rs.getString("job"));
			vo.setMgr(rs.getInt("mgr"));
			vo.setHiredate(rs.getDate("hiredate"));
			vo.setsDate();
			vo.setSal(rs.getInt("sal"));
			vo.setComm(rs.getInt("comm"));
			vo.setDno(rs.getInt("deptno"));
			
			list.add(vo);
		}
	} catch(Exception e){
		e.printStackTrace();
	} finally{
		db.close(rs);
		db.close(stmt);
		db.close(con);
	}
	
	request.setAttribute("LIST", list);
%>
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
	<div class="w3-col m6 w3-center">
		<h1 class="w3-margin-top w3-margin-bottom w3-amber w3-card-4 w3-round-large">사원 리스트</h1>
		<div class="w3-card-4 w3-round-large">
			<!-- 타이틀 -->
			<div class="w3-row w3-border-bottom">
				<div class="w3-col m1 w3-light-grey w3-border-right"><small>사원번호</small></div>
				<div class="w3-col m2 w3-light-grey w3-border-right"><small>사원이름</small></div>
				<div class="w3-col m2 w3-light-grey w3-border-right"><small>직 급</small></div>
				<div class="w3-col m1 w3-light-grey w3-border-right"><small>상사번호</small></div>
				<div class="w3-col m3 w3-light-grey w3-border-right"><small>입 사 일</small></div>
				<div class="w3-col m1 w3-light-grey w3-border-right"><small>급 여</small></div>
				<div class="w3-col m1 w3-light-grey w3-border-right"><small>커미션</small></div>
				<div class="w3-col m1 w3-light-grey"><small>부서</small></div>
			</div>
			
		<c:forEach var="data" items="${LIST}">
			<!-- data -->
			<div class="w3-row w3-border-bottom">
				<div class="w3-col m1 w3-border-right"><small>${data.eno}</small></div>
				<div class="w3-col m2 w3-border-right"><small>${data.ename}</small></div>
				<div class="w3-col m2 w3-border-right"><small>${data.job }</small></div>
				<div class="w3-col m1 w3-border-right">
					<small>
								<c:if test="${data.mgr == -999}">사장</c:if>
								<c:if test="${data.mgr != -999}">${data.mgr}</c:if>
					</small>
				</div>
				<div class="w3-col m3 w3-border-right"><small>${data.sDate }</small></div>
				<div class="w3-col m1 w3-border-right"><small>${data.sal }</small></div>
				<div class="w3-col m1 w3-border-right">
					<small>
						<c:if test="${data.comm == -999 }">없음</c:if>
						<c:if test="${data.comm != -999}">${data.comm}</c:if>
					</small>
				</div>
				<div class="w3-col m1"><small>${data.dno }</small></div>
			</div>
		</c:forEach>
			
			
			
		</div>
	</div>
</body>
</html>