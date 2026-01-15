package controller.UserFunctions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Chapter;
import model.bean.Stories;
import model.dao.ChapterDAO;
import model.dao.StoryDAO;

/**
 * Servlet implementation class QuickSearchController
 */
@WebServlet("/quick-search")
public class QuickSearchController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private StoryDAO storyDAO = new StoryDAO();
    private ChapterDAO chapterDAO = new ChapterDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        String keyword = request.getParameter("keyword");

        if (keyword == null || keyword.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }
        
        keyword = keyword.trim();

        
        List<Stories> stories = storyDAO.searchStoriesByTitle(keyword);

       
        Map<Integer, String> latestChapterInfo = new HashMap<>();

        for (Stories s : stories) {
            Chapter latest = chapterDAO.getLatestPublishedChapter(s.getId());
            int maxChap = chapterDAO.getMaxChapterNumber(s.getId());

            if (latest != null && latest.getPublishedAt() != null && maxChap > 0) {
                latestChapterInfo.put(
                    s.getId(),
                    "C. " + maxChap + " - " + s.getTimeAgo()
                );
            } else {
                latestChapterInfo.put(s.getId(), "Chưa có chương");
            }
        }

      
        request.setAttribute("title", "Kết quả tìm kiếm");
        request.setAttribute("subTitle", "Kết quả cho: \"" + keyword + "\"");
        request.setAttribute("stories", stories);
        request.setAttribute("latestChapterInfo", latestChapterInfo);

       
        request.getRequestDispatcher("/WEB-INF/view/utility/record_and_tag.jsp")
               .forward(request, response);
    }
}

