package controller.Navigation;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Chapter;
import model.bean.Genres;
import model.bean.Stories;
import model.bean.User;
import model.dao.ChapterDAO;
import model.dao.GenreDAO;
import model.dao.ReadingListDAO;
import model.dao.StoryDAO;

@WebServlet("/story-detail")
public class StoryPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StoryDAO storyDAO;
	private ChapterDAO chapterDAO;
	private GenreDAO genresDAO;

	public StoryPageController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		storyDAO = new StoryDAO();
		chapterDAO = new ChapterDAO();
		genresDAO = new GenreDAO();

		String idParam = request.getParameter("id");
		if (idParam == null) {
			response.sendRedirect(request.getContextPath() + "/home-page");
			return;
		}
		int storyID = Integer.parseInt(idParam);

		Stories story = storyDAO.getStoryById(storyID);
		List<Chapter> chapters = chapterDAO.getChaptersByStoryId(storyID);
		List<Genres> storyGenres = storyDAO.getGenresByStoryId(storyID);
		List<Stories> similarStories = storyDAO.getSimilarStories(storyID);
		ReadingListDAO rlDAO = new ReadingListDAO();
		User user = (User) request.getSession().getAttribute("user");
		boolean isFollowed = false;
		if (user != null) {
			isFollowed = rlDAO.isFollowed(user.getId(), storyID);
		}

		request.setAttribute("isFollowed", isFollowed);
		request.setAttribute("story", story);
		request.setAttribute("chapters", chapters);
		request.setAttribute("storyGenres", storyGenres);
		request.setAttribute("similarStories", similarStories);

		request.getRequestDispatcher("WEB-INF\\view\\story_page\\story.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
