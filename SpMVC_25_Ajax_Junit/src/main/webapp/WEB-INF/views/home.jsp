<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script>

<script type="text/javascript">

	$(function(){
		$("button[type='button'].btn-userId").on("click",function(){
			//alert("ajax 버튼 클릭")
			
			$.ajax({
				
				url : "sendUserId",
				type :"POST",
				data : {userId : $("#userId").val()},
				success : function(result){
					alert(result.RET_CODE)
					if(result.RET_CODE == "RECV_OK"){
						alert("사용자 ID : " + result.userVO.userId + "\n")
					}
				},
				error : function(result){
					
				}
				
			})
			
		})
		
		$(".user").click(function(){
			
			$.ajax({
				url : "sendUser",
				data : $("form").serialize(),
				type : "POST",
				success : function(userVO){
					
					let html = "<p>" + userVO.userId +"</p>"
					html += "<p>" + userVO.password +"</p>"
					html += "<p>" + userVO.userName +"</p>"
					html += "<p>" + userVO.userRole +"</p>"
					
					$("#ret_html").html(html)
				},
				error : function(result){
					
				}
			})
			
		})
		
		$(".user_html").click(function(){
			
			$.ajax({
				url : "html",
				data : $("form").serialize(),
				type : "POST",
				success : function(result){
					
					$("#ret_html").html(result)
					
				},
				error : function(result){
					
				}
			})
			
		})
		
		
	})

</script>

</head>
<body>

	<section>
		
		<h2>사용자 정보</h2>
		<h5>사용자 ID:${userVO.userId}</h5>
		<h5>비밀번호: ${userVO.password}</h5>
		<h5>사용자 이름: ${userVO.userName}</h5>
		<h5>사용자 권한: ${userVO.userRole}</h5>
	
	</section>
	<section>
	
		<form action="saveUser" method="post">
			
			<div>
				<input placeholder="사용자 ID" name="userId" id="userId">
			</div>
			<div>
				<input placeholder="비밀번호" name="password"  id="password">
			</div>
			<div>
				<input placeholder="사용자이름" name="userName"  id="userName">
			</div>
			<div>
				<input placeholder="사용자권한" name="userRole"  id="userRole">
			</div>
			<div>
				<button>저장</button>
				<button type="button" class="btn-userId">사용자 ID 전송</button>
				<button type="button" class="user">입력값 전송</button>
				<button type="button" class="user_html">입력 HTML로 받기</button>
			</div>
			
		</form>
	
	</section>
	
	<section id="ret_html">
	
	</section>
	
</body>
</html>