package model.dao;

import java.sql.*;

import model.bean.Stories;
import model.bean.Strories;
import model.database.DBContext;

public class StoryDAO {
	public boolean insertStoryFull(Stories stories, String[] genreIDs) {
		Connection conn = null;
		PreparedStatement psStory = null;
		PreparedStatement psGenre = null;
		ResultSet rs = null;
		String sqlStoryGenres = "insert into Story_Genre (story_id,genre_id) values(?,?)";
		String sqlStrories = "	insert into Stories (title,author,coverImageURL,bigCoverImageUrl,[description],"
				+ "created_at, last_update, view_counnt)" + "values (?, ?, ?, ?, ?, GETDATE(), GETDATE(), 0)";
		try {
			conn = DBContext.getConnection();
			conn.setAutoCommit(false);

			psStory = conn.prepareStatement(sqlStrories, Statement.RETURN_GENERATED_KEYS);
			
			
			psStory.setString(1, stories.getTitle());// thêm vào dấu ? đầu tiên
			psStory.setString(2, stories.getAuthor());
			psStory.setString(3, stories.getCoverImageURL());
			psStory.setString(4, stories.getBigCoverImageURL());
			psStory.setString(5, stories.getDescription());

			int affectedRows = psStory.executeUpdate();
			if (affectedRows == 0)
				throw new SQLException("Lỗi: Không có truyện nào được tạo.");

			// Lấy ID truyện vừa tự động tăng
			rs = psStory.getGeneratedKeys();
			int storyId = -1;
			if (rs.next()) {
				storyId = rs.getInt(1);// lấy cột đầu tiên
			}

			// chèn dữ liệu vào bảng Story_Genre
			if (storyId != -1 && genreIDs != null) {
			    psGenre = conn.prepareStatement(sqlStoryGenres);
			    for (String gId : genreIDs) {
			        try {
			            psGenre.setInt(1, storyId);
			            psGenre.setInt(2, Integer.parseInt(gId.trim())); // Loại bỏ khoảng trắng
			            psGenre.addBatch();
			        } catch (NumberFormatException e) {
			            System.err.println("Giá trị ID thể loại không hợp lệ: " + gId);
			            // Có thể bỏ qua giá trị lỗi này và tiếp tục vòng lặp
			            continue; 
			        }
			    }
			    psGenre.executeBatch();
			}

			// thực hiên được tạo truyện và thêm thể loại thì commit
			conn.commit();
			return true;

		} catch (SQLException e) {
			//lỗi tiến hành rollback
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			e.printStackTrace();
		}
		return false;
	}
	public static void main(String[] args) {
		StoryDAO stDAO = new StoryDAO();
		
	
	}
}
