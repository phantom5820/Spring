<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생조회</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	
	$(function() {
		$("#btn_submit").click(function(e) {
			var data = $("#search").serialize();//kind=sno&name=검색어
			$.ajax({
				url : "studentSerach.do",
				data : data,
				method : 'get',
				dataType:'json',
				success : function(d) {
					console.log(d);
					var arr = d.result;
					console.log(arr);
					var result = "<table>";
					for(i=0;i<arr.length;i++){
						//한건당 한줄씩 표현
						result += 
						"<tr><td>"+arr[i].sno+"<input type='hidden' name='sno' value='"+arr[i].sno+"'></td>"
						+"<td><input type='text' name='name' value='"+arr[i].name+"'></td>"
						+"<td><input type='text' name='major' value='"+arr[i].major+"'></td>"
						+"<td><input type='text' name='score' value='"+arr[i].score+"'></td></tr>"
					}
					arr += "</table>";
					$("#content_area").html(result);			
				}
			});
			e.preventDefault();	
		});
	});	
</script>
<style type="text/css">
	.container{
		text-align: center;
	}

</style>
</head>
<body>

	
	<div class="container">
		<div id="menu_bar">
			<form id="search">
				<select name="kind">
					<option value="sno">학번</option>
					<option value="name">이름</option>
					<option value="major">학과</option>
				</select>
				<input type="text" name="search">
				<button id="btn_submit">검색</button>
				<button id="btn_reset">리셋</button>
			</form>
		</div>
		<hr>
			
			<div id="content_area">
				<table>
				<c:forEach var="student" items="${requestScope.list }">
						<tr> 
							<td>${student.sno }</td>
							<td>${student.name }</td>
							<td>${student.major }</td>
							<td>${student.score }</td>
						</tr>
				</c:forEach>
				
				</table>
			</div>
		</div>
		
</body>
</html>