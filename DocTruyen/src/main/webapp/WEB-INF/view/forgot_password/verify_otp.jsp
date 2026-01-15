<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="vn">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Đọc Truyện || Nhập OTP</title>
<link rel="icon"href="https://valvrareteam.net/images/Khong_Co_Tieu_e431_20250703112444.png"type="image/png">
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
        <h2 class="intro__title">Xác thực OTP</h2>
        <p class="intro__text">Mã xác thực đã được gửi đến email: <b>${sessionScope.email}</b></p>
        <p class="intro__text">Vui lòng kiểm tra hộp thư (hoặc thư rác).</p>
        <img src="https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExM3Z0bjZubXJpbm5ueWZ4dzRndWdyNnZpZndxeDRndWdyNnZpZndxeCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/3o7TKVUn7iM8FMEU24/giphy.gif" alt="waiting">
    </section>

    <form action="${pageContext.request.contextPath}/verify-otp" class="login__form" method="post">
        <h1 class="form__title">Nhập mã OTP</h1>
        <div class="form__group">
            <input type="text" class="form__input" placeholder="Nhập mã OTP 6 số" name="otp" required maxlength="6">
        </div>
        <div class="form__group">
            <button class="form__button" type="submit">Xác nhận</button>
        </div>
    </form>
</main>
</body>
</html>