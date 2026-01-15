package controller.AdminFunctions;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.bean.Genres;
import model.bean.Stories;
import model.dao.GenreDAO;
import model.dao.StoryDAO;

@WebServlet("/admin/new-story")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class NewStoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StoryDAO storyDAO = new StoryDAO();
	private GenreDAO genreDAO = new GenreDAO();
	private static final String ADMIN_VIEW_PREFIX = "/WEB-INF/view/admin/";
	public NewStoryController() {
		super();

	}

	// Hiển thị form đăng truyện
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// load dữ liệu thể loại truyện lên
		List<Genres> listG = genreDAO.getAllGenres();
		request.setAttribute("genreList", listG);
		request.setAttribute("isEdit", false);
		request.setAttribute("activePage", "new-story");
		request.getRequestDispatcher(ADMIN_VIEW_PREFIX + "admin_new_story.jsp").forward(request, response);
	}

	// Xử lý dữ liệu form gửi lên
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    try {
	        // 1. Lấy thông tin text
	        String title = request.getParameter("Title");
	        String author = request.getParameter("Author");
	        String description = request.getParameter("Description");
	        String[] genreIds = request.getParameterValues("genreID");

	        // 2. Tạo đối tượng Stories (Lúc này đường dẫn ảnh tạm để trống)
	        Stories stories = new Stories();
	        stories.setTitle(title);
	        stories.setAuthor(author);
	        stories.setDescription(description);
	        stories.setCoverImageURL(""); 
	        stories.setBigCoverImageURL("");
	        
	        // 3. Gọi DAO để lưu truyện trước lấy ID
	        int storyId = storyDAO.insertStoryFull(stories, genreIds);

	        if (storyId != -1) {
	            // 4. Thiết lập đường dẫn theo ID vừa lấy được
	        	//D:\\WorkSpace\\JAVA\\DocTruyen\\src\\main\\webapp\\static\\uploads
	        	//C:\\Users\\THANH HIEN\\Desktop\\Web\\DocTruyen\\src\\main\\webapp\\static\\uploads
	            String baseUploadPath = "D:\\WorkSpace\\JAVA\\DocTruyen\\src\\main\\webapp\\static\\uploads";//tự thay đường dẫn
	          //String baseUploadPath= getServletContext().getRealPath("")+File.separator+"static" + File.separator + "uploads";//lưu vào server tomcat nhưng khi clean server mất hết
	            String storyPath = baseUploadPath + File.separator + storyId; // Folder là ID
	            
	            File storyDIR = new File(storyPath);
	            if (!storyDIR.exists()) storyDIR.mkdirs();

	            // 5. Xử lý lưu file
	            Part thumbPart = request.getPart("ThumnailImageURL");
	            String thumbName = "thumb_" + System.currentTimeMillis() + ".jpg";
	            thumbPart.write(storyPath + File.separator + thumbName);

	            Part coverPart = request.getPart("CoverImageURL");
	            String coverName = "cover_" + System.currentTimeMillis() + ".jpg";
	            coverPart.write(storyPath + File.separator + coverName);

	            // 6. Cập nhật lại đường dẫn ảnh vào Database
	            String thumbURL = "static/uploads/" + storyId + "/" + thumbName;
	            String coverURL = "static/uploads/" + storyId + "/" + coverName;
	            storyDAO.updateImagePaths(storyId, thumbURL, coverURL);

	            request.getSession().setAttribute("message", "Tạo truyện mới thành công với ID: " + storyId);
	            response.sendRedirect(request.getContextPath() + "/admin/story-list");
	        } else {
	            request.setAttribute("error", "Lỗi lưu Database!");
	            doGet(request, response);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("error", "Lỗi hệ thống: " + e.getMessage());
	        doGet(request, response);
	    }
	}
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	


