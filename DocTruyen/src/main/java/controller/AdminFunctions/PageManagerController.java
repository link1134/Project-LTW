package controller.AdminFunctions;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.bean.Page;
import model.dao.PageDAO;

/**
 * Servlet implementation class PageManagerController
 */
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
	    maxFileSize = 1024 * 1024 * 10,      // 10MB cho mỗi file
	    maxRequestSize = 1024 * 1024 * 50    // 50MB cho tổng toàn bộ form
	)
@WebServlet("/admin/page-manager")
public class PageManagerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PageDAO pageDAO = new PageDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PageManagerController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int chapterId = Integer.parseInt(request.getParameter("id"));
		String displayNum = request.getParameter("displayNum");
		List<Page> pages = pageDAO.getPagesByChapterId(chapterId);
		request.setAttribute("pages", pages);
		request.setAttribute("displayNum", displayNum);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/admin/admin_page_manager.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		if ("addPage".equals(action)) {
			addPage(request, response);
		} else {
			doGet(request, response);
		}
	}

	private void addPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int chapterId = Integer.parseInt(request.getParameter("chapterId"));
		int storyId = Integer.parseInt(request.getParameter("storyId"));
		String displayNum = request.getParameter("displayNum");

		String baseUploadPath = "D:/WorkSpace/JAVA/DocTruyen" + "/src/main/webapp/static/uploads/";

		String uploadPath = "static/uploads/" + storyId + "/" + displayNum + "/";
		String realPath = baseUploadPath + storyId + "/" + displayNum + "/";

		File uploadDir = new File(realPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		for (Part part : request.getParts()) {
			System.out.println("Processing part: " + part.getName() + " | File: " + part.getSubmittedFileName());
			if (!"images".equals(part.getName()) || part.getSize() == 0)
				continue;

			String fileName = part.getSubmittedFileName(); // vd: 1.jpg
			String baseName = fileName.substring(0, fileName.lastIndexOf("."));

			// ✅ Rule: tên file phải là số không âm
			int pageNumber;
			try {
				pageNumber = Integer.parseInt(baseName);
				if (pageNumber < 0)
					continue;
			} catch (NumberFormatException e) {
				System.out.println("Lỗi định dạng tên file: " + baseName); // Thêm dòng này
				continue; // bỏ file sai format
			}

			// Lưu file
			part.write(realPath + File.separator + fileName);

			// Lưu DB
			Page page = new Page();
			page.setChapterId(chapterId);
			page.setPageNumber(pageNumber);
			page.setPageURL(uploadPath + fileName);

			pageDAO.insertPage(page);
		}
		
		response.sendRedirect(request.getContextPath() + "/admin/page-manager?id=" + chapterId + "&storyId=" + storyId
				+ "&displayNum=" + displayNum);
	}

}
