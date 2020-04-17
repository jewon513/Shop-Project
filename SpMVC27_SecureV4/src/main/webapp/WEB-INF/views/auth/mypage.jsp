<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>

<%@ include file="/WEB-INF/views/include/include-head.jspf"%>

<script type="text/javascript">
	$(function() {

		$("input").prop("readonly", true)
		
		
		$(document).on("click","#btn-save",function(){
			
			
			
			
		})
		
		
		$(document).on("click","#btn-update",function(){
			
			let pass = $("#password").val()
			
			if(pass == ""){
				
				alert("수정하려면 비밀번호를 입력한 후 다시 수정버튼을 클릭하세요")
				$("div.password").css("display","block")
				$("#password").prop("readonly",false)
				$("#password").focus()
				return false;
				
			}
			
			if(pass != ""){
				
				$.ajax({
					
					url : '${rootPath}/user/password',
					method : 'POST',
					data : {pass : pass,
							"${_csrf.parameterName}" : "${_csrf.token}"	
					},
					success : function(result){
						
						if(result == "PASS_OK"){
							
							$("input").prop("readonly",false)
							$("input").css("color","blue")
							$("#btn-save").prop("disabled",false)
							$("#btn-update").prop("disabled",true)
							
						}else{
							alert("비밀번호가 일치하지 않습니다.")
						}
						
					},
					error : function(error){
						
						alert("서버 통신 오류")
						
					}
					
				})
				
			}
			
			
		})
		

	})
</script>

<style type="text/css">

	form div.password{
		display: none;
	}


</style>


</head>
<body>

	<%@ include file="/WEB-INF/views/include/include-nav.jspf"%>

	<div class="container body">
		<div class="mt-5">

			<form:form modelAttribute="userVO">
				<div>
					<form:input path="username" />
				</div>
				<div class="password">
					
					<input id="password" type="password" placeholder="비밀번호를 입력......."/>
				</div>
				<div>
					<form:input path="email" placeholder="Email"/>
				</div>
				<div>
					<form:input path="phone" placeholder="전화번호"/>
				</div>
				<div>
					<form:input path="address" placeholder="주소" />
				</div>
				<div>
					<button type="button" id="btn-update">수정</button>
					<button type="submit" id="btn-save" disabled="disabled">저장</button>
					<button type="button" id="btn-loss_pass">비밀번호찾기</button>
				</div>
			</form:form>

		</div>
	</div>


</body>
</html>