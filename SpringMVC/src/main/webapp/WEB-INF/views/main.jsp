<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(function(){
		$("#btnDate").click(function(){
		$.ajax({
			url: "ajax.do",
			method: "get",
			success:function(d){
				$("span").html(d);
			}
		});//ajax
		});//click
	});
</script>
</head>
<body>

	<form action="loginAction.do" method="post">
		아이디 : <input type="text" name="id" placeholder="아이디를 입력하세요"><br>
		비밀번호 : <input type="password" name="pass" placeholder="비밀번호를 입력하세요"><br>
		<button>로그인</button>
	</form>
	${sessionScope.id },${sessionScope.pass },<%=session.getId() %>
	<br>
	<button id="btnDate">날짜 시간 조회</button><span></span>
</body>
</html>