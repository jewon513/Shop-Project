<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />

<head>
<%@ include file="/WEB-INF/views/incldue/include-head.jspf"%>
</head>

<script>
	$(function() {

		$(".btn-update").click(function() {

			let b_id = '${BBSVO.b_id}'

			document.location.href = "${rootPath}/update?b_id=" + b_id

		})

		$(".btn-list").click(function() {

			document.location.href = "${rootPath}/list"

		})
		
		$(".btn-delete").click(function(){
			
			let b_id = $(this).attr("data-id")
			
			$.ajax({
				url : "${rootPath}/delete",
				type : "POST",
				data : {
						b_id : b_id
				},
				success : function(result){
					if(result == "OK"){
						alert("삭제완료")
						document.location.href = '${rootPath}/list'
					}else{
						alert("삭제실패")
						return false
					}
				},
				error : function(){
					alert("서버 통신 오류")
				}
				
			})
			
		})

	})
</script>

<style>
.content {
	white-space: pre-line;
}
</style>

<body>

	<%@ include file="/WEB-INF/views/incldue/include-header.jsp"%>


	<div class="container">

		<div class="subject">
			<h2>${BBSVO.b_subject}</h2>
			<small>${BBSVO.b_date_time}</small><small class="ml-2">작성자: ${BBSVO.b_writer}</small>
		</div>
		<hr />

		<div class="content">${BBSVO.b_content}</div>

		<hr />

		<div class="d-flex justify-content-end">
			<button class="btn btn-success btn-update mr-2" type="button">수정</button>
			<button class="btn btn-danger btn-delete mr-2" data-id="${BBSVO.b_id}" type="button">삭제</button>
			<button class="btn btn-primary btn-list" type="button">목록으로</button>
		</div>

		<div class="comment-list mt-5">
			<h3>Comment List</h3>
			<hr />

			<div class="p-4">
				<div class="d-flex align-items-center">
					<h4>작성자</h4>
					<small class="ml-2">${BBSVO.b_date_time}</small>
				</div>
				<p class="pl-3 pr-3">댓글 내용</p>
			</div>

		</div>

		<div class="comment-write mt-5">
			<h3>Comment Write</h3>
			<hr />
			<div class="p-4">
				<div class="form-group">
					<input class="form-control border border-secondary" placeholder="작성자">
				</div>
				<div class="form-group">
					<textarea class="form-control border border-secondary" rows="10" placeholder="댓글"></textarea>
				</div>

				<div class="d-flex justify-content-end">
					<button class="btn btn-primary mt-3 mb-5">댓글쓰기</button>
				</div>
			</div>
		</div>

	</div>

</body>
</html>