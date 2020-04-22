<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>

<%@ include file="/WEB-INF/views/include/include-head.jspf"%>

<style>
#body {
	display: flex;
}


#body .menu {
	flex: 1;
	margin: 15px;
}

#body article {
	flex: 3;
	margin: 15px;
}

#body li a {
	text-decoration: none;
	color :inherit;
	display: inline-block;
}
</style>

<script type="text/javascript">
	$(function() {

		$(document).on("click", "#user_list", function() {
			
			$.get("${rootPath}/admin/userList", function(result) {
				$("#admin_content").html(result)
			})

		})
		
		$(document).on("click","#auth_append",function(){
			
			let auth_input = $("<input/>", {class : "form-control", name : "auth"})
			$("div#auth_box").append("<div class='form-group'>")
			$("div#auth_box").append(auth_input)
			$("div#auth_box").append("</div>")
			
		})
		

		$(document).on(
				"click",
				"tr.tr_user",
				function() {
					let username = $(this).data("id")

					$.get("${rootPath}/admin/user_detail_view/" + username,
							function(result) {
								$("#admin_content").html(result)

							})

				})
				
		$(document).on("click","#btn-save",function(){
			
			let formData = $("form").serialize()
			
			$.post("${rootPath}/admin/user_detail_view",formData,function(result){
				alert("Update 성공")
				$("#admin_content").html(result)
			})
			
			
		})
		

	})
</script>
<body>

	<%@ include file="/WEB-INF/views/include/include-nav.jspf"%>

	<div class="container-fluid body">
		<div class="mt-5" id="body">

			<div class="menu">
				<div class="p-5 border shadow">
					<h3>관리자페이지</h3>
					<ul>
						<li><a href="javascropt:void(0)" id="user_list">회원 리스트</a></li>
					</ul>
				</div>
			</div>


			<article id="admin_content" class="p-5 border shadow "></article>
		</div>
	</div>

</body>
</html>