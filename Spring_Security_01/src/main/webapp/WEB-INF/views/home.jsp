<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath }" />
<%@ taglib uri="http://www.springframework.org/security/tags"  prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

	<%@ include file = "/WEB-INF/views/inlcude/include-head.jsp"  %>

</head>
<body>

	<!-- Navigation -->
	<%@ include file = "/WEB-INF/views/inlcude/include-navigation.jsp"  %>
	
	<div class="container">
		<p>principal : <sec:authentication property="principal"/> </p>
		<p>MemberVO : <sec:authentication property="principal.member"/> </p>
		<p>사용자이름 : <sec:authentication property="principal.member.username"/> </p>
		<p>사용자아이디 : <sec:authentication property="principal.username"/> </p>
		<p>사용자 권한 리스트 : <sec:authentication property="principal.member.authList"/></p>
	</div>

</body>
</html>