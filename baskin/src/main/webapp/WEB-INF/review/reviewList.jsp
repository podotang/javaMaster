<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="review-container">
	<div class="review-header">
		<div class="review-title">
			<span class="title-text">리뷰</span><span class="title-count">${cnt }</span>
			<span class="review-stars">★★★★★</span>
			<!-- 별 개수 추가 -->
		</div>
	</div>
	<div class="review-images-container">
		<span class="left-arrow" onclick="slideLeft()">&#10094;</span>
		<div class="review-images-wrapper">

			<div class="review-images">
				<c:forEach var="review" items="${reviewList }">
					<img src="resources/images/${review.productImg }"
						class="image-item" />
				</c:forEach>

			</div>
		</div>
		<span class="right-arrow" onclick="slideRight()">&#10095;</span>
	</div>
	<div class="review-content">
		<c:forEach var="review" items="${reviewList }">

			<div class="review-item">
				<div class="review-details">
					<div class="d-flex justify-content-center small text-warning mb-2">
						<c:forEach var="i" begin="1" end="${review.viewScore }">
							<div class="bi-star-fill"></div>
						</c:forEach>
					</div>
					<span class="review-user">${review.userId }</span> <span
						class="review-date">${review.viewDate }</span> <span
						class="review-option">사이즈 : ${review.productSize }</span> <span
						class="review-body">컬러 : ${review.productColor }</span>
					<div class="review-text">
						<span>${review.content }</span>
					</div>
				</div>
				<img src="resources/images/${review.productImg }"
					class="review-thumb" />
				<!-- 리뷰 썸네일 추가 -->
			</div>

			<!-- 추가적인 리뷰 아이템들 -->
		</c:forEach>

	</div>
	<div class="pagination">
		<span class="left" onclick="prevPage()">&#10094;</span> <span
			class="page-number" onclick="goToPage(1)">1</span> <span
			class="page-number" onclick="goToPage(2)">2</span> <span
			class="page-number" onclick="goToPage(3)">3</span> <span
			class="right" onclick="nextPage()">&#10095;</span>
	</div>
</div>

<script>
	const reviewImagesWrapper = document
			.querySelector('.review-images-wrapper');
	const reviewImages = document.querySelector('.review-images');
	let scrollAmount = 0;

	function slideLeft() {
		reviewImagesWrapper.scrollTo({
			top : 0,
			left : (scrollAmount -= 200),
			behavior : 'smooth'
		});
	}

	function slideRight() {
		reviewImagesWrapper.scrollTo({
			top : 0,
			left : (scrollAmount += 200),
			behavior : 'smooth'
		});
	}

	function prevPage() {
		// 이전 페이지로 이동하는 기능 구현
	}

	function nextPage() {
		// 다음 페이지로 이동하는 기능 구현
	}

	function goToPage(pageNumber) {
		// 특정 페이지로 이동하는 기능 구현
	}
</script>