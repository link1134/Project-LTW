package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.bean.Genres;
import model.bean.Stories;
import model.database.DBContext;

public class GenreDAO {
	/*
	 * lấy danh sách các thể loại trong database
	 */
	public List<Genres> getAllGenres() {
		List<Genres> listGenres = new ArrayList<>();
		String sql = "Select * from Genres";
		try (Connection conn = DBContext.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				listGenres.add(new Genres(rs.getInt("id"), rs.getString("name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listGenres;
	}

	/* kiểm tra tên có trùng trong DB chưa */
	public boolean existsByName(String name) {
		String sql = "select count(*) from Genres where name = ?";
		try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, name);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0; // Trả về true nếu count > 0
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/* xóa thể loại khỏi DB */
	public boolean deleteGenres(int id) {
		String sql = "Delete from Genres where id=?";

		try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, id);
			int result = ps.executeUpdate();
			System.out.println("Xóa " + id + ": " + (result > 0 ? "Thành công" : "Thất bại"));
			return result > 0;

		} catch (SQLException e) {
			System.err.println("Lỗi khi xóa thể loại '" + id + "': " + e.getMessage());
			e.printStackTrace();

		}
		return false;
	}

	/* thêm thể loại mới */
	public boolean addGenres(String name) {
		if (existsByName(name)) {
			System.out.println("Thể loại '" + name + "' đã tồn tại. Không thêm nữa.");
			return false;
		}
		String sql = "Insert into Genres (name) values (?) ";
		try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, name);
			int result = ps.executeUpdate();
			System.out.println("Thêm " + name + ": " + (result > 0 ? "Thành công" : "Thất bại"));
			return result > 0;
		} catch (SQLException e) {
			System.err.println("Lỗi khi thêm thể loại '" + name + "': " + e.getMessage());
			e.printStackTrace();

		}
		return false;
	}

	public String getGenreNameById(int genreId) {
		String sql = "SELECT name FROM Genres WHERE id = ?";
		try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, genreId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Stories> getStoriesByGenre(int genreId) {
		List<Stories> list = new ArrayList<>();

		String sql = "SELECT s.id, s.title, s.author, s.coverImageURL, s.bigCoverImageUrl, "
				+ "       s.description, s.created_at, s.last_update, s.view_counnt, "
				+ "       COUNT(c.id) AS numChapter " + "FROM Stories s " + "JOIN Story_Genre sg ON s.id = sg.story_id "
				+ "LEFT JOIN Chapter c ON s.id = c.story_id AND c.status = 'PUBLISHED' "
				+ "WHERE sg.genre_id = ? "
				+ "GROUP BY s.id, s.title, s.author, s.coverImageURL, s.bigCoverImageUrl, "
				+ "         s.description, s.created_at, s.last_update, s.view_counnt " + "ORDER BY s.last_update DESC";

		try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, genreId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Stories s = new Stories();
				s.setId(rs.getInt("id"));
				s.setTitle(rs.getString("title"));
				s.setAuthor(rs.getString("author"));
				s.setCoverImageURL(rs.getString("coverImageURL"));
				s.setBigCoverImageURL(rs.getString("bigCoverImageUrl"));
				s.setDescription(rs.getString("description"));

				if (rs.getTimestamp("created_at") != null)
					s.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());

				if (rs.getTimestamp("last_update") != null)
					s.setLastUpdate(rs.getTimestamp("last_update").toLocalDateTime());

				s.setViewCount(rs.getInt("view_counnt"));
				s.setNumChapter(rs.getInt("numChapter"));

				list.add(s);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public static void main(String[] args) {

		GenreDAO gd = new GenreDAO();
		List<Genres> listGenres = gd.getAllGenres();
		gd.addGenres("hài hước");
		gd.addGenres("buồn cười");
		gd.addGenres("hài nhảm");
		gd.addGenres("Hành động");

		// System.out.println(gd.deleteGenres("hài hước"));

	}
}
