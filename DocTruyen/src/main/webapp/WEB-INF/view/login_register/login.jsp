<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="vn">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Đọc Truyện || Đăng Nhập</title>
<link rel="icon"href="https://valvrareteam.net/images/Khong_Co_Tieu_e431_20250703112444.png"type="image/png">
<!-- main css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/static/css/reset.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/login_register/style.css">

<!-- google font -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100..900;1,100..900&display=swap"
	rel="stylesheet">

</head>
<body>
	<!-- font awesome -->
	<script src="https://kit.fontawesome.com/335fe6f64f.js"
		crossorigin="anonymous"></script>
	<!-- main js  -->
	<script src="./JS/main.js" defer></script>
	<div class="background-container"></div>
	<!-- header -->
	<c:if test="${not empty sessionScope.error}">
		<div class="toast-error" id="loginError">${sessionScope.error}</div>
		<c:remove var="error" scope="session" />
	</c:if>

	<header>
		<nav class="nav-bar">
			<div class="nav-bar__content">
				<a class="nav-bar__content--left"
					href="${pageContext.request.contextPath }/home-page"
					data-discorver="true"> <img
					src="https://valvrareteam.net/images/Khong_Co_Tieu_e431_20250703112444.png"
					alt="" class="nav-bar__content--left--logo">
					<div class="nav-bar__content--left--tittle-container">
						<h1 class="nav-bar__content--left--tittle">DocTruyen</h1>
					</div>
				</a>
				<nav class="nav-bar__content--right">
					<a href="#" class="nav-bar__content--right--link" id="facebook">Fanpage</a>
					<a href="#" class="nav-bar__content--right--link" id="discord">Discord</a>
				</nav>
			</div>
		</nav>
	</header>
	<!-- main -->
	<main>
		<!-- intro -->
		<section class="intro">
			<h2 class="intro__title">Chào mừng bạn đến với DocTruyen!</h2>
			<p class="intro__text">Nơi bạn có thể khám phá hàng ngàn truyện
				tranh và tiểu thuyết đặc sắc.</p>
			<p class="intro__text">Đăng ký ngay để lưu lại truyện yêu thích!</p>
			<img
				src="https://media1.giphy.com/media/v1.Y2lkPTc5MGI3NjExcHplM2dicGQxbGZodHllcjVqdzVzcm12djVvOGR0a3lwc2d4NHV4YSZlcD12MV9naWZzX3NlYXJjaCZjdD1n/11lxCeKo6cHkJy/giphy.webp"
				alt="dacing welcom">
		</section>
		<!-- formlogin -->
		<form action="${pageContext.request.contextPath}/login"
			class="login__form" method="post">
			<h1 class="form__title">Đăng nhập</h1>
			<div class="from_group">
				<input type="text" class="form__input" placeholder="Nhập email"
					name="email" required>
			</div>
			<div class="form__group">
				<input type="password" class="form__input"
					placeholder="Nhập mật khẩu" name="password" required>
			</div>
			<div class="form__group">
				<button class="form__button" type="submit">Đăng nhập</button>
			</div>
			<div class="form__links">
				<a href="${pageContext.request.contextPath}/register"
					class="form__link">bạn chưa có tài khoản?</a> 
					<a href="${pageContext.request.contextPath}/forgot-password"class="form__link">bạn quên mật khẩu?</a>

			</div>


			</div>
		</form>
	</main>
	<!-- footer -->
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
    const errorBox = document.getElementById("loginError");

    if (errorBox) {
        setTimeout(() => {
            errorBox.classList.add("fade-out");
        }, 2500);

        setTimeout(() => {
            errorBox.remove();
        }, 3500);
    }
</script>

</body>
</html>