package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller xử lý tất cả các yêu cầu trong khu vực Admin.
 * Ánh xạ tới: /admin/*
 */
@WebServlet("/admin/*")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    
   
	private static final String ADMIN_VIEW_PREFIX = "/WEB-INF/view/admin/";   
    
    public AdminController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
		
        // 1. Lấy Path Info (Phần sau /admin)
		String pathInfo = request.getPathInfo();
        
        // Xác định action, mặc định là dashboard (cho admin.jsp)
		String action = (pathInfo != null && pathInfo.length() > 1) ? pathInfo.substring(1): "dashboard";
		String targetView = "";
        
        // 2. Xử lý Action và xác định View cần chuyển tiếp
		switch (action) {
        case "dashboard":
            request.setAttribute("adminTitle", "Trang Chủ Admin (Dashboard)");
            // Map tới admin.jsp
            targetView = ADMIN_VIEW_PREFIX + "admin.jsp";
            break;
            
//        case "story-list":
//            request.setAttribute("adminTitle", "Danh Sách Truyện");
//            // Map tới admin_story_list.jsp
//            targetView = ADMIN_VIEW_PREFIX + "admin_story_list.jsp";
//            break;
            
      
            
       
            
        
            
//        default:
//            // Nếu không khớp, trả về lỗi 404
//            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Chức năng Admin không tồn tại.");
//            return;
        }
        
        
		RequestDispatcher dispatcher = request.getRequestDispatcher(targetView);
        dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
		// Logic xử lý Form POST (ví dụ: Đăng Truyện Mới, Thêm Thể loại) 
	}
}