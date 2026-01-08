package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.bean.Chapter;
import model.database.DBContext;

public class ChapterDAO {
	public List<Chapter> getChaptersByStoryId(int storyId) {
		List<Chapter> chapters = new ArrayList<>();

		String sql = """
				    SELECT *
				    FROM Chapter
				    WHERE story_id = ?
				    ORDER BY chapter_number ASC
				""";

		try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, storyId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Chapter c = new Chapter();
				c.setId(rs.getInt("id"));
				c.setStoryID(rs.getInt("story_id"));
				c.setDisplayNumChapter(rs.getString("display_num_chapter"));
				c.setChapterNumber(rs.getInt("chapter_number"));
				c.setTitle(rs.getString("title"));

				Timestamp ts = rs.getTimestamp("published_at");
				if (ts != null) {
					c.setPublishedAt(ts.toLocalDateTime());
				}

				c.setStatus(rs.getString("status"));

				chapters.add(c);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return chapters;
	}

	public boolean updateStatus(int chapterId, String status) {
		String sql = "UPDATE Chapter SET status = ? WHERE id = ?";

		try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, status);
			ps.setInt(2, chapterId);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public int highestChapterNumber(int storyId) {
		String sql = "SELECT ISNULL(MAX(chapter_number), 0) FROM Chapter WHERE story_id = ?";

		try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, storyId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public boolean insertChapter(Chapter c) {
		String sql = """
				    INSERT INTO Chapter
				    (story_id, display_num_chapter, chapter_number, title)
				    VALUES (?, ?, ?, ?)
				""";

		try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, c.getStoryID());
			ps.setString(2, c.getDisplayNumChapter());
			ps.setInt(3, c.getChapterNumber());
			ps.setString(4, c.getTitle());

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateChapterInfo(int chapterId, String display, String title) {

		String sql = """
				UPDATE Chapter
				SET display_num_chapter = ?,
				title = ?,
				published_at = CURRENT_TIMESTAMP
				WHERE id = ?
				""";

		try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, display);
			ps.setString(2, title);
			ps.setInt(3, chapterId);

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean existsDisplayNum(int storyId, String displayNum, Integer ignoreChapterId) {

		String sql = """
				SELECT 1 FROM Chapter
				WHERE story_id = ?
				AND display_num_chapter = ?
				""";

		if (ignoreChapterId != null) {
			sql += " AND id <> ?";
		}

		try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, storyId);
			ps.setString(2, displayNum);

			if (ignoreChapterId != null) {
				ps.setInt(3, ignoreChapterId);
			}

			return ps.executeQuery().next();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Chapter getLatestPublishedChapter(int storyId) {
	    String sql = """
	        SELECT TOP 1 *
	        FROM Chapter
	        WHERE story_id = ?
	          
	        ORDER BY published_at DESC
	    """;

	    try (Connection conn = DBContext.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, storyId);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            Chapter c = new Chapter();
	            c.setId(rs.getInt("id"));
	            c.setStoryID(rs.getInt("story_id"));
	            c.setDisplayNumChapter(rs.getString("display_num_chapter"));

	            Timestamp ts = rs.getTimestamp("published_at");
	            if (ts != null) {
	                c.setPublishedAt(ts.toLocalDateTime());
	            }
	            return c;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	
}
