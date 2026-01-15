package controller.UserFunctions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/verify-otp")
public class VerifyOTPController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Hiển thị giao diện nhập OTP
        request.getRequestDispatcher("/WEB-INF/view/forgot_password/verify_otp.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String userOTP = request.getParameter("otp");
        HttpSession session = request.getSession();
        String systemOTP = (String) session.getAttribute("otp");

        if (userOTP != null && userOTP.equals(systemOTP)) {
            // Nếu đúng, chuyển hướng sang trang đặt lại mật khẩu
            response.sendRedirect(request.getContextPath() + "/reset-password");
        } else {
            // Nếu sai, báo lỗi và ở lại trang verify
            request.setAttribute("error", "Mã OTP không chính xác hoặc đã hết hạn!");
            request.getRequestDispatcher("/WEB-INF/view/forgot_password/verify_otp.jsp").forward(request, response);
        }
    }
}
