<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>

	section {
	
		border : 1px solid blue;
		padding : 1rem;
	
	}

</style>

<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script src="${rootPath}/resources/js/ajax.js"></script>

<script>

	var rootPath = "${rootPath}"

</script>


</head>

<body>

	<section id="main">
	
		<button id="call_ajax" >Ajax 호출</button>
		
		<input type="text" id="msg">
		
		<button id="call_msg" >메세지 호출</button>
		<button id="call_addr" >주소 호출</button>
		<button id="call_addrView">뷰 호출</button>
	
		<section id="sub">
		
		</section>
	</section>

</body>
</html>