<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<style>
	
	span{
		color: orange;
		font-weight: bold;
		font-style: italic;
	}

</style>

<h3>사용자 ID: <span>${userVO.userId}</span></h3>
<h3>Password: <span>${userVO.password}</span></h3>
<h3>사용자 이름: <span>${userVO.userName}</span></h3>
<h3>사용자 권한: <span>${userVO.userRole}</span></h3>