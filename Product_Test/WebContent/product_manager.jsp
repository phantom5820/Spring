<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 입력 페이지</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(function(){
		$("button").click(function() {
			var param = $("#frm").serialize();
			$.ajax({
				url : "productInput.do",
				data : param,
				method : "get",
				dataType:"json",
				success:function(d){
					console.log(d);
					var arr = d.result;
					var text = "";
					for(i=0;i<arr.length;i++){
						text += arr[i].pno + " " + arr[i].pname + " " + arr[i].maker + " " + arr[i].price + " " + arr[i].ea + "<br>"; 
					}
					$("div").html(text)
				},
				//exception 처리
				error:function(xhr,text,error){
					//alert(xhr.status);//에러코드 확인
					switch(xhr.status){
					case 1001:
						alert(xhr.responseText);//
						break;
					case 1002:
						alert(xhr.responseText);//에러 메세지 수신
						break;
					case 1003:
						alert(xhr.responseText);//에러 메세지 수신
						break;
					}
				}
				
			});
		});
	});
	
</script>
</head>
<body>
	
	
	
	
	<form id="frm">
		<input type="text" name = "pno" placeholder="상품번호 입력">
		<input type="text" name = "pname" placeholder="상품명 입력">
		<input type="text" name = "maker" placeholder="제조사 입력">
		<input type="text" name = "price" placeholder="금액 입력">
		<input type="text" name = "ea" placeholder="재고 개수 입력">
		<button type="button">상품등록</button>
	</form>
	<hr>
	<div>
	
	</div>
</body>
</html>