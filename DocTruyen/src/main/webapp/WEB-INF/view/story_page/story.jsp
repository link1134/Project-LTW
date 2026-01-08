<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vn">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet"
	href="${pageContext.request.contextPath }/static/css/reset.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/story_page/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100..900;1,100..900&display=swap"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/335fe6f64f.js"
	crossorigin="anonymous"></script>
<title>Tittle</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/utility/header.jsp" />
	<main class="story-container">
		<div class="story-content">
			<div class="story-header-banner">
				<div class="banner-overlay">
					<img
						src="${pageContext.request.contextPath}/${story.bigCoverImageURL}"
						alt="<c:out value='${story.title}'/>" class="banner-img">
				</div>
			</div>

			<div class="story-info-block">
				<div class="story-info-left">
					<img class="story-thumbnail"
						src="${pageContext.request.contextPath}/${story.coverImageURL}"
						alt="<c:out value='${story.title}'/>">
				</div>

				<div class="story-info-right">
					<div class="story-title-group">
						<h1 class="story-name">
							<c:out value="${story.title}" />
						</h1>
						<p class="story-author">
							Tác giả:
							<c:out value="${story.author}" />
						</p>
						<p class="story-update-time">
							Cập nhật:
							<c:out value="${story.timeAgo}" />
						</p>
					</div>

					<div class="story-genres">
						<c:forEach var="genre" items="${storyGenres}">
							<a href="${pageContext.request.contextPath}/genre?id=${genre.id}"
								class="genre-tag"> <c:out value="${genre.name}" />
							</a>
						</c:forEach>
					</div>

					<div class="story-actions">
						<c:if test="${not empty sessionScope.user}">
							<form action="${pageContext.request.contextPath}/follow-story"
								method="post">
								<input type="hidden" name="storyId" value="${story.id}">
								<button type="submit" class="btn btn-follow">
									<i class="fa-solid fa-heart"></i>
									<c:choose>
										<c:when test="${isFollowed}">
                    Đã theo dõi
                </c:when>
										<c:otherwise>
                    Theo dõi
                </c:otherwise>
									</c:choose>
								</button>
							</form>
						</c:if>
						<a href="#" class="btn btn-read-first">Đọc từ chương 1</a>
					</div>
				</div>
			</div>

			<div class="story-details-grid">
				<div class="details-left">
					<div class="section-card">
						<h3 class="section-title">Nội dung truyện</h3>
						<div class="story-description">
							<p>
								<c:out value="${story.description}" />
							</p>
						</div>
					</div>
				</div>

				<div class="details-right">
					<div class="section-card stats-card">
						<div class="stat-item">
							<span class="stat-label">Số chương:</span> <span
								class="stat-value"><c:out value="${story.numChapter}" /></span>
						</div>
						<div class="stat-item">
							<span class="stat-label">Lượt xem:</span> <span
								class="stat-value"><c:out value="${story.viewCount}" /></span>
						</div>
					</div>
				</div>
			</div>

			<div class="section-card chapter-section">
				<h3 class="section-title">Danh sách chương</h3>
				<div class="chapter-list-wrapper">
					<ul class="chapter-list">
						<c:forEach var="chap" items="${chapters}">
							<li class="chapter-item"><a
								href="read-chapter?id=${chap.id}" class="chapter-link"> <span
									class="chap-number">Chương <c:out
											value="${chap.displayNumChapter}" /></span> <span
									class="chap-title"><c:out value="${chap.title}" /></span> <span
									class="chap-time"><c:out value="${chap.timeAgo}" /></span>
							</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>

			<div class="section-card similar-section">
				<h3 class="section-title">Truyện tương tự</h3>
				<div class="similar-grid">
					<c:forEach var="sim" items="${similarStories}">
						<div class="similar-card">
							<a
								href="${pageContext.request.contextPath}/story-detail?id=${sim.id}">
								<div class="similar-thumb">
									<img
										src="${pageContext.request.contextPath}/${sim.coverImageURL}"
										alt="${sim.title}">
								</div>
								<div class="similar-info">
									<h4 class="similar-name">
										<c:out value="${sim.title}" />
									</h4>
									<div class="similar-meta">
										<span>C.${sim.numChapter}</span> <span>${sim.timeAgo}</span>
									</div>
								</div>
							</a>
						</div>
					</c:forEach>
				</div>
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