<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon"href="https://valvrareteam.net/images/Khong_Co_Tieu_e431_20250703112444.png"type="image/png">
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
<title>Admin | Quản lý danh sách chương</title>

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
				<h2>Quản lý các chương của truyện</h2>
				<p>Tạo chương mới, chỉnh sửa và thêm nội dung cho các chương</p>
			</header>

			<section id="new-chapter" class="content-panel active">
				<h3>
					List chương của truyện: <span id="chapter-story-title">${storyTitle}</span>
				</h3>
				<div class="chapter-controller">
					<button>Chỉnh sửa thứ tự</button>
					<button type="button" id="btn-show-add">Thêm chapter mới</button>
				</div>

				<table>
					<thead>
						<tr>
							<th>ID</th>
							<th>STT</th>
							<th>Số chapter hiển thị</th>
							<th>Tựa đề</th>
							<th>Ngày đăng</th>
							<th>Trạng thái</th>
							<th>Thao tác</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="chapter" items="${chapters}">
							<tr>
								<td>${chapter.id}</td>

								<td>${chapter.chapterNumber}</td>

								<td><strong>${chapter.displayNumChapter}</strong></td>

								<td>${chapter.title}</td>

								<td>${chapter.publishedAt}</td>
								<td><select class="status-select"
									data-chapter-id="${chapter.id}">
										<option value="DRAFT"
											${chapter.status == 'DRAFT' ? 'selected' : ''}>
											DRAFT</option>
										<option value="PUBLISHED"
											${chapter.status == 'PUBLISHED' ? 'selected' : ''}>
											PUBLISHED</option>
								</select></td>
								<td class="chapter_action_group"><a
									href="${pageContext.request.contextPath}/admin/page-manager?id=${chapter.id}&displayNum=${chapter.displayNumChapter}&storyId=${storyId}">
										<button>
											<i class="fa-solid fa-file-pen"></i>
										</button>
								</a>
									<button type="button" class="btn-edit"
										data-chapter-id="${chapter.id}"
										data-display="${chapter.displayNumChapter}"
										data-title="${chapter.title}">
										<i class="fa-solid fa-gear"></i>
									</button>
									<button>
										<i class="fa-solid fa-xmark" style="color: red"></i>
									</button></td>
							</tr>
						</c:forEach>
						<tr id="add-chapter-row" style="display: none;">
							<form method="post"
								action="${pageContext.request.contextPath}/admin/chapter-manager">
								<input type="hidden" name="action" value="addChapter"> <input
									type="hidden" name="storyId" value="${param.storyId}">
								<input type="hidden" name="storyTitle" value="${storyTitle}">

								<td>—</td>
								<td>—</td>

								<td><input type="text" name="displayNumChapter"
									placeholder="VD: Chương 2.1" required></td>

								<td><input type="text" name="title"
									placeholder="Tựa đề chương" ></td>

								<td>—</td>

								<td>
									<button type="submit">Đăng</button>
								</td>
							</form>
						</tr>
						<tr id="edit-chapter-row" style="display: none;">
							<form method="post"
								action="${pageContext.request.contextPath}/admin/chapter-manager">

								<input type="hidden" name="action" value="editChapter">
								<input type="hidden" name="chapterId" id="edit-chapter-id">
								<input type="hidden" name="storyId" value="${param.storyId}">
								<input type="hidden" name="oldDisplay" id="old-display">

								<td>—</td>
								<td>—</td>

								<td><input type="text" name="displayNumChapter"
									id="edit-display" required></td>

								<td><input type="text" name="title" id="edit-title"
									required></td>

								<td>Update</td>

								<td>
									<button type="submit">Lưu</button>
								</td>
							</form>
						</tr>


					</tbody>
				</table>
			</section>
		</main>
	</div>
	<script>
		const contextPath = "${pageContext.request.contextPath}";
	</script>

	<script
		src="${pageContext.request.contextPath}/static/js/statusChange.js"></script>
	<script>
document.getElementById("btn-show-add")
    .addEventListener("click", () => {
        const row = document.getElementById("add-chapter-row");
        row.style.display = row.style.display === "none"
            ? "table-row"
            : "none";
    });
</script>
	<script>
let currentEditingId = null;

document.querySelectorAll(".btn-edit").forEach(btn => {
    btn.addEventListener("click", () => {

        const chapterId = btn.dataset.chapterId;
        const editRow = document.getElementById("edit-chapter-row");
        const addRow = document.getElementById("add-chapter-row");

        // Hide add row nếu đang mở
        addRow.style.display = "none";

        // 👉 CASE 1: Click lại cùng 1 chapter → toggle OFF
        if (currentEditingId === chapterId) {
            editRow.style.display = "none";
            currentEditingId = null;
            return;
        }

        // 👉 CASE 2: Edit chapter khác
        currentEditingId = chapterId;
        editRow.style.display = "table-row";

        // Fill dữ liệu
        document.getElementById("edit-chapter-id").value = chapterId;
        document.getElementById("edit-display").value = btn.dataset.display;
        document.getElementById("old-display").value = btn.dataset.display;
        document.getElementById("edit-title").value = btn.dataset.title;

        // Move edit row xuống ngay dưới dòng đang sửa
        btn.closest("tr").after(editRow);
    });
});
</script>



</body>
</html>