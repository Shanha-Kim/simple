<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File Board Write</title>
<link rel="stylesheet" href="/css/w3.css" >
<script type="text/javascript" src="/js/jquery-3.4.1.min.js" ></script>
<style>
</style>
<script type="text/javascript">
	$(function(){
		$('#cancel').click(function(){
			$(location).attr('href', 'boardList.cls');
		});
		
		function s1(){
			var ttl = $('#title').val();
			var db = $('#body').val();
			
			if(!ttl || !db){
				alert('필수 입력 사항을 확인하세요!');
				return;
			}
			
			$('#frm').attr('method', 'post');
			$('#frm').attr('action', '/board/fboardWriteProc.cls');
			$('#frm').submit();
		}
		
		$('#submit').click(s1);
	});
</script>
</head>
<body>
<%-- 
	이곳은  jsp 페이지에서 사용하는 다중행 주석
 --%>
<!-- 
	이곳은 html 페이지에서 사용하는 다중행 주석
	
	이 화면은 글쓰기 폼을 보여주면 된다.
	
	파일게시판 글 입력요청은 GET 으로는 처리를 할 수 없고
	post 방식으로 입력요청을 해야 하는데...
	post 방식은 내부적으로 2가지 방식으로 나눠서 처리해 줄 수 있다.
	
		1. parameter 방식
			==> value 값만 서버에 [ 키값=데이터 ] 의 형태로 전달하는 방식
				꺼낼때는 
					req.getParameter("키값"); 
				로 꺼내면 된다.
				
		2. stream 방식
			==> 폼안에 있는 내용을 스트림으로처리해서 byte[]로 만들어서
				서버에 전달하는 방식
				
				따라서 서버에서는 스트림으로 만들어서 받아야 한다.
				
		파일 첨부가 있는 경우 parameter 방식으로 보내면
		파일 이름만 서버에 전달이 된다.
		(즉, 파일의 내용은 (실제 코드) 서버에 전달되지 않는다.)
		파일의 내용까지 서버에 전달하기 위해서 파일을 스트림으로 변경시켜서 전달해야 한다.
		
		enctype="muntipart/form-data"
		라는 속성은 스트림방식으로 처리하라는 의미를 가진 속성이다.
		
		스트림을 제어하기 위한 라이브러리가 많이 제공되고 있는데
		그중에 가장 많이 사용하는 라이브러리는
			1. cos.jar
				- 중복이름의 파일이 존재할 경우 파일이름을 자동으로 변경해서 처리해준다.
				
			2. fileupload.jar
				- 파일 리네임규칙을 따로 정의해서 지정해줘야 한다.
				
		두가지 라이브러가 대표적으로 많이 사용된다.
		
		결론]
			파일 첨부라는 기능은 
			파일의 내용을 서버에 전달하여
			서버가 그 파일을 원하는 위치에 저장하도록 하는 것이다.
			따라서 반드시 스트림 방식으로 전달해야 하며
			결론적으로 폼을 만들 때 
				enctype="multipart/form-data"
			라는 속성을 반드시 지정해야 한다.
 -->
	<div class="w3-col m3"><p></p></div>
	<div class="w3-col m6 w3-center">
		<h2 class="w3-col w3-padding w3-blue w3-margin-bottom">
			파일 업로드 게시판 글쓰기
		</h2>
		
		<div class="w3-col w3-padding w3-margin-bottom">
			<form id="frm" enctype="multipart/form-data" class="w3-col w3-card" >
				<input type="hidden" id="id" name="id" value="${SID}" >
				<div class="w3-row">
					<h4 class="w3-col m2 w3-right-align">Title : </h4>
					<div class="w3-col m10 w3-padding">
						<input class="w3-input w3-border" type="text" id="title" name="title" placeholder="# 제목을 입력하세요!">
					</div>
				</div>
				<div class="w3-row">
					<h4 class="w3-col m2 w3-right-align">Content : </h4>
					<div class="w3-col m10 w3-padding">
						<textarea class="w3-input w3-border" id="body" name="body" 
									placeholder="글 내용 입력" 
									cols="50" rows="10" style="resize: none"></textarea>
					</div>
				</div>
				<div class="w3-row w3-margin-top">
					<h4 class="w3-col m2 w3-right-align">File : </h4>
					<div class="w3-col m10 w3-padding">
						<input class="w3-input w3-border" type="file" id="file" name="file" 
																		placeholder="# 파일 선택!">
					</div>
				</div>
			</form>
			<div class="w3-col w3-margin-top">
				<div class="w3-button w3-small w3-red w3-hover-amber w3-left" id="cancel" >cancel</div>
				<div class="w3-button w3-small w3-green w3-hover-lime w3-right" id="submit">글등록</div>
			</div>
		</div>
	</div>
</body>
</html>