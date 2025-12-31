package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.bean.Page;
import model.database.DBContext;

public class PageDAO {

	public List<Page> getPagesByChapterId(int chapterId) {
		List<Page> pageList = new ArrayList<>();
		String sql = "SELECT id, chapter_id, page_number, page_url " + "FROM Page " + "WHERE chapter_id = ? "
				+ "ORDER BY page_number ASC";

		try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, chapterId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Page page = new Page();
				page.setId(rs.getInt("id"));
				page.setChapterId(rs.getInt("chapter_id"));
				page.setPageNumber(rs.getInt("page_number"));
				page.setPageURL(rs.getString("page_url"));

				pageList.add(page);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pageList;
	}

	public boolean insertPage(Page page) {
		String sql = "INSERT INTO Page (chapter_id, page_number, page_url) VALUES (?, ?, ?)";

		try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, page.getChapterId());
			ps.setInt(2, page.getPageNumber());
			ps.setString(3, page.getPageURL());

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}