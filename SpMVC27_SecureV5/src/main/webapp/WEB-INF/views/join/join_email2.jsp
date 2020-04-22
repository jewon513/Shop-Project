<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>

<%@ include file="/WEB-INF/views/include/include-head.jspf"%>

</head>
<body>

	<%@ include file="/WEB-INF/views/include/include-nav.jspf"%>

	<div class="container body">
		<div class="mt-3">
			<h2>Email 인증</h2>
			<div class="form-group">회원가입을 완료하려면 Email 인증을 수행해야 합니다</div>
			<div>

				<form:form modelAttribute="userVO" action="${rootPath}/join/joinok">

					<div class="form-group">
						<form:input type="email" path="email" placeholder="email" class="form-control" />
					</div>

					<c:if test="${empty userVO.email}">

						<div class="form-group">
							<div class="d-flex justify-content-end">
								<button class="btn btn-primary">인증 Email 보내기</button>
							</div>
						</div>

					</c:if>

					<c:if test="${not empty userVO.email}">

						<div class="form-group">
							<div class="d-flex justify-content-end">
								<button class="btn btn-primary">인증 Email 다시보내기</button>
							</div>
						</div>

					</c:if>

					<c:choose>
						<c:when test="${JOIN=='EMAIL_OK'}">
							<div class="form-group">
								<div class="d-flex justify-content-end">
									<button class="btn btn-primary">인증 Email 다시보내기</button>
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