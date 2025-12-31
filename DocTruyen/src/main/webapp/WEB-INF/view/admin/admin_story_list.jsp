<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin Dashboard | Danh Sách Truyện</title>
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
	<div class="dashboard-container">

		<jsp:include page="/WEB-INF/view/utility/admin_sidebar.jsp" />

		<main class="content-area">
			<header class="content-header">
				<h2>Danh Sách Truyện</h2>
				<p>Quản lý và cập nhật nội dung truyện.</p>
			</header>

			<section id="story-list" class="content-panel active">
				<h3>Danh Sách Truyện Hiện Có</h3>

				<div class="search-bar">
					<input type="text"
						placeholder="Tìm kiếm theo tên truyện, tác giả...">
					<button class="btn btn-secondary">Tìm kiếm</button>
				</div>

				<table>
					<thead>
						<tr>
							<th>ID</th>
							<th>Bìa</th>
							<th>Tên Truyện</th>
							<th>Tác giả</th>
							<th>Thao tác</th>
							<th>Lần cuối cập nhật</th>
							<th>Số chương</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="story" items="${storyList}">
							<tr>
								<td>${story.id}</td>

								<td><img
									src="${pageContext.request.contextPath}/${story.coverImageURL}"
									alt="cover"
									style=" height: 5rem; object-fit: cover; border-radius: 4px;">
								</td>

								<td><strong>${story.title}</strong></td>

								<td>${story.author}</td>

								<td>
									<div class="btn-group">
										<c:url var="chapterManagerUrl" value="/admin/chapter-manager">
											<c:param name="storyId" value="${story.id}" />
											<c:param name="storyTitle" value="${story.title}" />
										</c:url>
										<a href="${chapterManagerUrl}" class="btn btn-action">
											Quản lý chapter </a> <a
											href="${pageContext.request.contextPath}/admin/edit-story?id=${story.id}"
											class="btn btn-edit">Sửa</a> <a
											href="${pageContext.request.contextPath}/admin/edit-story?id=${story.id}"
											class="btn btn-edit">Xóa</a>
									</div>
								</td>

								<!-- để trống -->
								<td></td>
								<td></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</section>
		</main>
	</div>
</body>
</html>