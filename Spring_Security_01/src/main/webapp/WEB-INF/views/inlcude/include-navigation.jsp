<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"  prefix="sec"%>

<script>

	$(function(){
		
		$(".logout-link").click(function(){
			
			$("#logout-form").submit()
			
		})
		
	})

</script>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
	<div class="container">
		<a class="navbar-brand" href="#">Security</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link" href="${rootPath}/">Home <span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="#">About</a></li>
				<sec:authorize access="isAnonymous()">
					<li class="nav-item"><a class="nav-link" href="${rootPath}/login">Login</a></li>
					<li class="nav-item"><a class="nav-link" href="${rootPath}/join">Join</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<form id="logout-form" action="${rootPath}/logout" method="POST">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
						<li class="nav-item"><a class="nav-link logout-link" href="#">Logout</a></li>
					</form>
					<li class="nav-item"><a class="nav-link" href="${rootPath}/login">My info</a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li class="nav-item"><a class="nav-link" href="${rootPath}/login">Manage</a></li>
				</sec:authorize>
			</ul>
		</div>
	</div>
</nav>