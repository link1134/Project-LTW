package controller.Navigation;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import model.bean.Chapter;
import model.bean.Comment;
import model.bean.Page;
import model.bean.Stories;
import model.bean.User;
import model.dao.ChapterDAO;
import model.dao.CommentDAO;
import model.dao.HistoryDAO;
import model.dao.PageDAO;
import model.dao.StoryDAO;

/**
 * Servlet implementation class ChapterDetailPageController
 */
@WebServlet("/read-chapter")
public class ChapterDetailPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChapterDAO chapterDAO;
	private PageDAO pageDAO;
	private StoryDAO storyDAO;
	private HistoryDAO historyDAO;
	private CommentDAO commentDAO = new CommentDAO();
	public ChapterDetailPageController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		chapterDAO = new ChapterDAO();
		pageDAO = new PageDAO();
		storyDAO = new StoryDAO();
		historyDAO = new HistoryDAO();

		// 1. Lấy ID chương từ URL
		String idStr = request.getParameter("id");
		if (idStr == null || idStr.isEmpty()) {
			response.sendRedirect("home-page");
			return;
		}

		try {
			int chapterId = Integer.parseInt(idStr);

			// 2. Lấy thông tin chương hiện tại (Bạn cần thêm hàm getChapterById vào
			// ChapterDAO nhé)
			Chapter currentChapter = chapterDAO.getChapterById(chapterId);
			if (currentChapter == null) {
				response.sendRedirect(request.getContextPath() + "/home-page");
				return;
			}
			User user = (User) request.getSession().getAttribute("user");
			// 3. Lấy thông tin truyện để hiện tên truyện trên đầu trang
			Stories story = storyDAO.getStoryById(currentChapter.getStoryID());

			// 4. Lấy danh sách ảnh của chương này
			List<Page> pages = pageDAO.getPagesByChapterId(chapterId);

			// 5. Lấy danh sách tất cả chương của truyện này (Dùng cho cái Overlay chuyển
			// chương)
			List<Chapter> allChapters = chapterDAO.getChaptersByStoryId(story.getId());

			// 6. Xử lý logic chương trước/sau (Dành cho 2 nút điều hướng)
			Chapter prevChapter = null;
			Chapter nextChapter = null;
			List<Comment> comments = commentDAO.getCommentsByChapterId(currentChapter.getId());
			request.setAttribute("comments", comments);
			for (int i = 0; i < allChapters.size(); i++) {
				if (allChapters.get(i).getId() == chapterId) {
					if (i > 0)
						prevChapter = allChapters.get(i - 1); // Chương trước
					if (i < allChapters.size() - 1)
						nextChapter = allChapters.get(i + 1); // Chương sau
					break;
				}
			}
			boolean haveTitle = true;
			if (currentChapter.getTitle().equalsIgnoreCase("KHÔNG CÓ TIÊU ĐỀ")) {
				haveTitle = false;
			}
			boolean nextChapterHaveTitle = false;

			if (nextChapter != null) {
				String nextTitle = nextChapter.getTitle();
				if (nextTitle != null && !nextTitle.trim().isEmpty()
						&& !nextTitle.equalsIgnoreCase("KHÔNG CÓ TIÊU ĐỀ")) {
					nextChapterHaveTitle = true;
				}
			}
			request.setAttribute("currentUserId", user.getId());
			// 7. Đẩy toàn bộ dữ liệu sang JSP
			request.setAttribute("countComment", commentDAO.countComment(chapterId));
			request.setAttribute("story", story);
			request.setAttribute("currentChapter", currentChapter);
			request.setAttribute("pages", pages);
			request.setAttribute("allChapters", allChapters);
			request.setAttribute("prevChapter", prevChapter);
			request.setAttribute("nextChapter", nextChapter);
			request.setAttribute("haveTitle", haveTitle);
			request.setAttribute("nextChapterHaveTitle", nextChapterHaveTitle);
			storyDAO.increaseView(story.getId());
			historyDAO.saveHistory(user.getId(), currentChapter.getId(), story.getId());
			request.getRequestDispatcher("/WEB-INF/view/chapter_detail_page/chapter_detail.jsp").forward(request,
					response);

		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/home-page");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
