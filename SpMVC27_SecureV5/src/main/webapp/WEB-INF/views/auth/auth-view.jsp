<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<%@ include file="/WEB-INF/views/include/include-head.jspf"%>

</head>
<body>

	<%@ include file="/WEB-INF/views/include/include-nav.jspf"%>

	<div class="container body">
		<div class="mt-5">
			<h1>로그인 동적 정보 보기</h1>

			<sec:authorize access="isAnonymous()">
				<p>로그인 하지 않았을때 보이는 정보</p>

			</sec:authorize>

			<sec:authorize access="isAuthenticated()">
				<h3>로그인 했을때 보이는 정보</h3>
				<p>
					Principal :
					<sec:authentication property="principal" />
				</p>
				<p>
					로그인 User name :
					<sec:authentication property="principal.username" />
				</p>
				<p>
					로그인 User 비밀번호 :
					<sec:authentication property="principal.password" />
				</p>
				<p>
					로그인 User Email :
					<sec:authentication property="principal.email" />
				</p>
				<p>
					로그인 User 전화번호 :
					<sec:authentication property="principal.phone" />
				</p>
				<p>
					로그인 User address :
					<sec:authentication property="principal.address" />
				</p>
				<p>
					로그인 User 권한리스트 :
					<sec:authentication property="principal.authorities" />
				</p>
			</sec:authorize>

			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<h3>ADMIN으로 로그인 했을때 보이는 정보</h3>
				
			</sec:authorize>

			<sec:authorize access="hasRole('ROLE_USER')">
				<h3>USER로그인 했을때 보이는 정보</h3>
			</sec:authorize>

			<sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
				<h3>ADMIN, USER로그인 했을때 보이는 정보</h3>
			</sec:authorize>

		</div>
	</div>


</body>
</html>