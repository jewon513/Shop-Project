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

#body menu {
	flex: 1;
	border: 1px solid blue;
	margin: 5px;
}

#body article {
	flex: 3;
	border: 1px solid blue;
	margin: 5px;
}

#body li a {
	text-decoration: none;
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
			<menu>
				<div class="m-auto">
					<h3>관리자페이지</h3>
					<ul>
						<li><a href="javascropt:void(0)" id="user_list">User List</a></li>
						<li><a href="#">메뉴1</a></li>
						<li><a href="#">메뉴2</a></li>
					</ul>
				</div>
			</menu>

			<article id="admin_content"></article>
		</div>
	</div>

</body>
</html>