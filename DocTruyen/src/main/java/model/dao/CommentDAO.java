package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import model.bean.Comment;
import model.database.DBContext;

public class CommentDAO {

	/**
	 * Lấy tất cả comment của một chapter, sắp xếp theo createdAt tăng dần
	 */
	public List<Comment> getCommentsByChapterId(int chapterId) {
	    List<Comment> comments = new ArrayList<>();

	    String sql =
	        "SELECT c.id, c.user_id, c.chapter_id, c.parent_comment_id, " +
	        "       c.content, c.created_at, " +
	        "       u.username, " +
	        "       pu.username AS parent_username " +
	        "FROM Comment c " +
	        "JOIN [User] u ON c.user_id = u.id " +
	        "LEFT JOIN Comment pc ON c.parent_comment_id = pc.id " +
	        "LEFT JOIN [User] pu ON pc.user_id = pu.id " +
	        "WHERE c.chapter_id = ? " +
	        "ORDER BY c.created_at ASC";

	    try (Connection conn = DBContext.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, chapterId);

	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                Comment c = new Comment();

	                c.setId(rs.getInt("id"));
	                c.setUserId(rs.getInt("user_id"));
	                c.setChapterId(rs.getInt("chapter_id"));

	                int parentId = rs.getInt("parent_comment_id");
	                c.setParentCommentId(rs.wasNull() ? null : parentId);

	                c.setContent(rs.getString("content"));
	                c.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());

	                c.setUsername(rs.getString("username"));
	                c.setParentUsername(rs.getString("parent_username"));

	                comments.add(c);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return comments;
	}


	public void addComment(Comment comment) {
		String sql = "INSERT INTO Comment(user_id, chapter_id, parent_comment_id, content, created_at) "
				+ "VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = DBContext.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

			ps.setInt(1, comment.getUserId());
			ps.setInt(2, comment.getChapterId());

			if (comment.getParentCommentId() == null)
				ps.setNull(3, java.sql.Types.INTEGER);
			else
				ps.setInt(3, comment.getParentCommentId());

			ps.setString(4, comment.getContent());
			ps.setTimestamp(5, java.sql.Timestamp.valueOf(comment.getCreatedAt()));

			ps.executeUpdate();

			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next()) {
					comment.setId(rs.getInt(1)); // Lấy ID mới tạo
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Comment> getCommentsWithUser(int chapterId) {
		List<Comment> comments = new ArrayList<>();

		String sql = "SELECT c.id, c.user_id, c.chapter_id, c.parent_comment_id, " + "       c.content, c.created_at, "
				+ "       u.username, " + "       pu.username AS parent_username " + "FROM Comment c "
				+ "JOIN [User] u ON c.user_id = u.id " + "LEFT JOIN Comment pc ON c.parent_comment_id = pc.id "
				+ "LEFT JOIN [User] pu ON pc.user_id = pu.id " + "WHERE c.chapter_id = ? "
				+ "ORDER BY c.created_at ASC";

		try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, chapterId);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Comment c = new Comment();

					c.setId(rs.getInt("id"));
					c.setUserId(rs.getInt("user_id"));
					c.setChapterId(rs.getInt("chapter_id"));

					int parentId = rs.getInt("parent_comment_id");
					c.setParentCommentId(rs.wasNull() ? null : parentId);

					c.setContent(rs.getString("content"));
					c.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());

					c.setUsername(rs.getString("username"));
					c.setParentUsername(rs.getString("parent_username")); 

					comments.add(c);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return comments;
	}

	public int countComment(int chapterId) {
		String sql = "SELECT COUNT(*) FROM Comment WHERE chapter_id = ?";
		int count = 0;

		try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, chapterId);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					count = rs.getInt(1);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return count;
	}

	public void deleteCommentCascadeRecursive(int commentId) {
		String getChildrenSql = "SELECT id FROM Comment WHERE parent_comment_id = ?";
		String deleteSql = "DELETE FROM Comment WHERE id = ?";

		try (Connection conn = DBContext.getConnection()) {
			conn.setAutoCommit(false);
			deleteRecursive(conn, getChildrenSql, deleteSql, commentId);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteRecursive(Connection conn, String getChildrenSql, String deleteSql, int commentId)
			throws SQLException {

		try (PreparedStatement ps = conn.prepareStatement(getChildrenSql)) {
			ps.setInt(1, commentId);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					int childId = rs.getInt("id");
					// 2. Đệ quy xóa con
					deleteRecursive(conn, getChildrenSql, deleteSql, childId);
				}
			}
		}

		try (PreparedStatement ps = conn.prepareStatement(deleteSql)) {
			ps.setInt(1, commentId);
			ps.executeUpdate();
		}
	}

}
