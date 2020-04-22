<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="/WEB-INF/views/include/include-head.jspf"%>




</head>
<body>

	<%@ include file="/WEB-INF/views/include/include-nav.jspf"%>


	<style>
#body {
	height: 2000px;
}
</style>

	<div class="container body">
		<div class="mt-3">
			<h2>회원정보 수정</h2>
		</div>

		<hr>

		<form:form>
			<div class="form-group">
				<label>아이디</label> <input class="form-control" value="${userVO.username}" readonly="readonly">
			</div>

			<div class="form-group">
				<label>이메일</label> <input class="form-control" value="${userVO.email}">
			</div>

			<div class="form-group">
				<label>전화번호</label> <input class="form-control" value="${userVO.phone}">
			</div>

			<div class="form-group">
				<label>주소</label> <input class="form-control" value="${userVO.address}">
			</div>

			<div class="d-flex justify-content-end">
				<a href="${rootPath}/user/mypageUpdate"><button class="btn btn-primary">수정</button></a>
			</div>

		</form:form>

	</div>


</body>
</html>