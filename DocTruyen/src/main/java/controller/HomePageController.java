package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Stories;
import model.dao.StoryDAO;

@WebServlet("/home_page")
public class HomePageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StoryDAO storyDAO;

	public HomePageController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		storyDAO= new StoryDAO();
		// 1. Lấy danh sách truyện mới nhất từ DAO
		List<Stories> newestStories = storyDAO.getNewestStories();
		// 2. Đưa danh sách vào request attribute để JSP có thể truy cập
		request.setAttribute("newestStories", newestStories);
		// 3. Forward request sang trang home_page.jsp
		request.getRequestDispatcher("/WEB-INF/view/main_page/home_page.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
