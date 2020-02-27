<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" 
		prefix="sec" %>
<!DOCTYPE html>
<html>

<%@ include file="/WEB-INF/views/include/include-head.jspf"%>

<script>
$(function(){

	$("a.logout").click(function(){
		$("#logout").submit()
	})
	
})

</script>

<body>

	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
		<div class="container">
			<a class="navbar-brand" href="#">Shop Project</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link" href="#">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="${rootPath}/user/product/list">상품리스트</a></li>
					<sec:authorize access="isAnonymous()">
						<li class="nav-item"><a class="nav-link" href="${rootPath}/auth/login">login</a></li>
					</sec:authorize>
					<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">input member nickname</a>
						<div class="dropdown-menu">
							<a class="dropdown-item" href="#">member info</a>
							<div class="dropdown-divider"></div>
							<sec:authorize access="hasRole('ADMIN')">
							<form id="logout" method="POST" action="${rootPath}/logout">
								<input type="hidden" 
									name="${_csrf.parameterName}" 
									value="${_csrf.token}">
								<a class="dropdown-item logout" >logout</a>
								<a class="dropdown-item" href="${rootPath}/user/product/cart_view">장바구니</a>
							</form>
								<a class="dropdown-item" href="${rootPath}/admin/">관리자</a>
							</sec:authorize>
						</div></li>
				</ul>
			</div>
		</div>
	</nav>

	<c:choose>
		<c:when test="${BODY=='DETAIL'}">
			<%@ include file="/WEB-INF/views/users/user_product_detail.jsp"%>

		</c:when>
		<c:when test="${BODY=='CART_VIEW'}">
			<%@ include file="/WEB-INF/views/users/cart_view.jsp"%>

		</c:when>

		<c:otherwise>
			<%@ include file="/WEB-INF/views/users/user_product_list.jsp"%>
		</c:otherwise>

	</c:choose>

	<!-- Footer -->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; Your Website 2019</p>
		</div>
		<!-- /.container -->
	</footer>

</body>
</html>