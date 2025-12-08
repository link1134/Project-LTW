<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/admin/admin.css">
    <title>Admin | Quản lý Truyện</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    
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

        
            
            
    
</body>
</html>