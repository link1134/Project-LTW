<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard | Quản lý Thể loại</title>
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
                   <li><a href="admin.html" class="nav-item">Trang chủ</a></li>
                    
                    <li class="nav-group-title">Quản lý Nội dung</li>
                    <li><a href="admin_story_list.html" class="nav-item">Danh Sách Truyện</a></li>
                    <li><a href="admin_new_story.html" class="nav-item">Đăng Truyện Mới</a></li>
                    
                    
                    <li class="nav-group-title">Quản lý Dữ liệu</li>
                    <li><a href="admin_genre_management.html" class="nav-item active">Quản lý Thể loại</a></li>
                </ul>
            </nav>
            <div class="admin-logout">
                <a href="#" class="logout-btn">Đăng xuất</a>
            </div>
        </aside>

        <main class="content-area">
            <header class="content-header">
                <h2>Quản lý Thể loại</h2>
                <p>Thêm, sửa, xóa các thể loại truyện.</p>
            </header>
            
            <section id="genres" class="content-panel active">
                <h3>Quản lý Thể loại</h3>
                
                <div class="genre-add-section">
                    <h4>Thêm Thể loại mới</h4>
                    <form class="inline-form">
                        <input type="text" placeholder="Tên Thể loại (ví dụ: Hành động)">
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
                                <th>Slug</th>
                                <th>Số truyện</th>
                                <th>Thao tác</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>1</td>
                                <td>Hài hước</td>
                                <td>hai-huoc</td>
                                <td>150</td>
                                <td>
                                    <button class="btn btn-edit">Sửa</button>
                                    <button class="btn btn-delete">Xóa</button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </section>
        </main>
    </div>
</body>
</html>