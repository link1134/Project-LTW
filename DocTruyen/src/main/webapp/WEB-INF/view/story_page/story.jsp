<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vn">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/story_page/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/reset.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    <title>Tittle</title>
</head>
<body>
    <header></header>
    <main>
        <div class="content">
            <div class="header_content">
                <div class="Cover_Image">
                    <img src="${pageContext.request.contextPath }/IMAGE/cover_img.png" alt="ảnh bìa truyện">
                </div>
                
                
                
            </div>
            <div class="body_content_1">
                <div class="main_info">
                        <img class="story_thumbnail" src="${pageContext.request.contextPath }/IMAGE/thumnail.png" alt="Ảnh đại diện">
                        <div class="text_">
                            <h1 class="story_name">DORAEMON</h1>
                            <h1 class="author_name">Fujiko Fujio</h1>
                            <h1 class="publish_time">6 giờ trước</h1>
                        </div>
                        
                    </div>
                
                <div class="list_button">
                    <nav class="category_nav">
                        <a href="">Thiếu nhi</a>
                        <a href="">Vui tươi</a>
                        
                    </nav>
                </div>
                <div class="action_buttons">
                    <div class="follow_button">
                        <a >Theo dõi </a>
                    </div>
                    <div class="read_from_chapter1_button">
                        <a >Đọc từ chương 1</a>
                    </div>
                </div>
                

            </div>
            <div class="body_content_2">
                <div class="left_column">
                    <div class="description">
                        <p>Tập hợp các truyện ngắn không được xuất bản trong 45 tập gốc của Doraemon.</p>
                        
                    </div>
                </div>
                <div class="right_column_stats">
                    <div class="statistics">
                        <div class="chapter_number">6 chương</div>
                        <div class="view">2000 lượt xem</div>
                    </div>
                </div>
            </div>
                
            <div class="body_content_3">
                <h1>Danh sách chương</h1>
                <div class="list_chapter">
                    <ul class="list_chapter">
                        <li class="chapter_items">
                            <a href="/HTML/chapter_detail.html" class="chapter_link">
                                <h4>Chương 1</h4>
                                <h5>Hành tinh GARAPA</h5>
                                <H6>6 giờ trước</H6>
                                <H6>lượt xem</H6>
                                <H6>bình luận</H6>
                            </a>
                        </li>
                        <li class="chapter_items">
                            <a href="/HTML/chapter_detail.html" class="chapter_link">
                                <h4>Chương 2</h4>
                                <h5>Hành tinh GARAPA</h5>
                                <H6>6 giờ trước</H6>
                                <H6>lượt xem</H6>
                                <H6>bình luận</H6>
                            </a>
                        </li>
                        <li class="chapter_items">
                            <a href="/HTML/chapter_detail.html" class="chapter_link">
                                <h4>Chương 3</h4>
                                <h5>Hành tinh GARAPA</h5>
                                <H6>6 giờ trước</H6>
                                <H6>lượt xem</H6>
                                <H6>bình luận</H6>
                            </a>
                        </li>
                        <li class="chapter_items">
                            <a href="/HTML/chapter_detail.html" class="chapter_link">
                                <h4>Chương 4</h4>
                                <h5>Hành tinh GARAPA</h5>
                                <H6>6 giờ trước</H6>
                                <H6>lượt xem</H6>
                                <H6>bình luận</H6>
                            </a>
                        </li>
                        <li class="chapter_items">
                            <a href="/HTML/chapter_detail.html" class="chapter_link">
                                <h4>Chương 5</h4>
                                <h5>Hành tinh GARAPA</h5>
                                <H6>6 giờ trước</H6>
                                <H6>lượt xem</H6>
                                <H6>bình luận</H6>
                            </a>
                        </li>
                        <li class="chapter_items">
                            <a href="/HTML/chapter_detail.html" class="chapter_link">
                                <h4>Chương 6</h4>
                                <h5>Hành tinh GARAPA</h5>
                                <H6>6 giờ trước</H6>
                                <H6>lượt xem</H6>
                                <H6>bình luận</H6>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="footer_content">
                <div class="similar_story">
                    <h1>Truyện tương tự</h1>
                    <ul class="list_stroy">
                        <li class="stroy_items">
                            <a href="/HTML/stroy.html" class="story_link">
                                <img src="${pageContext.request.contextPath }/IMAGE/thumnail.png" alt="ảnh truyện">
                                <h4>DORAEMON</h4>
                                <h5>số chương</h5>
                                <h5>6 giờ trước</h5>
                            </a>
                        </li>
                        <li class="stroy_items">
                            <a href="/HTML/stroy.html" class="story_link">
                                <img src="${pageContext.request.contextPath }/IMAGE/thumnail.png" alt="ảnh truyện">
                                <h4>DORAEMON</h4>
                                <h5>số chương</h5>
                                <h5>6 giờ trước</h5>
                            </a>
                        </li>
                        <li class="stroy_items">
                            <a href="/HTML/stroy.html" class="story_link">
                                <img src="${pageContext.request.contextPath }/IMAGE/thumnail.png" alt="ảnh truyện">
                                <h4>DORAEMON</h4>
                                <h5>6 chương</h5>
                                <h5>6 giờ trước</h5>
                            </a>
                        </li>
                        <li class="stroy_items">
                            <a href="/HTML/stroy.html" class="story_link">
                                <img src="${pageContext.request.contextPath }/IMAGE/thumnail.png" alt="ảnh truyện">
                                <h4>DORAEMON</h4>
                                <h5>6 chương</h5>
                                <h5>6 giờ trước</h5>
                            </a>
                        </li>
                        <li class="stroy_items">
                            <a href="/HTML/stroy.html" class="story_link">
                                <img src="${pageContext.request.contextPath }/IMAGE/thumnail.png" alt="ảnh truyện">
                                <h4>DORAEMON</h4>
                                <h5>số chương</h5>
                                <h5>6 giờ trước</h5>
                            </a>
                        </li>
                        <li class="stroy_items">
                            <a href="/HTML/stroy.html" class="story_link">
                                <img src="${pageContext.request.contextPath }/IMAGE/thumnail.png" alt="ảnh truyện">
                                <h4>DORAEMON</h4>
                                <h5>số chương</h5>
                                <h5>6 giờ trước</h5>
                            </a>
                        </li>
                        <li class="stroy_items">
                            <a href="/HTML/stroy.html" class="story_link">
                                <img src="${pageContext.request.contextPath }/IMAGE/thumnail.png" alt="ảnh truyện">
                                <h4>DORAEMON</h4>
                                <h5>số chương</h5>
                                <h5>6 giờ trước</h5>
                            </a>
                        </li>
                        <li class="stroy_items">
                            <a href="/HTML/stroy.html" class="story_link">
                                <img src="${pageContext.request.contextPath }/IMAGE/thumnail.png" alt="ảnh truyện">
                                <h4>DORAEMON</h4>
                                <h5>số chương</h5>
                                <h5>6 giờ trước</h5>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>


        </div>


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