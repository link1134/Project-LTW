package controller;

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
		request.getRequestDispatcher(ADMIN_VIEW_PREFIX + "admin_new_story.jsp").forward(request, response);
	}

	// Xử lý dữ liệu form gửi lên
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//thiết lập cho tiếng việt
		request.setCharacterEncoding("UTF-8");
		
		try {
			//1. lấy thông tin dạng text
			String title = request.getParameter("Title");
            String author = request.getParameter("Author");
            String description = request.getParameter("Description");
            String[] genreIds = request.getParameterValues("genreID");
			
            
            
			//2. Thiết lập đường dẫn
	            /*2.1 xử lý tên file để lưu
				/* Lấy title để đặt tên file (nên loại bỏ dấu và khoảng trắng)*/
			String rawTitle = request.getParameter("Title");
			String safeTitle = rawTitle.replaceAll("[^a-zA-Z0-9]", "_"); // Đổi ký tự đặc biệt thành dấu gạch dưới
			
			
				//2.2 tạo đường dẫn
			String baseUploadPath="D:\\WorkSpace\\JAVA\\DocTruyen\\src\\main\\webapp\\static\\uploads";//lưu local trên máy
			//String baseUploadPath= getServletContext().getRealPath("")+File.separator+"static" + File.separator + "uploads";//lưu vào server tomcat nhưng khi clean server mất hết
			String storyPath = baseUploadPath + File.separator + safeTitle;
			File storyDIR= new File(storyPath);
			if(!storyDIR.exists())storyDIR.mkdir();
            if (!storyDIR.exists()) storyDIR.mkdirs();
			
            
            
			//3. xử lý file ảnh
				
				//3.1lưu thumbnail
			Part thumbPart= request.getPart("ThumnailImageURL");
			String thumbName= "thumb_"+System.currentTimeMillis()+"_"+safeTitle+".jpg";
			thumbPart.write(storyPath+File.separator+thumbName);
			
				//3.2 Lưu Cover
			Part coverPart= request.getPart("CoverImageURL");
			String coverName="cover_"+System.currentTimeMillis()+"_"+safeTitle+".jpg";
			coverPart.write(storyPath+File.separator+coverName);
			
			
            
            
            //4. Tạo Object Stories
            Stories stories = new Stories();
            stories.setTitle(title);
            stories.setAuthor(author);
            stories.setDescription(description);
            stories.setBigCoverImageURL("static/uploads/"+safeTitle+"/"+thumbName);
            stories.setCoverImageURL("static/uploads/"+safeTitle+"/"+coverName);
            System.out.println(stories.toString());
            
            
            
            //5. gọi DAO để lưu vào DB
            boolean isSucces= storyDAO.insertStoryFull(stories, genreIds);
            if(isSucces) {
            	request.getSession().setAttribute("message", "Tạo truyện mới thành công!");
            	response.sendRedirect(request.getContextPath() + "/admin/story-list");
            }
            else {
            	request.setAttribute("error", "Lỗi lưu Database!");
                doGet(request, response);
            }
            
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Lỗi xử lý file: " + e.getMessage());
            doGet(request, response);
		}
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}


