<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="vn">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Đọc Truyện || Lấy lại mật khẩu</title>
<link rel="icon"
	href="https://valvrareteam.net/images/Khong_Co_Tieu_e431_20250703112444.png"
	type="image/png">
<!-- main css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/login_register/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/static/css/reset.css">
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


	<main>
		<section class="intro">
			<h2 class="intro__title">Lấy lại mật khẩu</h2>
			<p class="intro__text">Nhập email của bạn để nhận mã xác thực
				OTP.</p>
			<img
				src="https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExNHJndXpueG94bmpxbmFwamRndm9vbmRndXpueG94bmpxbmFwaCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/3o7TKVUn7iM8FMEU24/giphy.gif"
				alt="waiting">
		</section>

		<form action="${pageContext.request.contextPath}/forgot-password"
			class="login__form" method="post">
			<h1 class="form__title">Quên mật khẩu</h1>
			<div class="form__group">
				<input type="email" class="form__input" placeholder="Nhập email"
					name="email" required>
			</div>
			<div class="form__group">
				<button class="form__button" type="submit">Gửi mã xác nhận</button>
			</div>
			<div class="form__links">
				<a href="${pageContext.request.contextPath}/login"
					class="form__link">Quay lại Đăng nhập</a>
			</div>
		</form>
	</main>

	<footer class="footer">
		<div class="footer__container">
			<span class="footer__copy">© 2025 - doctruyen.net</span>
		</div>
	</footer>

	<script>
        const errorBox = document.getElementById("errorBox");
        if (errorBox) {
            setTimeout(() => { errorBox.style.display = 'none'; }, 3500);
        }
    </script>
</body>
</html>