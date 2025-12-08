<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard | Đăng Truyện Mới</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/admin/admin.css">
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
                <h2>Đăng Truyện Mới</h2>
                <p>Thêm thông tin và tệp bìa cho truyện mới.</p>
            </header>
            
            <section id="new-story" class="content-panel hidden">
                <h3>Đăng Truyện Mới</h3>
                <form class="admin-form" enctype="multipart/form-data">
                    
                    <div class="form-group">
                        <label for="title">Tên Truyện:</label>
                        <input type="text" id="title" name="Title" required>
                    </div>

                    <div class="form-group">
                        <label for="author">Tên Tác Giả:</label>
                        <input type="text" id="author" name="Author" required>
                    </div>

                    <div class="form-group">
                        <label for="thumbnail">Ảnh Thumbnail (Bìa nhỏ):</label>
                        <input type="file" id="thumbnail" name="ThumnailImageURL" accept="image/*" required>
                        <small class="help-text">Chọn ảnh bìa nhỏ cho truyện (hiển thị trong danh sách).</small>
                    </div>

                    <div class="form-group">
                        <label for="cover-image">Ảnh Cover (Bìa lớn):</label>
                         <input type="file" id="cover-image" name="CoverImageURL" accept="image/*" required>
                        <small class="help-text">Chọn ảnh bìa lớn cho truyện (hiển thị trên trang chi tiết).</small>
                    </div>

                    <div class="form-group">
                        <label>Chọn Thể loại:</label>
                        <div class="genre-radio-buttons">
                            <label><input type="radio" name="GenreId" value="1" checked> Hài hước</label>
                            <label><input type="radio" name="GenreId" value="2"> Hành động</label>
                            <label><input type="radio" name="GenreId" value="3"> Lãng mạn</label>
                            <label><input type="radio" name="GenreId" value="4"> Giả tưởng</label>
                            <label><input type="radio" name="GenreId" value="5"> Phiêu lưu</label>
                        </div>
                        <small class="help-text">Truyện được phân loại theo một thể loại chính.</small>
                    </div>

                    <div class="form-group">
                        <label for="description">Mô tả Truyện:</label>
                        <textarea id="description" name="Description" rows="5" required></textarea>
                    </div>
                    
                    <div class="form-group">
                        <label for="status">Trạng thái:</label>
                        <select id="status" name="Status">
                            <option value="in-progress">Đang tiến hành</option>
                            <option value="completed">Hoàn thành</option>
                            <option value="paused">Tạm ngưng</option>
                        </select>
                    </div>
                    
                    <button type="submit" class="btn btn-primary">Đăng Truyện</button>
                </form>
            </section>
        </main>
    </div>
</body>
</html>