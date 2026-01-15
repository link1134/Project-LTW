<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/user_info/user_info.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    <link rel="icon"href="https://valvrareteam.net/images/Khong_Co_Tieu_e431_20250703112444.png"type="image/png">
    <title>Đọc truyện || Thông tin người dùng</title>
</head>
<body>
    <header></header>
    <main class="container">
        <nav class="sidebar">
            <h2 class="user-name">Chào, [Tên người dùng]!</h2>
            <ul class="nav-list">
                <li>
                    <a href="#" class="nav-item active" data-content="info">
                        Thông tin người dùng
                    </a>
                </li>
                <li>
                    <a href="#" class="nav-item" data-content="followed">
                        Truyện theo dõi
                    </a>
                </li>
                <li>
                    <a href="#" class="nav-item" data-content="history">
                        Lịch sử đọc
                    </a>
                </li>
                <li class="logout-item"> 
                    <a href="#" class="nav-item logout-link" style="color: red;"> 
                        Đăng xuất
                    </a>
                </li>
            </ul>
        </nav>

        <section class="content-area">
            <div id="info" class="content-panel active">
                <h2>Thông tin cá nhân</h2>
                <div class="user-details">
                    <p><strong>Tên tài khoản:</strong> user_example</p>
                    <p><strong>Email:</strong> user@example.com</p>
                    <p><strong>Ngày tham gia:</strong> 01/01/2024</p>
                    <button class="edit-btn">Chỉnh sửa thông tin</button>
                </div>
            </div>

            <div id="followed" class="content-panel hidden">
                <h2>Truyện đã theo dõi</h2>
                <ul class="story-list">
                    <li><a href="#">Doraemon Plus</a> - Chương mới nhất: 125</li>
                    <li><a href="#">Cô Gái Hậu Đậu</a> - Đã đọc đến: Chương 15</li>
                    </ul>
            </div>

            <div id="history" class="content-panel hidden">
                <h2>Lịch sử đọc truyện</h2>
                <ul class="story-list history-list">
                    <li><a href="#">Thám tử Conan</a> - Đọc lần cuối: 10/12/2025</li>
                    <li><a href="#">One Piece</a> - Đọc lần cuối: 08/12/2025</li>
                    </ul>
            </div>
        </section>
    </main>

    <footer class="footer">
        <div class="footer__container">
        <p class="footer__text footer__text--highlight">
            Contact for work, copyright and more:
        </p>

    <p class="footer__email">
      <a href="mailto:ad.doctruyen@gmail.com" class="footer__email-link">ad.doctruyen@gmail.com</a>
    </p>

    <div class="footer__links">
      <a href="#" class="footer__link">Điều khoản dịch vụ</a>
      <a href="#" class="footer__link">Chính sách bảo mật</a>
    </div>

    <span class="footer__copy">© 2025 - doctruyen.net</span>
  </div>
    </footer>
</body>
</html>
<script>
        document.addEventListener('DOMContentLoaded', () => {
            const navItems = document.querySelectorAll('.nav-item');
            const contentPanels = document.querySelectorAll('.content-panel');

            navItems.forEach(item => {
                item.addEventListener('click', function(e) {
                    e.preventDefault();

                    // 1. Xóa trạng thái active khỏi tất cả các nút nav
                    navItems.forEach(nav => nav.classList.remove('active'));
                    // 2. Thêm trạng thái active cho nút được click
                    this.classList.add('active');

                    // 3. Ẩn tất cả các panel nội dung
                    contentPanels.forEach(panel => panel.classList.add('hidden'));
                    
                    // 4. Lấy ID của panel cần hiển thị
                    const targetId = this.getAttribute('data-content');
                    const targetPanel = document.getElementById(targetId);
                    
                    // 5. Hiển thị panel mục tiêu
                    if (targetPanel) {
                        targetPanel.classList.remove('hidden');
                        targetPanel.classList.add('active');
                    }
                });
            });
        });
    </script>