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
<link rel="icon"
	href="https://valvrareteam.net/images/Khong_Co_Tieu_e431_20250703112444.png"
	type="image/png">
<title>Chapter ${currentChapter.displayNumChapter}</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/utility/header.jsp" />
	<main>
		<div class="story-interaction">
			<button class="comment-button">
				<span>0</span> <span><i class="fa-solid fa-message"></i></span>
			</button>

			<div class="content-select-controller">


				<c:choose>
					<c:when test="${prevChapter == null}">
						<button class="previous-chapter-button"
							style="opacity: 0.4; pointer-events: none; cursor: default;">
							<i class="fa-solid fa-chevron-left"></i>
						</button>
					</c:when>
					<c:otherwise>
						<a
							href="${pageContext.request.contextPath}/read-chapter?id=${prevChapter.id}">
							<button class="previous-chapter-button">
								<i class="fa-solid fa-chevron-left"></i>
							</button>
						</a>
					</c:otherwise>
				</c:choose>


				<button class="chapter-select">
					<span>Chương ${currentChapter.displayNumChapter}</span>

				</button>


				<c:choose>
					<c:when test="${nextChapter == null}">
						<button class="next-chapter-button"
							style="opacity: 0.4; pointer-events: none; cursor: default;">
							<span>Tiếp</span> <span><i
								class="fa-solid fa-chevron-right"></i></span>
						</button>
					</c:when>
					<c:otherwise>
						<a
							href="${pageContext.request.contextPath}/read-chapter?id=${nextChapter.id}">
							<button class="next-chapter-button">
								<span>Tiếp</span> <span><i
									class="fa-solid fa-chevron-right"></i></span>
							</button>
						</a>
					</c:otherwise>
				</c:choose>

			</div>

			<button class="up-button">
				<a href="#navbar"><i class="fa-solid fa-chevron-up"></i></a>

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

						<c:forEach var="chap" items="${allChapters}">
							<a
								href="${pageContext.request.contextPath}/read-chapter?id=${chap.id}"
								class="${chap.id == currentChapter.id ? 'active' : ''}">
								Chương
								<div>${chap.displayNumChapter}</div>
							</a>
						</c:forEach>

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
				<a
					href="${pageContext.request.contextPath}/story-detail?id=${story.id}"
					class="story_name"> <span>← ${story.title}</span>
				</a>
			</div>
			<h1 class="story_chapter_name">
				<span> Chương ${currentChapter.displayNumChapter} <c:if
						test="${haveTitle}">
            : ${currentChapter.title}
        </c:if>
				</span>
			</h1>
			<div class="chapter_controls">
				<!-- Chương trước -->
				<div>
					<c:choose>
						<c:when test="${prevChapter == null}">
							<a style="opacity: 0.4; pointer-events: none; cursor: default;">
								chương trước </a>
						</c:when>
						<c:otherwise>
							<a
								href="${pageContext.request.contextPath}/read-chapter?id=${prevChapter.id}">
								chương trước </a>
						</c:otherwise>
					</c:choose>
				</div>

				<!-- Chương sau -->
				<div>
					<c:choose>
						<c:when test="${nextChapter == null}">
							<a style="opacity: 0.4; pointer-events: none; cursor: default;">
								chương sau </a>
						</c:when>
						<c:otherwise>
							<a
								href="${pageContext.request.contextPath}/read-chapter?id=${nextChapter.id}">
								chương sau </a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>

		</div>
		<div class="story_content">
			<ul class="list_img">
				<c:forEach var="page" items="${pages}">
					<li><img
						src="${pageContext.request.contextPath}/${page.pageURL}"
						alt="page ${page.id}"></li>
				</c:forEach>
			</ul>
		</div>

		<div class="chapter_end_controller_one">
			<c:choose>
				<c:when test="${nextChapter == null}">
					<a style="opacity: 0.4; pointer-events: none; cursor: default;">
						<div>Chương mới nhất</div>
					</a>
				</c:when>

				<c:otherwise>
					<a
						href="${pageContext.request.contextPath}/read-chapter?id=${nextChapter.id}">
						<div>Xem tiếp chương ${nextChapter.displayNumChapter}</div> <c:if
							test="${nextChapterHaveTitle}">
							<div>${nextChapter.title}</div>
						</c:if>
					</a>
				</c:otherwise>
			</c:choose>
		</div>


		<div class="chapter_end_controller_two">
			<div>
				<c:choose>
					<c:when test="${prevChapter == null}">
						<a style="opacity: 0.4; pointer-events: none; cursor: default;">
							Chương cũ nhất </a>
					</c:when>

					<c:otherwise>
						<a
							href="${pageContext.request.contextPath}/read-chapter?id=${prevChapter.id}">
							Chương trước </a>
					</c:otherwise>
				</c:choose>

				<button onclick="location.href='#navbar'">lên đầu</button>
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