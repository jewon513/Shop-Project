<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>

<%@ include file="/WEB-INF/views/include/include-head.jspf"%>

</head>

<style>
	
	#secret {
		display: none;
	}

</style>

<script type="text/javascript">

	$(document).on("click","#btn_email_ok",function(){
		
		let secret_key = $("#secret").text()
		let secret_value = $("#email_ok").val()
		
		if(secret_value ==""){
			alert("인증코드를 입력한 후 인증 버튼을 클릭하세요")
			$("#email_ok").focus()
			return false;
		}
		
		$.ajax({
			
			url : "${rootPath}/join/email_token_check",
			type : "POST",
			data : {
				
				"${_csrf.parameterName}" : "${_csrf.token}",
				secret_key : secret_key,
				secret_value : secret_value,
				secret_id : "${username}"
				
			},
			success : function(result){
				if(result == "OK"){
					document.location.replace("${rootPath}/user/login")	
				}else{
					alert("인증실패")
				}
				
			},
			error : function(error){
				alert("서버통신오류")
			}
			
		})
		
		
	})

</script>

<body>

	<%@ include file="/WEB-INF/views/include/include-nav.jspf"%>

	<div class="container body">
		<div class="mt-3">
			<h2>Email 인증</h2>
			<div class="form-group">회원가입을 완료하려면 Email 인증을 수행해야 합니다</div>
			<div>

				<form:form modelAttribute="userVO" action="${rootPath}/join/join_last">

					<div class="form-group">
						<form:input type="email" path="email" placeholder="email" class="form-control" />
					</div>

					<c:choose>
						<c:when test="${JOIN=='EMAIL_OK'}">
							<div class="form-group">
								<div class="d-flex justify-content-end">
									<button class="btn btn-primary">인증 Email 다시보내기</button>
								</div>
								<div class="form-group">E-mail을 열어서 인증코드를 확인한 후 아래 입력란에 입력 후 인증 버튼을 클릭하세요</div>
								<div class="form-group">
									<span id="secret">${My_Email_Secret}</span>
									<input class="form-control" id="email_ok">
								</div>
								<div class="d-flex justify-content-end">
									<button type="button" id="btn_email_ok" class="btn btn-primary">인증하기</button>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="form-group">
								<div class="d-flex justify-content-end">
									<button class="btn btn-primary">인증 Email 보내기</button>
								</div>
							</div>
						</c:otherwise>

					</c:choose>

				</form:form>

			</div>
		</div>
	</div>
</body>
</html>