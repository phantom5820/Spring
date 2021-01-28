<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
  <script type="text/javascript">
  	$(function(){
  		$("button").click(function(){
  			var data = $("frm").serialize();
  			$.ajax({
  				url: "search.do",
  				data: param,
  				method: "get",
  				success: function(result){
  					$("div").html(result);
  				},
  				error:function(xhr,text,result){
  					$("div").html(xhr.status +" "+ xhr.responseText + " " + text);
  					
  				}
  			});
  		});
  	});
  </script>
</head>
<body>
	<form id="frm">
		이름 : <input type="text" name="name"> <button type="button">검색</button>
	</form>
	<hr>
	<div>
		
	</div>
</body>
</html>