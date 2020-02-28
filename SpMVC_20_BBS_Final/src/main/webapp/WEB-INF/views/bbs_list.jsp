<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />

<head>
<%@ include file="/WEB-INF/views/incldue/include-head.jspf"%>


<script>
	$(function() {

		$(".btn-writer").click(function() {
			document.location.href = "${rootPath}/insert"
		})

	})
</script>

</head>
<body>

	<%@ include file="/WEB-INF/views/incldue/include-header.jsp" %>


		<section class="container-fluid">
			<article>
				<table class="table table-hover">
					<tr class="table-active">
						<th width="15%">NO</th>
						<th width="15%">작성자</th>
						<th width="30%">일시</th>
						<th width="40%">제목</th>
					</tr>
					<c:forEach items="${BBS_LIST}" var="BBS">
						<tr>
							<td>${BBS.b_id}</td>
							<td>${BBS.b_writer}</td>
							<td>${BBS.b_date_time}</td>
							<td><a href="${rootPath}/detail?b_id=${BBS.b_id}">${BBS.b_subject}</a></td>
						</tr>
					</c:forEach>
				</table>
			</article>

		<article class="d-flex justify-content-end">
			<button class="btn btn-primary btn-writer">글쓰기</button>
		</article>

	</section>


</body>
</html>