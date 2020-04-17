<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />

<script type="text/javascript">
	$(function() {
		
		
		
		

	})
</script>

<style type="text/css">
form div.password {
	display: none;
}

form input.auth {
	display: block;
}
</style>

<div class="container body">
	<div class="mt-5 mb-5">

		<form:form modelAttribute="userVO">
			<div class="form-group">
				<label for="username">UserName</label>
				<form:input path="username" readonly="true" class="form-control" />
			</div>
			<div class="form-group">
				<label for="email">email</label>
				<form:input path="email" class="form-control" />
			</div>
			<div class="form-group">
				<label for="phone">phone</label>
				<form:input path="phone" class="form-control" />
			</div>
			<div class="form-group">
				<label for="address">address</label>
				<form:input path="address" class="form-control" />
			</div>
			<div class="form-group">
				<label for="enabled">계정활성화</label>
				<form:checkbox path="enabled" class="form-control" />
			</div>
			<div class="form-group">
				<button id="auth_append" class="btn btn-danger" type="button">권한 정보 입력 추가</button>
			</div>
			<div id="auth_box" class="form-group">
				<c:if test="${not empty userVO.authorities}">
					<c:forEach items="${userVO.authorities}" var="auth">
						<div class="form-group">
							<input name="auth" value="${auth.authority}" class="form-control">
						</div>
					</c:forEach>
				</c:if>
			</div>
			<div class="form-group">
				<button type="button" id="btn-update" class="btn btn-primary">수정</button>
				<button type="button" id="btn-save" class="btn btn-primary">저장</button>
			</div>
		</form:form>

	</div>
</div>
