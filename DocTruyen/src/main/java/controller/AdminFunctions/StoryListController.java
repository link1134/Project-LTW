package controller.AdminFunctions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Stories;
import model.dao.StoryDAO;


@WebServlet("/admin/story-list")
public class StoryListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private StoryDAO storyDAO= new StoryDAO();  
    private static final String ADMIN_VIEW_PREFIX = "/WEB-INF/view/admin/";
    
    
    public StoryListController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//lấy danh sách truyện trong DB
		List<Stories> listStroies= storyDAO.getAllStories();
		request.setAttribute("activePage", "story-list");

		request.setAttribute("storyList", listStroies);
		request.getRequestDispatcher(ADMIN_VIEW_PREFIX+"admin_story_list.jsp").forward(request, response);;
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
