<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vn">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/static/css/chapter_detail_page/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/static/css/reset.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100..900;1,100..900&display=swap"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/335fe6f64f.js"
	crossorigin="anonymous"></script>
<title>Chapter</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/utility/header.jsp" />
	<main>
		<div class="story-interaction">
			<button class="comment-button">
				<span>0</span> <span><i class="fa-solid fa-message"></i> </span>
			</button>
			<div class="content-select-controller">
				<button class="previous-chapter-button">
					<i class="fa-solid fa-chevron-left"></i>
				</button>
				<button class="chapter-select">
					<span>Chương ?</span>
				</button>
				<button class="next-chapter-button">
					<span>Tiếp</span> <span><i class="fa-solid fa-chevron-right"></i></span>
				</button>
			</div>
			<button class="up-button">
				<i class="fa-solid fa-chevron-up"></i>
			</button>
		</div>
		<div class="comment_overlay" id="commentOverlay">
			<div class="comment_box">
				<div class="comment_box_title">
					Bình luận
					<button class="comment_box_close">
						<i class="fa-solid fa-xmark"></i>
					</button>
				</div>
				<div class="comment_box_content">
					<div class="comment_box_inner_content">
						<div class="comment">
							<div class="comment_author_and_content">
								<div class="comment_author">
									<span>Hiển</span>
								</div>
								<div class="comment_content">
									<p>Eeyo wtf ?????</p>
								</div>
							</div>
							<div class="comment_information">
								<span>2 năm trước</span> <span class="mx-1">·</span> <span>
									<span class="text-gray-700">^</span> 134022
								</span> <span class="mx-1">·</span>
								<button class="reply_button">Trả lời</button>
							</div>
						</div>
					</div>
				</div>
				<div class="write_comment_box">
					<form action="">
						<div>
							<button>Gửi</button>
						</div>
						<textarea placeholder="Nhập bình luận..." class="comment_input"></textarea>
					</form>
				</div>
			</div>
		</div>
		<div class="chapter_overlay" id="chapterOverlay">

			<div class="chapter_box">
				<div class="chapter_box_title">
					Chuyển chương
					<button class="chapter_box_close">
						<i class="fa-solid fa-xmark"></i>
					</button>

				</div>
				<div class="chapter_box_content">
					<div class="chapter_box_grid">
						<a> Chương
							<div>1</div>
						</a> <a> Chương
							<div>2</div>
						</a> <a> Chương
							<div>3</div>
						</a> </a> <a> Chương
							<div>4</div>
						</a> </a> <a> Chương
							<div>5</div>
						</a>
					</div>
				</div>

			</div>

		</div>
		<div class="small_controller">
			<button onclick="location.href='#navbar'">↑</button>
			<button onclick="location.href='#footer'">↓</button>
		</div>
		<div class="content">
			<div class="story_name_carrier">
				<a href="${pageContext.request.contextPath}/story_page"
					class="story_name"> <span>← DORAEMON</span>
				</a>
			</div>
			<h1 class="story_chapter_name">
				<span></span> <span>Hành tinh GARAPA</span>
			</h1>
			<div class="chapter_controls">
				<div>
					<a href="">chương sau</a>
				</div>
				<div>
					<a href="">chương trước</a>
				</div>
			</div>
		</div>
		<div class="story_content">
			<ul class="list_img">
				<li><img src="${pageContext.request.contextPath }/IMAGE/0.png"
					alt="0"></li>
				<li><img src="${pageContext.request.contextPath }/IMAGE/1.png"
					alt="0"></li>
				<li><img src="${pageContext.request.contextPath }/IMAGE/2.png"
					alt="0"></li>
				<li><img src="${pageContext.request.contextPath }/IMAGE/3.png"
					alt="0"></li>
				<li><img src="${pageContext.request.contextPath }/IMAGE/4.png"
					alt="0"></li>
				<li><img src="${pageContext.request.contextPath }/IMAGE/5.png"
					alt="0"></li>
				<li><img src="${pageContext.request.contextPath }/IMAGE/6.png"
					alt="0"></li>
				<li><img src="${pageContext.request.contextPath }/IMAGE/7.png"
					alt="0"></li>
				<li><img src="${pageContext.request.contextPath }/IMAGE/8.png"
					alt="0"></li>
				<li><img src="${pageContext.request.contextPath }/IMAGE/9.png"
					alt="0"></li>
				<li><img src="${pageContext.request.contextPath }/IMAGE/10.png"
					alt="0"></li>
				<li><img src="${pageContext.request.contextPath }/IMAGE/11.png"
					alt="0"></li>
				<li><img src="${pageContext.request.contextPath }/IMAGE/12.png"
					alt="0"></li>
				<li><img src="${pageContext.request.contextPath }/IMAGE/13.png"
					alt="0"></li>
				<li><img src="${pageContext.request.contextPath }/IMAGE/14.png"
					alt="0"></li>
				<li><img src="${pageContext.request.contextPath }/IMAGE/15.png"
					alt="0"></li>
				<li><img src="${pageContext.request.contextPath }/IMAGE/16.png"
					alt="0"></li>
				<li><img src="${pageContext.request.contextPath }/IMAGE/17.png"
					alt="0"></li>
				<li><img src="${pageContext.request.contextPath }/IMAGE/18.png"
					alt="0"></li>
				<li><img src="${pageContext.request.contextPath }/IMAGE/19.png"
					alt="0"></li>
				<li><img src="${pageContext.request.contextPath }/IMAGE/20.png"
					alt="0"></li>

				<li><img src="/IMAGE/21.png" alt="0"></li>
				<li><img src="/IMAGE/22.png" alt="0"></li>
				<li><img src="/IMAGE/23.png" alt="0"></li>
				<li><img src="/IMAGE/24.png" alt="0"></li>
				<li><img src="/IMAGE/25.png" alt="0"></li>
				<li><img src="/IMAGE/26.png" alt="0"></li>
				<li><img src="/IMAGE/27.png" alt="0"></li>
				<li><img src="/IMAGE/28.png" alt="0"></li>
				<li><img src="/IMAGE/29.png" alt="0"></li>
				<li><img src="/IMAGE/30.png" alt="0"></li>
				<li><img src="/IMAGE/31.png" alt="0"></li>
				<li><img src="/IMAGE/32.png" alt="0"></li>
				<li><img src="/IMAGE/33.png" alt="0"></li>
				<li><img src="/IMAGE/34.png" alt="0"></li>
				<li><img src="/IMAGE/35.png" alt="0"></li>
				<li><img src="/IMAGE/36.png" alt="0"></li>
				<li><img src="/IMAGE/37.png" alt="0"></li>
				<li><img src="/IMAGE/38.png" alt="0"></li>
				<li><img src="/IMAGE/39.png" alt="0"></li>
				<li><img src="/IMAGE/40.png" alt="0"></li>
				<li><img src="/IMAGE/41.png" alt="0"></li>
				<li><img src="/IMAGE/42.png" alt="0"></li>
				<li><img src="/IMAGE/43.png" alt="0"></li>
				<li><img src="/IMAGE/44.png" alt="0"></li>
				<li><img src="/IMAGE/45.png" alt="0"></li>
				<li><img src="/IMAGE/46.png" alt="0"></li>
				<li><img src="/IMAGE/47.png" alt="0"></li>
				<li><img src="/IMAGE/48.png" alt="0"></li>
				<li><img src="/IMAGE/49.png" alt="0"></li>
				<li><img src="/IMAGE/50.png" alt="0"></li>
			</ul>
		</div>
		<div class="chapter_end_controller_one">
			<a href="">
				<div>xem tiếp chương 2</div>
				<div>tiêu đề chương 2</div>
			</a>
		</div>
		<div class="chapter_end_controller_two">
			<div>
				<a href=""> Chương trước </a>
				<button onclick="location.href='#navbar_mainPage'">lên đầu</button>
			</div>
		</div>

	</main>
	<footer class="footer" id="footer">
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
	<script>
		document.addEventListener("DOMContentLoaded",
				function() {
					const chapterSelectBtn = document
							.querySelector(".chapter-select");
					const chapterOverlay = document
							.getElementById("chapterOverlay");
					const closeBtn = chapterOverlay
							.querySelector(".chapter_box_close");

					// Mở overlay
					chapterSelectBtn.addEventListener("click", function() {
						chapterOverlay.classList.add("active");
					});

					// Đóng bằng nút X
					closeBtn.addEventListener("click", function() {
						chapterOverlay.classList.remove("active");
					});

					// Đóng khi click nền đen
					chapterOverlay.addEventListener("click", function(e) {
						if (e.target === chapterOverlay) {
							chapterOverlay.classList.remove("active");
						}
					});
				});
		document.addEventListener("DOMContentLoaded", function() {

			// ===== COMMENT OVERLAY =====
			const commentBtn = document.querySelector(".comment-button");
			const commentOverlay = document.getElementById("commentOverlay");
			const commentCloseBtn = commentOverlay
					.querySelector(".comment_box_close");
			const storyInteraction = document
					.querySelector(".story-interaction");

			// MỞ comment
			commentBtn.addEventListener("click", function() {
				commentOverlay.classList.add("active");
				storyInteraction.classList.add("hide");
			});

			// ĐÓNG comment bằng nút X
			commentCloseBtn.addEventListener("click", function() {
				commentOverlay.classList.remove("active");
				storyInteraction.classList.remove("hide");
			});

			// ĐÓNG comment khi click nền đen
			commentOverlay.addEventListener("click", function(e) {
				if (e.target === commentOverlay) {
					commentOverlay.classList.remove("active");
					storyInteraction.classList.remove("hide");
				}
			});

		});
	</script>
</body>
</html>