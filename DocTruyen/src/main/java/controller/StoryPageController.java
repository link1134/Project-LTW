package controller;

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
import model.dao.ChapterDAO;
import model.dao.GenreDAO;
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

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		storyDAO= new StoryDAO();
		chapterDAO= new ChapterDAO();
		genresDAO= new GenreDAO();
		//lấy id của homePage gửi sang
		String idParam= request.getParameter("id");
		if(idParam==null) {
			response.sendRedirect("home");
			return;
		}
		int storyID= Integer.parseInt(idParam);
		
		// 1. Lấy thông tin truyện
        Stories story = storyDAO.getStoryById(storyID);
        List<Chapter> chapters= chapterDAO.getChaptersByStoryId(storyID);
        List<Genres> storyGenres = storyDAO.getGenresByStoryId(storyID);
        List<Stories> similarStories= storyDAO.getSimilarStories(storyID);
        // 2. tạo thuộc tính vào request
        request.setAttribute("story", story);
        request.setAttribute("chapters", chapters);
        request.setAttribute("storyGenres", storyGenres);
        request.setAttribute("similarStories", similarStories);
        
        // 3. dẫn đến storydetail
//        D:\WorkSpace\JAVA\DocTruyen\src\main\webapp\WEB-INF\view\story_page\story.jsp
        request.getRequestDispatcher("WEB-INF\\view\\story_page\\story.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
