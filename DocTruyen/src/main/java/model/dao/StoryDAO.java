package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.bean.Genres;
import model.bean.Stories;

import model.database.DBContext;

public class StoryDAO {

	public List<Stories> getAllStories() {
		List<Stories> listStories = new ArrayList<>();

		String sql = "select * from Stories ";
		try (Connection conn = DBContext.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				listStories.add(new Stories(rs.getInt("id"), rs.getString("title"), rs.getString("author"),
						rs.getString("coverImageURL"), rs.getString("bigCoverImageUrl"), rs.getString("description"),
						rs.getTimestamp("created_at").toLocalDateTime(),
						rs.getTimestamp("last_update").toLocalDateTime(), rs.getInt("view_counnt")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listStories;
	}

	public List<Stories> getNewestStories() {
		List<Stories> result = new ArrayList<>();
		String sql = "select top 24 * from Stories order by last_update desc";
		try (Connection conn = DBContext.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				result.add(new Stories(rs.getInt("id"), rs.getString("title"), rs.getString("author"),
						rs.getString("coverImageURL"), rs.getString("bigCoverImageUrl"), rs.getString("description"),
						rs.getTimestamp("created_at").toLocalDateTime(),
						rs.getTimestamp("last_update").toLocalDateTime(), rs.getInt("view_counnt")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int insertStoryFull(Stories stories, String[] genreIDs) {
		Connection conn = null;
		PreparedStatement psStory = null;
		PreparedStatement psGenre = null;
		ResultSet rs = null;
		int storyId = -1;

		String sqlStoryGenres = "insert into Story_Genre (story_id, genre_id) values(?,?)";
		String sqlStories = "insert into Stories (title, author, coverImageURL, bigCoverImageUrl, [description], "
				+ "created_at, last_update, view_counnt) values (?, ?, ?, ?, ?, GETDATE(), GETDATE(), 0)";
		try {
			conn = DBContext.getConnection();
			conn.setAutoCommit(false);

			psStory = conn.prepareStatement(sqlStories, Statement.RETURN_GENERATED_KEYS);
			psStory.setString(1, stories.getTitle());
			psStory.setString(2, stories.getAuthor());
			psStory.setString(3, stories.getCoverImageURL()); // Lúc này có thể để null hoặc chuỗi tạm
			psStory.setString(4, stories.getBigCoverImageURL());
			psStory.setString(5, stories.getDescription());

			int affectedRows = psStory.executeUpdate();
			if (affectedRows == 0)
				throw new SQLException("Lỗi: Không có truyện nào được tạo.");

			rs = psStory.getGeneratedKeys();
			if (rs.next()) {
				storyId = rs.getInt(1);
			}

			if (storyId != -1 && genreIDs != null) {
				psGenre = conn.prepareStatement(sqlStoryGenres);
				for (String gId : genreIDs) {
					try {
						psGenre.setInt(1, storyId);
						psGenre.setInt(2, Integer.parseInt(gId.trim()));
						psGenre.addBatch();
					} catch (NumberFormatException e) {
						continue;
					}
				}
				psGenre.executeBatch();
			}

			conn.commit();
			return storyId; // Trả về ID vừa tạo thành công

		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			e.printStackTrace();
			return -1;
		} finally {
			// Đóng các resource (ps, rs, conn) như bình thường
		}
	}

	public Stories getStoryById(int ID) {
		Stories stories = new Stories();

		String sql = "select * from Stories where id=?";

		try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, ID);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				stories.setId(rs.getInt("id"));
				stories.setAuthor(rs.getString("author"));
				stories.setBigCoverImageURL(rs.getString("bigCoverImageUrl"));
				stories.setCoverImageURL(rs.getString("coverImageURL"));
				stories.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
				stories.setDescription(rs.getString("description"));
				stories.setTitle(rs.getString("title"));
				stories.setLastUpdate(rs.getTimestamp("last_update").toLocalDateTime());
				stories.setViewCount(rs.getInt("view_counnt"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return stories;
	}

	public List<Integer> getGenreIdsByStoryId(int storyId) {
		List<Integer> list = new ArrayList<>();
		String sql = "select genre_id from Story_Genre where story_id = ?";

		try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, storyId);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					// Thêm từng ID thể loại vào danh sách
					list.add(rs.getInt("genre_id"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean updateStoryFull(Stories stories, String[] genreIDs) {
		Connection conn = null;
		PreparedStatement psStory = null;
		PreparedStatement psDelGenre = null;
		PreparedStatement psInsGenre = null;

		String sqlUpdateStory = "update Stories set title=?, author=?, coverImageURL=?, bigCoverImageUrl=?, "
				+ "[description]=?, last_update=GETDATE() where id=?";

		String sqlDeleteGenres = "delete from Story_Genre WHERE story_id = ?";
		String sqlInsertGenres = "insert into Story_Genre (story_id, genre_id) values (?, ?)";

		try {
			conn = DBContext.getConnection();
			conn.setAutoCommit(false); // Bắt đầu Transaction

			// 1. Cập nhật bảng Stories
			psStory = conn.prepareStatement(sqlUpdateStory);
			psStory.setString(1, stories.getTitle());
			psStory.setString(2, stories.getAuthor());
			psStory.setString(3, stories.getCoverImageURL());
			psStory.setString(4, stories.getBigCoverImageURL());
			psStory.setString(5, stories.getDescription());
			psStory.setInt(6, stories.getId());
			psStory.executeUpdate();

			// 2. Xóa tất cả thể loại cũ của truyện này
			psDelGenre = conn.prepareStatement(sqlDeleteGenres);
			psDelGenre.setInt(1, stories.getId());
			psDelGenre.executeUpdate();

			// 3. Chèn lại danh sách thể loại mới (nếu có)
			if (genreIDs != null && genreIDs.length > 0) {
				psInsGenre = conn.prepareStatement(sqlInsertGenres);
				for (String gId : genreIDs) {
					psInsGenre.setInt(1, stories.getId());
					psInsGenre.setInt(2, Integer.parseInt(gId.trim()));
					psInsGenre.addBatch();
				}
				psInsGenre.executeBatch();
			}

			conn.commit(); // Hoàn tất mọi thay đổi
			return true;

		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {

			try {
				if (psStory != null)
					psStory.close();
				if (psDelGenre != null)
					psDelGenre.close();
				if (psInsGenre != null)
					psInsGenre.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	// hàm cập nhật đường dẫn ảnh sau khi đã có ID
	public boolean updateImagePaths(int id, String cover, String bigCover) {
		String sql = "UPDATE Stories SET coverImageURL = ?, bigCoverImageUrl = ? WHERE id = ?";
		try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, cover);
			ps.setString(2, bigCover);
			ps.setInt(3, id);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			return false;
		}
	}

	public static void main(String[] args) {
		StoryDAO stDAO = new StoryDAO();
		List<Stories> listStories = stDAO.getAllStories();
		for (Stories stories : listStories) {
			System.out.println(stories.getTitle().toString());

		}
		System.out.println(stDAO.getStoryById(18).getTitle());
	}

}
