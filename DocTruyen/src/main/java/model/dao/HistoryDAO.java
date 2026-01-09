package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.bean.ReadingHistory;
import model.database.DBContext;

public class HistoryDAO {

	public void saveHistory(int userId, int chapterId, int storyId) {
		String checkSql = """
				SELECT 1 FROM Reading_History
				WHERE user_id = ? AND chapter_id = ?
				""";

		String updateSql = """
				UPDATE Reading_History
				SET read_at = ?
				WHERE user_id = ? AND chapter_id = ?
				""";

		String insertSql = """
				INSERT INTO Reading_History (user_id, story_id, chapter_id, read_at)
				VALUES (?, ?, ?, ?)
				""";

		try (Connection conn = DBContext.getConnection()) {

			LocalDateTime now = LocalDateTime.now();

			try (PreparedStatement checkPs = conn.prepareStatement(checkSql)) {
				checkPs.setInt(1, userId);
				checkPs.setInt(2, chapterId);

				ResultSet rs = checkPs.executeQuery();

				if (rs.next()) {

					try (PreparedStatement updatePs = conn.prepareStatement(updateSql)) {
						updatePs.setTimestamp(1, Timestamp.valueOf(now));
						updatePs.setInt(2, userId);
						updatePs.setInt(3, chapterId);
						updatePs.executeUpdate();
					}
				} else {

					try (PreparedStatement insertPs = conn.prepareStatement(insertSql)) {
						insertPs.setInt(1, userId);
						insertPs.setInt(2, storyId);
						insertPs.setInt(3, chapterId);
						insertPs.setTimestamp(4, Timestamp.valueOf(now));
						insertPs.executeUpdate();
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<ReadingHistory> getHistoryByUserId(int userId) {
		List<ReadingHistory> list = new ArrayList<>();

		String sql = """
				    SELECT id, user_id, story_id, chapter_id, read_at
				    FROM Reading_History
				    WHERE user_id = ?
				    ORDER BY read_at DESC
				""";

		try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ReadingHistory rh = new ReadingHistory();
				rh.setId(rs.getInt("id"));
				rh.setUserID(rs.getInt("user_id"));
				rh.setStoryID(rs.getInt("story_id"));
				rh.setChapterID(rs.getInt("chapter_id"));

				// DATETIME -> LocalDateTime
				Timestamp ts = rs.getTimestamp("read_at");
				if (ts != null) {
					rh.setReadAt(ts.toLocalDateTime());
				}

				list.add(rh);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
