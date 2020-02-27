<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script>
	$(function() {

		$(".product_item")
				.click(
						function() {

							let p_id = $(this).attr("data-id")

							document.location.href = "${rootPath}/user/product/detail?product_id="
									+ p_id

						})

	})
</script>

<div class="container">


	<!-- Page Content -->
	<div class="container">

		<!-- Jumbotron Header -->
		<header class="jumbotron my-4">
			<h1 class="display-3 font-weight-bold">클릭하고 주문하는데까지 1분</h1>
			<p class="lead font-weight-bold m-2">안사도 됩니다! 구경만 하고 가세요!</p>
			<a href="#" class="btn btn-primary btn-lg">추천상품 바로가기!</a>
		</header>

		<!-- Page Features -->
		<div class="row text-center">

			<c:choose>
				<c:when test="${empty product_list}">
					<h2 class="font-weight-bold"> 상품정보가 없습니다.</h2>
				</c:when>

				<c:otherwise>
					<c:forEach items="${product_list}" var="vo">
						<div class="col-lg-3 col-md-6 mb-4">
							<div class="card h-100">
								<img class="card-img-top" src="http://placehold.it/500x325" alt="">
								<div class="card-body">
									<h4 class="card-title font-weight-bold">${vo.p_name}</h4>
									<p class="card-text"><fmt:formatNumber value="${vo.p_oprice}" type="currency" /></p>
								</div>
								<div class="card-footer">
									<a href="#" class="btn btn-primary product_item" data-id="${vo.id}">자세히 보기</a>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:otherwise>

			</c:choose>

		</div>
		<!-- /.row -->

		<div class="p-3">
			<div class="form-inline justify-content-around">
				<select class="form-control col-sm-4 m-2" id="sel1">
					<option>카테고리</option>
					<option>공산품</option>
					<option>농산물</option>
					<option>수산물</option>
				</select>
				<input class="form-control col-sm-7 m-2" placeholder="상품이름을 입력한 후 Enter...">
			</div>
		</div>


	</div>
	<!-- /.container -->


</div>

