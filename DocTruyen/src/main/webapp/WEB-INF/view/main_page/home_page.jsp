<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Home Page</title>
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
	href="${pageContext.request.contextPath }/static/css/home_page/style.css">

</head>

<body>
	<jsp:include page="/WEB-INF/view/utility/header.jsp" />
	<main>
		<div class="big-wrapper">
			<div class="big-carousel">
				<div class="big-card card-no-marker"></div>
				<div class="big-card">
					<img
						src="${pageContext.request.contextPath}/IMAGE/image_processing20251206-2-12ogpga.jpg"
						alt="" class="big-card-img">
					<div class="big-card-content">
						<div class="big-card-description">
							<div>
								<a href="">
									<h2 class="big-card-name">Trường Hợp Tôi Đi Theo Tiểu Thư
										Băng Giá Và Bị Cô Ấy Cưng Chiều Đến Mức Phát Điên!</h2>
								</a>
							</div>
							<div class="big-card-small-description">
								<p>Chạn vương</p>
							</div>
						</div>
						<div class="big-card-details-btn">
							<a href=""> Xem thông tin </a>
						</div>
					</div>
				</div>
				<div class="big-card"></div>
				<div class="big-card"></div>
				<div class="big-card"></div>
				<div class="big-card"></div>
				<div class="big-card"></div>
				<div class="big-card"></div>
				<div class="big-card"></div>
				<div class="big-card"></div>
				<div class="big-card card-no-marker"></div>

			</div>
			<div class="big-background"></div>
		</div>
		<div class="list_manga">
			<h1>Truyện mới</h1>
			<div class="carousel">
				<c:forEach var="story" items="${newestStories}">
					<div class="card">
						<div class="card-wrapped">
							<a href="${pageContext.request.contextPath}/story-detail?id=${story.id}"> 
								<img src="${pageContext.request.contextPath}/${story.coverImageURL}"alt="<c:out value='${story.title}'/>" class="card-img">
							</a>
						</div>
						<div class="card-description">
							<a href="" class="card-description-name">
								<h3><c:out value="${story.title}"/></h3>
							</a>
							<h5>
								<a href="" class="card-description-chapter_time"> <span>C.6</span>
									<span>6 ngày trước</span>
								</a>

							</h5>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
				
		<div class="list_manga bg_secondl">
			<h1>ROMCOM</h1>
			<div class="carousel">
				<c:forEach var="story" items="${romcomStories}">
					<div class="card">
						<div class="card-wrapped">
							<a href="${pageContext.request.contextPath}/story-detail?id=${story.id}"> 
                        		<img src="${pageContext.request.contextPath}/${story.coverImageURL}"alt="<c:out value='${story.title}'/>" class="card-img">
                    		</a>
						</div>
						<div class="card-description">
							<a href="${pageContext.request.contextPath}/story-detail?id=${story.id}" class="card-description-name">
                        		<h3><c:out value="${story.title}"/></h3>
                    		</a>
							<h5>
								<a href="" class="card-description-chapter_time"> <span>C.1</span>
									- <span>1 ngày trước</span>
								</a>
	
							</h5>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="list_manga bg_thirdl">
			<h1>ONESHOT</h1>
			<div class="carousel">
				<c:forEach var="story" items="${oneshotStories}">
					<div class="card">
						<div class="card-wrapped">
							<a href="${pageContext.request.contextPath}/story-detail?id=${story.id}"> 
                        		<img src="${pageContext.request.contextPath}/${story.coverImageURL}"alt="<c:out value='${story.title}'/>" class="card-img">
                    		</a>
						</div>
						<div class="card-description">
							<a href="${pageContext.request.contextPath}/story-detail?id=${story.id}" class="card-description-name">
                        		<h3><c:out value="${story.title}"/></h3>
                    		</a>
							<h5>
								<a href="" class="card-description-chapter_time"> <span>C.1</span>
									- <span>1 ngày trước</span>
								</a>
	
							</h5>
						</div>
					</div>
				</c:forEach>
				
			</div>
		</div>
		<div class="list_manga bg_fourthl">
			<h1>FANTASY</h1>
			<div class="carousel">
				<c:forEach var="story" items="${fantasyStories}">
					<div class="card">
						<div class="card-wrapped">
							<a href="${pageContext.request.contextPath}/story-detail?id=${story.id}"> 
                        		<img src="${pageContext.request.contextPath}/${story.coverImageURL}"alt="<c:out value='${story.title}'/>" class="card-img">
                    		</a>
						</div>
						<div class="card-description">
							<a href="${pageContext.request.contextPath}/story-detail?id=${story.id}" class="card-description-name">
                        		<h3><c:out value="${story.title}"/></h3>
                    		</a>
							<h5>
								<a href="" class="card-description-chapter_time"> <span>C.1</span>
									- <span>1 ngày trước</span>
								</a>
	
							</h5>
						</div>
					</div>
				</c:forEach>
				
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
	<script>
        const navbar = document.getElementById('navbar');
        // Lấy tất cả chữ và icon trong navbar
        const navbarItems = navbar.querySelectorAll('a, span, .icon-btn i, .avatar');

        window.addEventListener('scroll', () => {
            if (window.scrollY > 50) {
                navbar.classList.add('scrolled');
                // đổi màu chữ/icon khi scroll
                navbarItems.forEach(el => {
                    el.style.color = 'black';
                });
            } else {
                navbar.classList.remove('scrolled');
                // trả màu chữ/icon về mặc định
                navbarItems.forEach(el => {
                    el.style.color = '';
                });
            }
        });
    </script>
</body>

</html>