package controller.UserFunctions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Chapter;
import model.bean.Stories;
import model.bean.TimeAgoUtils;
import model.dao.ChapterDAO;
import model.dao.GenreDAO;

/**
 * Servlet implementation class TagSearchController
 */
@WebServlet("/genre")
public class GenreSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GenreDAO genreDAO = new GenreDAO();
	private ChapterDAO chapterDAO = new ChapterDAO();

	public GenreSearchController() {
		super();
		// TODO Auto-generated constructor stub

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String genreIdRaw = request.getParameter("genreId");
		if (genreIdRaw == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		int genreId;
		try {
			genreId = Integer.parseInt(genreIdRaw);
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		
		String genreName = genreDAO.getGenreNameById(genreId);

		
		List<Stories> stories = genreDAO.getStoriesByGenre(genreId);

	
		Map<Integer, String> latestChapterInfo = new HashMap<>();

		for (Stories s : stories) {

		
			Chapter latestChapter = chapterDAO.getLatestPublishedChapter(s.getId());

		
			int maxChapterNumber = chapterDAO.getMaxChapterNumber(s.getId());

			if (maxChapterNumber > 0 && latestChapter != null && latestChapter.getPublishedAt() != null) {

				String info = "C. " + maxChapterNumber + " - " + s.getTimeAgo();

				latestChapterInfo.put(s.getId(), info);
			} else {
				latestChapterInfo.put(s.getId(), "Chưa có chương");
			}
		}

		
		request.setAttribute("title", genreName);
		request.setAttribute("subTitle", genreName);
		request.setAttribute("stories", stories);
		request.setAttribute("latestChapterInfo", latestChapterInfo);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/utility/record_and_tag.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
