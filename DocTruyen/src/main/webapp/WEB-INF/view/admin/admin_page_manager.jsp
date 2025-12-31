<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/static/css/reset.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/static/css/admin/admin.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100..900;1,100..900&display=swap"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/335fe6f64f.js"
	crossorigin="anonymous"></script>
<title>Admin | Quản lý danh sách trang</title>

</head>
<body>
	<div class="dashboard-container">

		<aside class="sidebar">
			<div class="logo">
				<h1>Admin Panel</h1>
			</div>
			<nav class="main-nav">
				<ul>
					<li><a
						href="${pageContext.request.contextPath}/admin/dashboard"
						class="nav-item">Trang chủ</a></li>

					<li class="nav-group-title">Quản lý Nội dung</li>
					<li><a
						href="${pageContext.request.contextPath}/admin/story-list"
						class="nav-item">Danh Sách Truyện</a></li>
					<li><a
						href="${pageContext.request.contextPath}/admin/new-story"
						class="nav-item active">Đăng Truyện Mới</a></li>


					<li class="nav-group-title">Quản lý Dữ liệu</li>
					<li><a
						href="${pageContext.request.contextPath}/admin/genre-management"
						class="nav-item">Quản lý Thể loại</a></li>
				</ul>
			</nav>
			<div class="admin-logout">
				<a href="#" class="logout-btn">Đăng xuất</a>
			</div>
		</aside>

		<main class="content-area">
			<header class="content-header">
				<h2>Quản lý các trang của một chương</h2>
				<p>Thêm các trang vào chương</p>
			</header>

			<section id="new-chapter" class="content-panel active">
				<h3>
					List trang của truyện: <span id="chapter-story-title">${storyTitle}</span>
				</h3>
				<div class="chapter-controller">
					<button>Chỉnh sửa thứ tự</button>
					<button type="button"
						onclick="document.getElementById('uploadForm').style.display='block'">
						Thêm ảnh</button>

					<form id="uploadForm"
						action="${pageContext.request.contextPath}/admin/page-manager"
						method="post" enctype="multipart/form-data"
						style="display: none; margin-top: 1rem;">

						<input type="hidden" name="action" value="addPage"> <input
							type="hidden" name="chapterId" value="${param.id}"> <input
							type="hidden" name="storyId" value="${param.storyId}"> <input
							type="hidden" name="displayNum" value="${param.displayNum}">

						<input type="file" name="images" multiple required>

						<button type="submit">Upload</button>
					</form>


				</div>
				<div class="table-wrapper">
					<table>
						<thead>
							<tr>
								<th>ID</th>

								<th>Ảnh</th>
								<th>Chapter id</th>
								<th>Số trang</th>

								<th>Thao tác</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach var="page" items="${pages}">
								<tr>

									<td>${page.id}</td>
									<td><img
										src="${pageContext.request.contextPath}/${page.pageURL}"
										alt="page ${page.pageNumber}"
										style="height: 5rem; object-fit: cover; border-radius: 4px;"></td>
									<td>${page.chapterId}</td>
									<td>${page.pageNumber}</td>
									<td class="chapter_action_group">

										<button type="button">
											<i class="fa-solid fa-gear"></i>
										</button>
										<button>
											<i class="fa-solid fa-xmark" style="color: red"></i>
										</button>
									</td>
								</tr>

							</c:forEach>
						</tbody>
					</table>
				</div>
			</section>
		</main>
	</div>
	<script>
		const contextPath = "${pageContext.request.contextPath}";
	</script>
</body>
</html>