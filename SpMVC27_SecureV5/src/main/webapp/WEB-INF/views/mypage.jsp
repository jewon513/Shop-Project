<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>

<%@ include file="/WEB-INF/views/include/include-head.jspf"%>

</head>
<body>

	<%@ include file="/WEB-INF/views/include/include-nav.jspf"%>

	<div class="container body">
		<div class="mt-3">
			<h2>회원정보</h2>
		</div>
		
		<hr>

		<div>
			
			<div class="row">
			
			</div>
			
			
			<p>아이디: ${userVO.username}</p>
			<p>사용여부: ${userVO.enabled}</p>
			<p>accountNonExpired: ${userVO.accountNonExpired}</p>
			<p>accountNonLocked: ${userVO.accountNonLocked}</p>
			<p>credentialsNonExpired: ${userVO.credentialsNonExpired}</p>
			<c:forEach items="${userVO.authorities}" var="vo">
				<p>${vo}</p>
			</c:forEach>
			<p>이메일: ${userVO.email}</p>
			<p>전화번호: ${userVO.phone}</p>
			<p>주소: ${userVO.address}</p>

		</div>
		
		<div class="d-flex justify-content-end">
			<a href="${rootPath}/user/mypageUpdate"><button class="btn btn-primary">수정</button></a>
		</div>
		
		
	</div>


</body>
</html>