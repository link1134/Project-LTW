<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
.user-menu {
	position: relative;
	cursor: pointer;
}

.user_dropdown {
	position: absolute;
	top: 60px;
	right: 1rem;
	width: 200px;
	background: white;
	border-radius: 8px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
	display: none;
	flex-direction: column;
	z-index: 1000;
}

/* Dành cho các link */
.user_dropdown a {
	padding: 10px 14px;
	text-decoration: none;
	color: #333;
	font-size: 14px;
	display: block; /* để padding hoạt động như link */
}

/* Hover cho link */
.user_dropdown a:hover {
	background: #f2f2f2;
	border-radius: 8px;
}

/* Dành cho <hr> */
.user_dropdown hr {
	margin: 5px 0;
	border: none;
	border-top: 1px solid #ddd;
}

/* Dành cho nút đăng xuất */
.user_dropdown .logout button {
	padding: 10px 14px;
	font-size: 14px;
	color: #d9534f;
	background: none;
	border: none;
	width: 100%;
	text-align: left;
	cursor: pointer;
	border-radius: 8px;
}

/* Hover cho nút */
.user_dropdown .logout button:hover {
	background: #f2f2f2;
}

/*Chỗ này của cái search*/
.search_overlay {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: rgba(0, 0, 0, 0.6);
	z-index: 2000;
	opacity: 0;
	visibility: hidden;
	transition: opacity 0.3s ease;
}

.search_box {
	background: white;
	padding: 15px 20px;
	width: 100%;
	transform: translateY(-120%);
	transition: transform 0.4s ease;
	display: flex;
	justify-content: center;
}

.search_box input {
	padding: 14px 18px;
	font-size: 16px;
	border-radius: 30px;
	border: none;
	outline: none;
	background: #eee;
}

.search_overlay.active {
	opacity: 1;
	visibility: visible;
}

.search_overlay.active .search_box {
	transform: translateY(0);
}

.search_box_inner {
	display: flex;
	flex-direction: column;
	max-width: 768px;
	width: 100%
}

.search_box_inner div {
	justify-content: flex-end;
	display: flex;
	margin-top: 0.5em;
	text-decoration: none;
}

.search_box_inner div a {
	font-size: 14px;
	text-decoration: none;
	font-weight: bolder;
	color: rgb(107, 114, 128);
	font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto,
		Oxygen-Sans, DejaVu Sans, Ubuntu, Cantarell, Helvetica Neue,
		sans-serif;
}

.search_box_inner form {
	display: flex;
}

.search_box_inner form input {
	width: 100%;
}

.search_box_inner form button {
	width : 5rem;
	cursor: pointer;
	border: none;
	width: 5rem;
	background-color: transparent;
}

.main_logo {
	text-decoration: none
}
</style>
</head>
<body>
	<header class="header_wrapper">
		<nav class="navbar" id="navbar">
			<!-- Left menu -->
			<ul class="nav-left">


			</ul>

			<!-- Center logo -->
			<a class="main_logo"
				href="${pageContext.request.contextPath}/home-page">
				<div class="nav-logo">
					<img
						src="https://valvrareteam.net/images/Khong_Co_Tieu_e431_20250703112444.png"
						alt="Logo"> <span>DocTruyen</span>
				</div>
			</a>
			<!-- Right actions -->
			<ul class="nav-right">
				<li><button class="icon-btn" id="searchBtn">
						<i class="fas fa-search"></i>
					</button></li>
				<li><button class="icon-btn">
						<i class="fa fa-bell"></i>
					</button></li>
				<li class="user-menu"><img
					src="https://valvrareteam.net/images/Khong_Co_Tieu_e431_20250703112444.png"
					class="avatar" alt="User" id="userAvatar">

					<div class="user_dropdown" id="userDropdown">
						<a href="${pageContext.request.contextPath}/follow-story">
							Danh sách theo dõi</a> <a
							href="${pageContext.request.contextPath}/history-page"> Lịch
							sử đọc </a>
						<c:if test="${sessionScope.user.role == 'ADMIN'}">
							<a href="${pageContext.request.contextPath}/admin">Trang
								Admin</a>
						</c:if>
						<hr>
						<form action="${pageContext.request.contextPath}/logout"
							method="post" class="logout">
							<button type="submit" class="logout">Đăng xuất</button>
						</form>

					</div></li>

			</ul>
		</nav>
	</header>
	<div class="search_overlay" id="searchOverlay">
		<div class="search_box">
			<div class="search_box_inner">
				<form action="${pageContext.request.contextPath}/quick-search"
					method="get">
					<input type="text" name="keyword" placeholder="Tìm kiếm truyện"
						required>
					<button type="submit">
						<i class="fa-solid fa-magnifying-glass fa-xl"></i>
					</button>
				</form>

				<div>
					<a href="#">Tìm kiếm nâng cao</a>
				</div>
			</div>
		</div>
	</div>

	<script>
		const avatar = document.getElementById("userAvatar");
		const dropdown = document.getElementById("userDropdown");

		avatar.addEventListener("click", function(e) {
			e.stopPropagation();
			dropdown.style.display = dropdown.style.display === "flex" ? "none"
					: "flex";
		});

		document.addEventListener("click", function() {
			dropdown.style.display = "none";
		});

		const searchBtn = document.getElementById("searchBtn");
		const searchOverlay = document.getElementById("searchOverlay");

		searchBtn.addEventListener("click", function (e) {
		    e.stopPropagation();
		    searchOverlay.classList.add("active");

		    setTimeout(() => {
		        document.querySelector(".search_box input").focus();
		    }, 200);
		});

		
		searchOverlay.addEventListener("click", function() {
			searchOverlay.classList.remove("active");
		});

		// Không đóng khi click vào ô search
		document.querySelector(".search_box").addEventListener("click",
				function(e) {
					e.stopPropagation();
				});

		// ESC để đóng
		document.addEventListener("keydown", function(e) {
			if (e.key === "Escape") {
				searchOverlay.classList.remove("active");
			}
		});
	</script>

</body>
</html>