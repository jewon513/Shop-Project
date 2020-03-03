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

		$(".btn-delete").click(function() {

			let b_id = $(this).attr("data-id")

			$.ajax({
				url : "${rootPath}/delete",
				type : "POST",
				data : {
					b_id : b_id
				},
				success : function(result) {
					if (result == "OK") {
						alert("삭제완료")
						document.location.href = '${rootPath}/list'
					} else {
						alert("삭제실패")
						return false
					}
					s
				},
				error : function() {
					alert("서버 통신 오류")
				}

			})

		})

		$(".btn-comment-write").click(function() {

			let comment_data = $("#comment-form").serialize()

			$.ajax({

				url : '${rootPath}/comment/insert',
				data : comment_data,
				type : "POST",
				success : function(result) {
					if (result == 'OK') {

						$.ajax({

							url : '${rootPath}/comment/list',
							data : {
								c_b_id : '${BBSVO.b_id}'
							},
							type : "GET",
							success : function(result) {
								$(".comment-list").html(result)
								$(".form-control").val("")
							},
							error : function() {
								alert("댓글 리스트를 불러오는데 서버 통신 오류가 발생했습니다.")
							}

						})

					} else {
						alert("댓글을 작성하는데 오류가 발생했습니다.")
					}
				},
				error : function(error) {
					alert("서버통신 오류")
				}

			})

		})

		// 댓글 수정 부분
		$(document).on("click", ".comment-item", function() {

			let id = $(this).data("id")
			let writer = $(this).find(".writer").text()
			let comment = $(this).find(".subject").text()
			
			$("#c_id").val(id)
			$("#c_writer").val(writer)
			$("#c_comment").val(comment)
			
		})
		
		$(document).on("click", ".comment-item-delete", function(event) {
			
			// 이벤트 버블링에 있어 현재 이벤트 이후의 전파를 막는다.
			event.stopPropagation()
			
			if(!confirm("댓글을 삭제 할까요 ?")){
				return false;
			}
			
			let c_id = $(this).closest(".comment-item").data("id")
			
			// alert("delete 하기 위한 item의 id : " +c_id)
			
			$.ajax({
				
				url : "${rootPath}/comment/delete",
				type : "POST",
				data : {
					c_id : c_id,
					b_id : '${BBSVO.b_id}'
				},
				success : function(result){
					$(".comment-list").html(result)			
				},
				error : function(){
					alert("서버 통신 오류")
				}
				
			})
			
			
			
		})
		
		$(document).on("click",".comment-item-reply",function(event){
			
			let b_id = "${BBSVO.b_id}"
			let c_id = $(this).closest(".comment-item").data("id")
			let data = {
				c_b_id : b_id,
				c_p_id : c_id
			}
			
			
			event.stopPropagation()
			
			$.get("${rootPath}/comment/reply",data,function(result){
				
				$(".modal-body").html(result)
				$(".modal-main").css("display","block")
				
			})
			
		})
		
		$(document).on("click",".btn-comment-reply",function(event){
			
			let comment_data = $("#comment-form-reply").serialize()
			
			$.ajax({

				url : '${rootPath}/comment/insert',
				data : comment_data,
				type : "POST",
				success : function(result) {
					if (result == 'OK') {

						$.ajax({

							url : '${rootPath}/comment/list',
							data : {
								c_b_id : '${BBSVO.b_id}'
							},
							type : "GET",
							success : function(result) {
								$(".comment-list").html(result)
								$(".form-control").val("")
								$(".modal-main").css("display","none")
							},
							error : function() {
								alert("댓글 리스트를 불러오는데 서버 통신 오류가 발생했습니다.")
							}

						})

					} else {
						alert("댓글을 작성하는데 오류가 발생했습니다.")
					}
				},
				error : function(error) {
					alert("서버통신 오류")
				}

			})
			
		})
		
		
		
		// 답글쓰기 부분
		$(".btn-rewrite").click(function(){
			
			// alert("답글쓰기")
			document.location.href = "${rootPath}/repl?b_id=${BBSVO.b_id}"
					
						
		})

	})
</script>

<style>
	.content {
		white-space: pre-line;
	}
	
	.comment-item-delete{
		cursor: pointer;
	}
	
	.comment-item-reply{
		cursor: pointer;
	}

	.jewon-pl-2{
		padding-left: 3rem;
	}
	
	.jewon-pl-3{
		padding-left: 6rem;
	}
	
	.jewon-pl-4{
		padding-left: 9rem;
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
			<button class="btn btn-info btn-rewrite mr-2" type="button">답글쓰기</button>
			<button class="btn btn-primary btn-list" type="button">목록으로</button>
		</div>

		<div class="mt-5">
			<h3>Comment List</h3>
			<hr />

			<div class="p-4 comment-list">

				<%@ include file="/WEB-INF/views/incldue/include-comment-list.jsp" %>

			</div>

		</div>

		<div class="comment-write mt-5">
			<h3>Comment Write</h3>
			<hr />
			<div class="p-4">
				<form id="comment-form">
					<input type="hidden" id="c_id" name="c_id" value="0">
					<input type="hidden" id="c_b_id" name="c_b_id" value="${BBSVO.b_id}">
					<div class="form-group">
						<input class="form-control border border-secondary" id="c_writer" name=c_writer placeholder="작성자">
					</div>
					<div class="form-group">
						<textarea class="form-control border border-secondary" id="c_comment" name="c_comment" rows="10" placeholder="댓글"></textarea>
					</div>

					<div class="d-flex justify-content-end">
						<button class="btn btn-primary btn-comment-write mt-3 mb-5" type="button">댓글쓰기</button>
					</div>
				</form>
			</div>
		</div>

	</div>
	
		<style>
			div.modal-main{
				position: fixed;
				top:0;
				left:0;
				
				width: 100%;
				height: 100%;
				
				overflow: auto;
				
				background-color: rgba(0,0,0,0.4);
				z-index: 10;
				display: none;
			}
			
			div.modal-content{
			
				position: relative;
				margin: auto;
				top: 100px;
				padding: 0;
				
				width: 70%;
				
			}
			
			span.modal-close{
				cursor : pointer;
			}
			
		</style>
		
		<script>
		
			$(function(){
				
				$(".modal-close").click(function(){
					$(".modal-main").css("display","none")
				})	
				
			})
			
		</script>
		
		
		<div class="modal-main">
			<div class="modal-content p-2">
				<div class="modal-header">
					<h3>COMMENT WRITE</h3>
					<span class="modal-close ml-auto">&times;</span>
				</div>
				<div class="modal-body">
					
				</div>
			</div>
		</div>
			

</body>
</html>