<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<header>
		<h2>Model Attribute</h2>
	</header>
	
	<section>
		<h3>UserId : ${userVO.userId}</h3>
		<h3>Password : ${userVO.password}</h3>
		<h3>UserName : ${userVO.userName}</h3>
		<h3>UserRole : ${userVO.userRole}</h3>
	</section>

</body>
</html>