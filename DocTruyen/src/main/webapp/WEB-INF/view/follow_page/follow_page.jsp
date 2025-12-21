<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Follow Page</title>
<!-- main css2 -->

<!-- google font -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100..900;1,100..900&display=swap"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/335fe6f64f.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/static/css/follow_page/style.css">

</head>
<body>
	<jsp:include page="/WEB-INF/view/utility/header.jsp" />
	<main>
		<div class="container">Đang theo dõi</div>
		<div class="card_container">
			<div class="card-grid">
				<div class="card">
					<div class="card-wrapped">
						<a href="${pageContext.request.contextPath}/story_page"> <img
							src="${pageContext.request.contextPath}/IMAGE/thumnail.png"
							alt="" class="card-img">
						</a>
					</div>
					<div class="card-description">
						<a href="${pageContext.request.contextPath}/story_page"
							class="card-description-name">
							<h3>Doraemon</h3>
						</a>
						<h5>
							<a href="" class="card-description-chapter_time"> <span>C.6</span>
								- <span>6 ngày trước</span>
							</a>
							</h4>
					</div>
				</div>
				<div class="card">
					<div class="card-wrapped">
						<a href=""> <img
							src="${pageContext.request.contextPath}/IMAGE/image_processing20251206-2-1l6kpfc.jpg"
							alt="" class="card-img">
						</a>
					</div>
					<div class="card-description">
						<a href="" class="card-description-name">
							<h3>IDOLxIDOL STORY</h3>
						</a>
						<h5>
							<a href="" class="card-description-chapter_time"> <span>C.1</span>
								- <span>1 ngày trước</span>
							</a>

							</h4>
					</div>
				</div>
			</div>
			<div class="controller ">
				<a href="#" class="disable-button"> <span>←Trang sau</span>
				</a> <a href="#" class="disable-button"> <span>Trang trước →</span>
				</a>
			</div>
		</div>

	</main>
	<footer class="footer">
		<div class="footer__container">
			<p class="footer__text footer__text--highlight">Contact for work,
				copyright and more:</p>

			<p class="footer__email">
				<a href="mailto:ad.doctruyen@gmail.com" class="footer__email-link">ad.doctruyen@gmail.com</a>
			</p>

			<div class="footer__links">
				<a href="#" class="footer__link">Điều khoản dịch vụ</a> <a href="#"
					class="footer__link">Chính sách bảo mật</a>
			</div>

			<span class="footer__copy">© 2025 - doctruyen.net</span>
		</div>
	</footer>
</body>
</html>