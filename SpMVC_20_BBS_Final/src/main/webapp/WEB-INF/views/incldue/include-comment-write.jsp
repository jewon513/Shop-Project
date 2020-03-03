<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />

<div class="p-4">
	<form id="comment-form-reply" method="POST">
		<input type="hidden" id="c_p_id" name="c_p_id" value="${CMT.c_p_id}"> 
		<input type="hidden" id="c_b_id" name="c_b_id" value="${CMT.c_b_id}">
		<div class="form-group">
			<input class="form-control border border-secondary" id="c_writer" name=c_writer placeholder="작성자">
		</div>
		<div class="form-group">
			<textarea class="form-control border border-secondary" id="c_comment" name="c_comment" rows="10" placeholder="댓글"></textarea>
		</div>

		<div class="d-flex justify-content-end">
 			<button class="btn btn-primary btn-comment-reply mt-3 mb-5" type="button">저장</button>
		</div>
	</form>
</div>