package controller.UserFunctions;

import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Service.EmailService;

/**
 * Servlet implementation class ForgotPasswordController
 */
@WebServlet("/forgot-password")
public class ForgotPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ForgotPasswordController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/forgot_password/forgot_password.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		
		
		// 1. Tạo mã OTP 6 số
        Random rand = new Random();
        int otpValue = 100000 + rand.nextInt(900000);
        String otp = String.valueOf(otpValue);
        
        // 2. Lưu OTP vào Session
        HttpSession session = request.getSession();
        session.setAttribute("otp", otp);
        session.setAttribute("email", email);
        
        // Đặt thời gian hết hạn cho OTP (ví dụ: 5 phút = 300 giây)
        session.setMaxInactiveInterval(300);
        
        // 3. Gửi Email
        EmailService emailService = new EmailService();
        boolean isSent = emailService.sendOTP(email, otp);
        
        if (isSent) {
            // Chuyển hướng sang trang nhập mã OTP
            response.sendRedirect(request.getContextPath() + "/verify-otp");
        } else {
            request.setAttribute("error", "Không thể gửi email. Vui lòng thử lại!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/forgot_password/forgot_password.jsp");
    		dispatcher.forward(request, response);
        }
    }
	}


