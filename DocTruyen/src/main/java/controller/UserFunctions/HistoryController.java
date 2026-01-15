package controller.UserFunctions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Chapter;
import model.bean.ReadingHistory;
import model.bean.Stories;
import model.bean.TimeAgoUtils;
import model.bean.User;
import model.dao.ChapterDAO;
import model.dao.HistoryDAO;
import model.dao.StoryDAO;

@WebServlet("/history-page")
public class HistoryController extends HttpServlet {

	private HistoryDAO historyDAO;
	private StoryDAO storyDAO;
	private ChapterDAO chapterDAO;

	@Override
	public void init() throws ServletException {
		historyDAO = new HistoryDAO();
		storyDAO = new StoryDAO();
		chapterDAO = new ChapterDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		request.setAttribute("title", "History Page");
		request.setAttribute("subTitle", "Lịch sử đọc");

		
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		int userId = user.getId();

		
		List<ReadingHistory> histories = historyDAO.getHistoryByUserId(userId);

		
		Map<Integer, Stories> storyMap = new HashMap<>();
		Map<Integer, String> latestChapterInfo = new HashMap<>();

		for (ReadingHistory rh : histories) {

			int storyId = rh.getStoryID();

		
			if (storyMap.containsKey(storyId)) {
				continue;
			}

			Stories story = storyDAO.getStoryById(storyId);
			Chapter chapter = chapterDAO.getChapterById(rh.getChapterID());

			if (story == null || chapter == null)
				continue;

			storyMap.put(storyId, story);

			String info = "C. " + chapter.getDisplayNumChapter()
						+ " - " + TimeAgoUtils.format(rh.getReadAt());

			latestChapterInfo.put(storyId, info);
		}

		request.setAttribute("stories", new ArrayList<>(storyMap.values()));
		request.setAttribute("latestChapterInfo", latestChapterInfo);

		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/view/utility/record_and_tag.jsp");
		dispatcher.forward(request, response);
	}
}
