package controller.UserFunctions;

import java.io.IOException;
import org.mindrot.jbcrypt.BCrypt;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.User;
import model.dao.UserDAO;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private UserDAO userDAO = new UserDAO();
	public RegisterController() {
		super();
		// TODO Auto-generated constructor stub
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher =
		        request.getRequestDispatcher("/WEB-INF/view/login_register/register.jsp");
		    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    request.setCharacterEncoding("UTF-8");

	    String name = request.getParameter("name");
	    String password = request.getParameter("password");
	    String email = request.getParameter("email");

	    // BCrypt hash password
	    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));

	    User newUser = new User();
	    newUser.setUserName(name);
	    newUser.setPassword(hashedPassword);
	    newUser.setEmail(email);
	    newUser.setRole("USER");

	    boolean isSuccess = false;
	    try {
	        isSuccess = userDAO.registerUser(newUser);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    if (isSuccess) {
	        response.sendRedirect(request.getContextPath() + "/login");
	    } else {
	        request.setAttribute("errorMessage", "Đăng ký thất bại! Email có thể đã tồn tại.");
	        request.getRequestDispatcher("/WEB-INF/view/login_register/register.jsp")
	               .forward(request, response);
	    }
	}


}
