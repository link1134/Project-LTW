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
import model.bean.User;
import model.dao.ChapterDAO;
import model.dao.ReadingListDAO;

/**
 * Servlet implementation class test_chapter_detail
 */
@WebServlet("/follow-story")
public class FollowController extends HttpServlet {
	private ReadingListDAO dao = new ReadingListDAO();
    private ChapterDAO chapterDAO = new ChapterDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        List<Stories> stories = dao.getFollowedStories(user.getId());
        Map<Integer, String> latestChapterInfo = new HashMap<>();
        for (Stories s : stories) {
            Chapter c = chapterDAO.getLatestPublishedChapter(s.getId());

            if (c != null && c.getPublishedAt() != null) {
                String info = "C. " + c.getDisplayNumChapter()
                        + " - " + TimeAgoUtils.format(c.getPublishedAt());
                latestChapterInfo.put(s.getId(), info);
            } else {
                latestChapterInfo.put(s.getId(), "Chưa có chương");
            }
        }

        
        request.setAttribute("title", "Follow Page");
        request.setAttribute("subTitle", "Đang theo dõi");
        request.setAttribute("stories", stories);
        request.setAttribute("latestChapterInfo", latestChapterInfo);

        RequestDispatcher rd =
            request.getRequestDispatcher("/WEB-INF/view/utility/record_and_tag.jsp");
        rd.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

      
        User user = (User) request.getSession().getAttribute("user");
        

        int userId = user.getId();
        int storyId = Integer.parseInt(request.getParameter("storyId"));

        ReadingListDAO dao = new ReadingListDAO();

       
        if (dao.isFollowed(userId, storyId)) {
            dao.unfollow(userId, storyId);
        } else {
            dao.follow(userId, storyId);
        }

       
        response.sendRedirect(request.getHeader("Referer"));
    }
}