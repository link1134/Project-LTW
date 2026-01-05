package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Chapter;
import model.dao.ChapterDAO;
import model.dao.PageDAO;

/**
 * Servlet implementation class ChapterManagerController
 */
@WebServlet("/admin/chapter-manager")
public class ChapterManagerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChapterDAO chapterDAO = new ChapterDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChapterManagerController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String storyTitle = request.getParameter("storyTitle");
		int storyId = Integer.parseInt(request.getParameter("storyId"));
		List<Chapter> chapters = chapterDAO.getChaptersByStoryId(storyId);
		request.setAttribute("chapters", chapters);
		request.setAttribute("storyTitle", storyTitle);
		request.setAttribute("storyId", storyId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/admin/admin_chapter_manager.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if ("updateStatus".equals(action)) {
			updateChapterStatus(request, response);
		} else if ("addChapter".equals(action)) {
			addChapter(request, response);
		} else if ("editChapter".equals(action)) {
			editChapter(request, response);
		}

	}

	private void editChapter(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		int chapterId = Integer.parseInt(request.getParameter("chapterId"));
		int storyId = Integer.parseInt(request.getParameter("storyId"));

		String newDisplay = request.getParameter("displayNumChapter");
		String oldDisplay = request.getParameter("oldDisplay");
		String title = request.getParameter("title");

		// Nếu đổi display → phải check trùng
		if (!oldDisplay.equals(newDisplay)) {

			if (chapterDAO.existsDisplayNum(storyId, newDisplay, chapterId)) {

				request.setAttribute("error", "Số chapter hiển thị đã tồn tại!");

				request.setAttribute("chapters", chapterDAO.getChaptersByStoryId(storyId));

				request.setAttribute("storyTitle", request.getParameter("storyTitle"));

				request.getRequestDispatcher("/WEB-INF/view/admin/admin_chapter_manager.jsp").forward(request,
						response);
				return;
			}
		}

		// UPDATE DB
		if (chapterDAO.updateChapterInfo(chapterId, newDisplay, title)) {

			if (!oldDisplay.equals(newDisplay)) {

				// 1. Rename folder
				renameChapterFolder(storyId, oldDisplay, newDisplay);

				// 2. Update page_url
				PageDAO pageDAO = new PageDAO();
				pageDAO.updatePageUrlByChapter(chapterId, oldDisplay, newDisplay, storyId);
			}
		}

		response.sendRedirect(request.getContextPath() + "/admin/chapter-manager?storyId=" + storyId);

	}

	private void renameChapterFolder(int storyId, String oldDisplay, String newDisplay) {

		String basePath = "C:/Users/THANH HIEN/Desktop/Web/DocTruyen/" + "src/main/webapp/static/uploads/";

		File oldDir = new File(basePath + storyId + "/" + oldDisplay);
		File newDir = new File(basePath + storyId + "/" + newDisplay);

		if (oldDir.exists() && !newDir.exists()) {
			oldDir.renameTo(newDir);
		} else {
			System.err.println("Rename failed: " + newDir.getAbsolutePath());
		}

	}

	private void addChapter(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		int storyId = Integer.parseInt(request.getParameter("storyId"));
		String storyTitle = request.getParameter("storyTitle");
		String displayNum = request.getParameter("displayNumChapter");
		String title = request.getParameter("title");

		int nextChapterNumber = chapterDAO.highestChapterNumber(storyId) + 1;

		Chapter c = new Chapter();
		c.setStoryID(storyId);
		c.setDisplayNumChapter(displayNum);
		c.setChapterNumber(nextChapterNumber);
		c.setTitle(title);

		// CHECK TRÙNG
		if (chapterDAO.existsDisplayNum(storyId, displayNum, null)) {

			request.setAttribute("error", "Số chapter hiển thị đã tồn tại!");

			request.setAttribute("chapters", chapterDAO.getChaptersByStoryId(storyId));

			request.setAttribute("storyTitle", storyTitle);

			request.getRequestDispatcher("/WEB-INF/view/admin/admin_chapter_manager.jsp").forward(request, response);
			return;
		}

		// INSERT
		if (chapterDAO.insertChapter(c)) {
			createChapterFolder(storyId, displayNum);
		}

		response.sendRedirect(
				request.getContextPath() + "/admin/chapter-manager?storyId=" + storyId + "&storyTitle=" + storyTitle);

	}

	private void createChapterFolder(int storyId, String displayNumChapter) {
		// Chỗ này đổi tùy máy
		String basePath = "C:/Users/THANH HIEN/Desktop/Web/DocTruyen/" + "src/main/webapp/static/uploads/";

		File storyDir = new File(basePath + storyId);

		if (!storyDir.exists()) {
			System.err.println("Story folder not found: " + storyDir.getAbsolutePath());
			return;
		}

		File chapterDir = new File(storyDir, displayNumChapter);

		if (!chapterDir.exists()) {
			chapterDir.mkdirs();
		}
	}

	private void updateChapterStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		int chapterId = Integer.parseInt(request.getParameter("chapterId"));
		String status = request.getParameter("status");

		boolean success = chapterDAO.updateStatus(chapterId, status);

		response.setContentType("text/plain");
		response.getWriter().write(success ? "OK" : "FAIL");
	}

}
