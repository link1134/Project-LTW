<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="vi">
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin Dashboard | Quản lý Thể loại</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/static/css/reset.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/static/css/admin/admin.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100..900;1,100..900&display=swap"
	rel="stylesheet">
</head>
<body>
	<c:if test="${not empty error}">
		<div class="error-box">⚠ ${error}</div>
	</c:if>

	<div class="dashboard-container">

		<jsp:include page="/WEB-INF/view/utility/admin_sidebar.jsp" />

		<main class="content-area">
			<header class="content-header">
				<h2>Quản lý Thể loại</h2>
				<p>Thêm, xóa các thể loại truyện.</p>
			</header>

			<section id="genres" class="content-panel active">
				<h3>Quản lý Thể loại</h3>
				<c:if test="${not empty sessionScope.message}">
					<div style="color: green; margin-bottom: 10px;">${sessionScope.message}</div>
					<c:remove var="message" scope="session" />
				</c:if>
				<c:if test="${not empty sessionScope.error}">
					<div style="color: red; margin-bottom: 10px;">${sessionScope.error}</div>
					<c:remove var="error" scope="session" />
				</c:if>
				<div class="genre-add-section">
					<h4>Thêm Thể loại mới</h4>
					<form class="inline-form"
						action="${pageContext.request.contextPath}/admin/genre-management"
						method="POST">
						<input type="text" name="genreName"
							placeholder="Tên Thể loại (ví dụ: Hành động)" required>
						<button type="submit" class="btn btn-secondary">Thêm mới</button>
					</form>
				</div>

				<div class="genre-list-section">
					<h4>Danh sách Thể loại hiện tại</h4>
					<table>
						<thead>
							<tr>
								<th>ID</th>
								<th>Tên Thể loại</th>
								<th>Thao tác</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="genre" items="${genreList}">
								<tr>
									<td>${genre.id}</td>
									<td>${genre.name}</td>
									<td><a
										href="${pageContext.request.contextPath}/admin/genre-management?action=delete&id=${genre.id}"
										class="btn btn-delete"
										onclick="return confirm('Bạn có chắc chắn muốn xóa?')">Xóa</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</section>
		</main>
	</div>
</body>
</html>