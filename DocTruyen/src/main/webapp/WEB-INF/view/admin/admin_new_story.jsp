<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon"href="https://valvrareteam.net/images/Khong_Co_Tieu_e431_20250703112444.png"type="image/png">
    <title>Admin Dashboard | Đăng Truyện Mới</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/admin/admin.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
</head>
<body>
    <div class="dashboard-container">
        
        <jsp:include page="/WEB-INF/view/utility/admin_sidebar.jsp" />

        <main class="content-area">
            <header class="content-header">
                <h2>Đăng Truyện Mới</h2>
                <p>Thêm thông tin và tệp bìa cho truyện mới.</p>
            </header>
            
            <section id="new-story" class="content-panel hidden">
                <h3>Đăng Truyện Mới</h3>
                <form class="admin-form" enctype="multipart/form-data" action="${pageContext.request.contextPath}/admin/new-story"method="POST" >
                    
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
                            <c:forEach var="g" items="${genreList}">
                            	<label style="margin-right: 15px;">
                            		<input type="checkbox"name="genreID" value="${g.id}"> 	${g.name}
                            	</label>
                            </c:forEach>
                        </div>
                        <small class="help-text">Truyện được phân loại theo một thể loại chính.</small>
                    </div>

                    <div class="form-group">
                        <label for="description">Mô tả Truyện:</label>
                        <textarea id="description" name="Description" rows="5" required></textarea>
                    </div>
                    
                    <!-- <div class="form-group">
                        <label for="status">Trạng thái:</label>
                        <select id="status" name="Status">
                            <option value="in-progress">Đang tiến hành</option>
                            <option value="completed">Hoàn thành</option>
                            <option value="paused">Tạm ngưng</option>
                        </select>
                    </div> -->
                    
                    <button type="submit" class="btn btn-primary">Đăng Truyện</button>
                </form>
            </section>
        </main>
    </div>
</body>
</html>