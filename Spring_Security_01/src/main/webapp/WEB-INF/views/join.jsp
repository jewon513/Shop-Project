<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<%@ include file="/WEB-INF/views/inlcude/include-head.jsp"%>

</head>

<style type="text/css">
.login-box {
	width: 500px;
}
</style>

<body>

	<!-- Navigation -->
	<%@ include file="/WEB-INF/views/inlcude/include-navigation.jsp"%>

	<!-- container -->
	<div class="container">

		<form method="POST">
			<div class="login-box ml-auto mr-auto mt-5 mb-5 text-center border border-secondary pt-5 pb-5 pl-3 pr-3">
				<h2 class="m-3">Join</h2>
				<div class="form-group">
					<input class="form-control border border-secondary" type="text" name="userid" placeholder="ID">
				</div>
				<div class="form-group">
					<input class="form-control border border-secondary" type="password" name="userpw" placeholder="Password">
				</div>
				<div class="form-group">
					<input class="form-control border border-secondary" type="text" name="username" placeholder="Name">
				</div>
				<div class="d-flex justify-content-center">
					<button class="btn btn-primary">Welcome</button>
				</div>
			</div>
			
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			
		</form>

	</div>

</body>
</html>