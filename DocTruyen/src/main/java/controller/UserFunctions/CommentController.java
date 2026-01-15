package controller.UserFunctions;

import java.io.Console;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Comment;
import model.bean.User;
import model.dao.CommentDAO;

@WebServlet("/comment")

public class CommentController extends HttpServlet {
	private CommentDAO commentDAO = new CommentDAO();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return;
		}

		String action = request.getParameter("action");

		if ("delete".equals(action)) {
			int commentId = Integer.parseInt(request.getParameter("commentId"));
			int chapterId = Integer.parseInt(request.getParameter("chapterId"));

			commentDAO.deleteCommentCascadeRecursive(commentId);

			List<Comment> comments = commentDAO.getCommentsWithUser(chapterId);
			request.setAttribute("comments", comments);

			request.getRequestDispatcher("/WEB-INF/view/utility/comments.jsp").forward(request, response);
			return;
		}

		String chapterIdStr = request.getParameter("chapterId");
		int chapterId = Integer.parseInt(chapterIdStr);
		String content = request.getParameter("content");

		Comment comment = new Comment();
		comment.setUserId(user.getId());
		comment.setChapterId(chapterId);
		String parentIdStr = request.getParameter("parentCommentId");
		if (parentIdStr != null && !parentIdStr.isEmpty()) {
			comment.setParentCommentId(Integer.parseInt(parentIdStr));
		} else {
			comment.setParentCommentId(null);
		}

		comment.setContent(content);
		comment.setCreatedAt(LocalDateTime.now());

		commentDAO.addComment(comment);

		List<Comment> comments = commentDAO.getCommentsWithUser(chapterId);
		request.setAttribute("comments", comments);

		request.getRequestDispatcher("/WEB-INF/view/utility/comments.jsp").forward(request, response);
	}

}
