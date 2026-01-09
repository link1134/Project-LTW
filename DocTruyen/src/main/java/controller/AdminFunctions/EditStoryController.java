package controller.AdminFunctions;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.bean.Stories;
import model.dao.GenreDAO;
import model.dao.StoryDAO;

@WebServlet("/admin/edit-story")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, 
                 maxFileSize = 1024 * 1024 * 10, 
                 maxRequestSize = 1024 * 1024 * 50)
public class EditStoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StoryDAO storyDAO = new StoryDAO();
    private GenreDAO genreDAO = new GenreDAO();
    private static final String ADMIN_VIEW_PREFIX = "/WEB-INF/view/admin/";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idParam = request.getParameter("id");
            if (idParam == null) {
                response.sendRedirect(request.getContextPath() + "/admin/story-list");
                return;
            }

            int id = Integer.parseInt(idParam);
            Stories story = storyDAO.getStoryById(id);
            
            if (story != null) {
                request.setAttribute("story", story);
                request.setAttribute("genreList", genreDAO.getAllGenres());
                request.setAttribute("selectedGenreIds", storyDAO.getGenreIdsByStoryId(id));
                
                // CHỈNH SỬA: Trỏ tới file JSP mới của bạn ở đây
                request.getRequestDispatcher(ADMIN_VIEW_PREFIX + "admin_edit_story.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/admin/story-list");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/story-list");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Stories oldStory = storyDAO.getStoryById(id); 
            
            String title = request.getParameter("Title");
            String author = request.getParameter("Author");
            String description = request.getParameter("Description");
            String[] genreIds = request.getParameterValues("genreID");
            
            String baseUploadPath = "D:\\WorkSpace\\JAVA\\DocTruyen\\src\\main\\webapp\\static\\uploads";
            String storyPath = baseUploadPath + File.separator + id;
            
            File storyDIR = new File(storyPath);
            if (!storyDIR.exists()) storyDIR.mkdirs();

            // 1. Xử lý Thumbnail
            Part thumbPart = request.getPart("ThumnailImageURL");
            String thumbPathDB;
            if (thumbPart != null && thumbPart.getSize() > 0) {
                deletePhysicalFile(baseUploadPath, oldStory.getCoverImageURL());
                String thumbName = "thumb_" + System.currentTimeMillis() + ".jpg";
                thumbPart.write(storyPath + File.separator + thumbName);
                thumbPathDB = "static/uploads/" + id + "/" + thumbName;
            } else {
                thumbPathDB = oldStory.getCoverImageURL(); 
            }

            // 2. Xử lý Cover
            Part coverPart = request.getPart("CoverImageURL");
            String coverPathDB;
            if (coverPart != null && coverPart.getSize() > 0) {
                deletePhysicalFile(baseUploadPath, oldStory.getBigCoverImageURL());
                String coverName = "cover_" + System.currentTimeMillis() + ".jpg";
                coverPart.write(storyPath + File.separator + coverName);
                coverPathDB = "static/uploads/" + id + "/" + coverName;
            } else {
                coverPathDB = oldStory.getBigCoverImageURL();
            }

            // 3. Update DB
            Stories stories = new Stories();
            stories.setId(id);
            stories.setTitle(title);
            stories.setAuthor(author);
            stories.setDescription(description);
            stories.setCoverImageURL(thumbPathDB);
            stories.setBigCoverImageURL(coverPathDB);

            boolean isSuccess = storyDAO.updateStoryFull(stories, genreIds);
            if (isSuccess) {
                request.getSession().setAttribute("message", "Cập nhật thành công!");
                response.sendRedirect(request.getContextPath() + "/admin/story-list");
            } else {
                request.setAttribute("error", "Lỗi cập nhật Database!");
                doGet(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi: " + e.getMessage());
            doGet(request, response);
        }
    }

    private void deletePhysicalFile(String baseUploadPath, String dbPath) {
        if (dbPath == null || dbPath.isEmpty()) return;
        
        // Loại bỏ phần path cứng để lấy path tương đối trong thư mục uploads
        String relativePath = dbPath.replace("static/uploads/", ""); 
        File file = new File(baseUploadPath + File.separator + relativePath.replace("/", File.separator));
        if (file.exists()) {
            file.delete();
        }
    }
}