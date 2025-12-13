<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/admin/admin.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    <title>Admin | Đăng Chương Mới</title>
</head>
<body>
    <div class="dashboard-container">
        
        <aside class="sidebar">
            <div class="logo">
                <h1>Admin Panel</h1>
            </div>
            <nav class="main-nav">
                <ul>
                    <li><a href="${pageContext.request.contextPath}/admin/dashboard" class="nav-item">Trang chủ</a></li>
                    
                    <li class="nav-group-title">Quản lý Nội dung</li>
                    <li><a href="${pageContext.request.contextPath}/admin/story-list" class="nav-item">Danh Sách Truyện</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/new-story" class="nav-item active">Đăng Truyện Mới</a></li>
                    
                    
                    <li class="nav-group-title">Quản lý Dữ liệu</li>
                    <li><a href="${pageContext.request.contextPath}/admin/genre-management" class="nav-item">Quản lý Thể loại</a></li>
                </ul>
            </nav>
            <div class="admin-logout">
                <a href="#" class="logout-btn">Đăng xuất</a>
            </div>
        </aside>
        
        <main class="content-area">
            <header class="content-header">
                <h2>Đăng Chương Mới</h2>
                <p>Thực hiện upload ảnh cho nội dung chương truyện.</p>
            </header>

            <section id="new-chapter" class="content-panel active">
                <h3>Đăng Chương Mới cho: <span id="chapter-story-title">[Tên Truyện]</span></h3>
                
                <form class="admin-form" method="POST" enctype="multipart/form-data">
                    
                    <input type="hidden" id="selected-story-id" name="story_id" value=""> 
                    
                    <div class="form-group">
                        <label for="chapter-number">Số Chương:</label>
                        <input type="number" id="chapter-number" name="chapter-number" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="chapter-title">Tên Chương (Tiêu đề):</label>
                        <input type="text" id="chapter-title" name="chapter-title">
                    </div>
                    
                    <div class="form-group">
                        <label for="chapter-content">Nội dung Chương (Chọn nhiều hình ảnh):</label>
                        <input 
                            id="chapter-content" 
                            name="chapter-content[]" 
                            type="file" 
                            accept="image/*" 
                            multiple 
                            required>
                         <small class="help-text">Chọn một hoặc nhiều tệp hình ảnh cho nội dung chương.</small>
                    </div>
                    
                    <button type="submit" class="btn btn-primary">Đăng Chương</button>
                </form>
            </section>
        </main>
    </div>
</body>
</html>