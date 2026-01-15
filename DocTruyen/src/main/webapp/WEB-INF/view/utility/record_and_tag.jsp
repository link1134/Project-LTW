<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${title}</title>
<style>
@charset "ISO-8859-1";

* {
	padding: 0;
	margin: 0;
}

body {
	font-family: "Roboto", sans-serif;
	background-color: rgb(209, 213, 219);
}

header {
	position: sticky;
	background-color: white;
	border-bottom: 1px solid #ddd;
	display: flex;
	align-items: center;
	z-index: 100;
	top: 0;
	box-shadow: 0 3px 6px #00000012, 0 10px 20px #0000000d;
	font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto,
		Oxygen-Sans, DejaVu Sans, Ubuntu, Cantarell, Helvetica Neue,
		sans-serif !important;
}

/* Thêm navbar*/
.nav-left a, .nav-logo span, .nav-right a {
	color: black;
	font-weight: bold;
}

.navbar {
	display: flex;
	justify-content: space-between;
	align-items: center;
	width: 100%;
	padding: 0 5rem;
	box-sizing: border-box;
	font-size: .75rem;
	text-transform: uppercase;
}

.nav-left, .nav-right {
	display: flex;
	gap: 20px;
	align-items: center;
	list-style: none;
}

/* Logo cố định ở giữa */
.nav-logo {
	display: flex;
	align-items: center;
	gap: 10px;
	color: var(- -title-color);
	font-size: 1.5rem;
	margin: 0;
}

.nav-logo img {
	width: 3rem;
	/* hoặc 24px, tuỳ bạn */
	height: auto;
	object-fit: contain;
	padding-bottom: 1rem
}

.avatar {
	width: 3rem;
	height: 3rem;
	border-radius: 50%;
}

.nav_left_item a {
	text-decoration: none;
}

.icon-btn {
	font-size: 1.2rem;
	width: 3rem;
	height: 3rem;
	border-radius: 50%;
	display: flex;
	justify-content: center;
	align-items: center;
	cursor: pointer;
	border: none;
}

main {
	/* Căn giữa nội dung chính */
	padding-top: 5rem;
	padding-bottom: 40px;
	min-height: 90vh;
}

.container {
	max-width: 1280px;
	margin-left: auto;
	margin-right: auto;
	margin-bottom: 4rem;
	text-transform: uppercase;
	font-weight: 700;
	font-size: 1.25rem;
	line-height: 1.75rem;
}

.card_container {
	max-width: 1280px;
	margin-left: auto;
	margin-right: auto;
}

.card-grid {
	display: grid;
	grid-template-columns: repeat(auto-fill, minmax(12rem, 1fr));
	gap: 1.5rem;
}

.card {
	padding: 0;
	scroll-snap-align: start;
	flex: 0 0 12rem;
	height: 19rem;
	border-radius: .5em;
	align-content: center;
	overflow: hidden;
	display: flex;
	flex-direction: column;
	font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto,
		Oxygen-Sans, DejaVu Sans, Ubuntu, Cantarell, Helvetica Neue,
		sans-serif;
}

.card-img {
	width: 100%;
	height: 15em;
	object-fit: cover;
}

.card-wrapped {
	height: auto;
	width: 100%;
}

.card-description {
	overflow: hidden;
}

.card-description-name {
	text-decoration: none;
	display: -webkit-box;
	-webkit-box-orient: vertical;
	font-size: .8rem;
	-webkit-line-clamp: 2;
	overflow: hidden;
	color: black;
	-webkit-line-clamp: 2;
}

.card-description-chapter_time {
	text-decoration: none;
	color: rgb(117, 117, 117);
	display: -webkit-box;
	-webkit-box-orient: vertical;
	-webkit-line-clamp: 1;
	overflow: hidden;
}

.controller {
	max-width: 1280px;
	display: flex;
	justify-content: center;
	align-items: center;
	margin-top: 3rem;
	margin-bottom: 3rem;
	text-transform: uppercase;
	font-weight: 700;
	font-size: 1.25rem;
	line-height: 1.75rem;
	gap: 1rem;
}

.controller a {
	white-space: nowrap;
	border-radius: .5rem;
	text-align: center;
	font-size: .875rem;
	line-height: 1.25rem;
	font-weight: 700;
	text-transform: uppercase;
	transition-property: color, background-color, border-color,
		text-decoration-color, fill, stroke, opacity, box-shadow, transform,
		filter, backdrop-filter;
	transition-duration: .2s;
	transition-timing-function: cubic-bezier(.4, 0, .2, 1);
	padding-top: .5rem;
	padding-bottom: .5rem;
	padding-left: 1.5rem;
	padding-right: 1.5rem;
	background: white;
	color: black;
	text-decoration: none;
}

.controller a, .disable-button {
	cursor: default;
	background-color: rgb(107, 114, 128);
	color: rgb(78, 85, 99);
	pointer-events: none;
}
/* --- Footer cuối trang --- */
.footer {
	background-color: #242526; /* Màu nền đen/xám đậm */
	color: #b0b3b8; /* Màu chữ xám nhạt */
	padding: 20px 0;
	font-size: 14px;
	text-align: center;
}

.footer__container {
	width: 100%;
	max-width: 1000px;
	margin: 0 auto;
	padding: 0 20px;
}

.footer__text--highlight {
	font-weight: 600;
	color: #ffffff;
	margin-bottom: 5px;
}

.footer__email {
	margin-bottom: 15px;
}

.footer__email-link {
	color: #1877f2;
	transition: color 0.2s;
}

.footer__email-link:hover {
	text-decoration: underline;
}

.footer__links {
	margin-bottom: 15px;
	display: flex;
	justify-content: center;
	gap: 20px;
}

.footer__link {
	color: #b0b3b8;
}

.footer__link:hover {
	text-decoration: underline;
}
</style>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100..900;1,100..900&display=swap"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/335fe6f64f.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/view/utility/header.jsp" />
	<main>
		<div class="container">${subTitle}</div>
		<div class="card_container">
			<div class="card-grid">
				<c:forEach var="story" items="${stories}">
					<div class="card">
						<div class="card-wrapped">
							<a
								href="${pageContext.request.contextPath}/story-detail?id=${story.id}">
								<img
								src="${pageContext.request.contextPath}/${story.coverImageURL}"
								class="card-img">
							</a>
						</div>

						<div class="card-description">
							<a
								href="${pageContext.request.contextPath}/story-detail?id=${story.id}"
								class="card-description-name">
								<h3>
									<c:out value="${story.title}" />
								</h3>
							</a>
							<h5 class="card-description-chapter_time">
								<span>${latestChapterInfo[story.id]}</span>
							</h5>

						</div>
					</div>
				</c:forEach>

				<c:if test="${empty stories}">
					<p>Chưa có truyện nào.</p>
				</c:if>
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