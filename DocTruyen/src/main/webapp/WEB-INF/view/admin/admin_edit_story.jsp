<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon"href="https://valvrareteam.net/images/Khong_Co_Tieu_e431_20250703112444.png"type="image/png">
    <title>Admin Dashboard | Chỉnh Sửa Truyện</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/admin/admin.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
</head>
<body>
    <div class="dashboard-container">
        <aside class="sidebar">
            <div class="logo"><h1>Admin Panel</h1></div>
            <nav class="main-nav">
                <ul>
                    <li><a href="${pageContext.request.contextPath}/admin/dashboard" class="nav-item">Trang chủ</a></li>
                    <li class="nav-group-title">Quản lý Nội dung</li>
                    <li><a href="${pageContext.request.contextPath}/admin/story-list" class="nav-item">Danh Sách Truyện</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/new-story" class="nav-item">Đăng Truyện Mới</a></li>
                    <li class="nav-group-title">Quản lý Dữ liệu</li>
                    <li><a href="${pageContext.request.contextPath}/admin/genre-management" class="nav-item">Quản lý Thể loại</a></li>
                </ul>
            </nav>
        </aside>

        <main class="content-area">
            <header class="content-header">
                <h2>Chỉnh Sửa Truyện</h2>
                <p>Cập nhật thông tin chi tiết cho truyện: <strong>${story.title}</strong></p>
            </header>
            
            <section class="content-panel">
                <form class="admin-form" enctype="multipart/form-data" action="${pageContext.request.contextPath}/admin/edit-story" method="POST">
                    
                    <input type="hidden" name="id" value="${story.id}">
                    
                    <div class="form-group">
                        <label for="title">Tên Truyện:</label>
                        <input type="text" id="title" name="Title" value="${story.title}" required>
                    </div>

                    <div class="form-group">
                        <label for="author">Tên Tác Giả:</label>
                        <input type="text" id="author" name="Author" value="${story.author}" required>
                    </div>

                    <div class="form-group">
                        <label for="thumbnail">Ảnh Thumbnail (Bìa nhỏ):</label>
                        <div style="margin: 10px 0;">
                            <p style="font-size: 13px; color: #666;">Bìa hiện tại:</p>
                            <img src="${pageContext.request.contextPath}/${story.coverImageURL}" width="80" style="border-radius: 4px; border: 1px solid #ddd;">
                        </div>
                        <input type="file" id="thumbnail" name="ThumnailImageURL" accept="image/*">
                        <small class="help-text">Chọn file mới nếu muốn thay đổi ảnh bìa nhỏ.</small>
                    </div>

                    <div class="form-group">
                        <label for="cover-image">Ảnh Cover (Bìa lớn):</label>
                        <div style="margin: 10px 0;">
                            <p style="font-size: 13px; color: #666;">Cover hiện tại:</p>
                            <img src="${pageContext.request.contextPath}/${story.bigCoverImageURL}" width="150" style="border-radius: 4px; border: 1px solid #ddd;">
                        </div>
                        <input type="file" id="cover-image" name="CoverImageURL" accept="image/*">
                        <small class="help-text">Chọn file mới nếu muốn thay đổi ảnh bìa lớn.</small>
                    </div>

                    <div class="form-group">
                        <label>Chọn Thể loại:</label>
                        <div class="genre-radio-buttons" style="max-height: 150px; overflow-y: auto; border: 1px solid #eee; padding: 10px; border-radius: 5px; background: #fafafa;">
                            <c:forEach var="g" items="${genreList}">
                                <label style="display: block; margin-bottom: 8px; cursor: pointer;">
                                    <input type="checkbox" name="genreID" value="${g.id}" 
                                        <c:forEach var="selectedId" items="${selectedGenreIds}">
                                            <c:if test="${selectedId == g.id}">checked</c:if>
                                        </c:forEach>
                                    > ${g.name}
                                </label>
                            </c:forEach>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="description">Mô tả Truyện:</label>
                        <textarea id="description" name="Description" rows="5" required>${story.description}</textarea>
                    </div>
                    
                    <div class="form-actions" style="margin-top: 20px;">
                        <button type="submit" class="btn btn-primary">Lưu Thay Đổi</button>
                        <a href="${pageContext.request.contextPath}/admin/story-list" class="btn btn-secondary">Hủy bỏ</a>
                    </div>
                </form>
            </section>
        </main>
    </div>
</body>
</html>