package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Genres;
import model.dao.GenreDAO;

@WebServlet("/admin/genre-management")
public class GenreController extends HttpServlet {
	private GenreDAO genreDAO = new GenreDAO();
	private static final String ADMIN_VIEW_PREFIX = "/WEB-INF/view/admin/";

	public GenreController() {
		super();

	}

	// doGet: Dùng để hiển thị danh sách thể loại lên bảng
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if ("delete".equals(action)) {
		    try {
		        int id = Integer.parseInt(request.getParameter("id"));
		        boolean success = genreDAO.deleteGenres(id);
		        if (success) {
		            request.getSession().setAttribute("message", "Xóa thành công!");
		        } else {
		            request.getSession().setAttribute("error", "Không thể xóa thể loại này (có thể đang có truyện thuộc thể loại này)!");
		        }
		    } catch (NumberFormatException e) {
		        request.getSession().setAttribute("error", "ID không hợp lệ!");
		    }
		    response.sendRedirect(request.getContextPath() + "/admin/genre-management");
		    return;
		}
		// Lấy danh sách từ DB và gửi sang JSP
		
		List<Genres> list = genreDAO.getAllGenres();
		request.setAttribute("genreList", list);
		request.getRequestDispatcher(ADMIN_VIEW_PREFIX + "admin_genre_management.jsp").forward(request, response);
		;
	}

	// doPost: Dùng để xử lý khi người dùng nhấn nút "Thêm mới","Sửa","Xóa"
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Thiết lập tiếng Việt cho request
		request.setCharacterEncoding("UTF-8");

		
		
		String genreName = request.getParameter("genreName");
		if (genreName != null && !genreName.trim().isEmpty()) {
			genreName = genreName.trim();

			boolean isAdded = genreDAO.addGenres(genreName);
			if (isAdded) {
				request.getSession().setAttribute("message", "Thêm thể loại thành công!");
			} else {
				request.getSession().setAttribute("error", "Thể loại đã tồn tại hoặc có lỗi xảy ra!");
			}
		}
		response.sendRedirect(request.getContextPath() + "/admin/genre-management");
	}

}
