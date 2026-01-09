package controller.UserFunctions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.UserDAO;

@WebServlet("/reset-password")
public class ResetPasswordController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/forgot_password/reset_password.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String newPass = request.getParameter("newPassword");
        String confirmPass = request.getParameter("confirmPassword");
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        if (newPass.equals(confirmPass)) {
            // Gọi DAO để cập nhật
            UserDAO dao = new UserDAO();
            boolean success = dao.updatePassword(email, newPass);

            if (success) {
                session.invalidate(); // Xóa session sau khi đổi xong
                response.sendRedirect(request.getContextPath() + "/login?message=success");
            } else {
                request.setAttribute("error", "Lỗi hệ thống, vui lòng thử lại!");
                request.getRequestDispatcher("/WEB-INF/view/forgot_password/reset_password.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Mật khẩu xác nhận không khớp!");
            request.getRequestDispatcher("/WEB-INF/view/forgot_password/reset_password.jsp").forward(request, response);
        }
    }
}
