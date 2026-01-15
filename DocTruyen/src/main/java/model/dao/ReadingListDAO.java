package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.bean.Stories;
import model.database.DBContext;



public class ReadingListDAO {

    
    public boolean isFollowed(int userId, int storyId) {
        String sql = "SELECT 1 FROM Reading_List WHERE user_id = ? AND story_id = ?";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, storyId);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public void follow(int userId, int storyId) {
        String sql = "INSERT INTO Reading_List(user_id, story_id) VALUES (?, ?)";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, storyId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
    public void unfollow(int userId, int storyId) {
        String sql = "DELETE FROM Reading_List WHERE user_id = ? AND story_id = ?";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, storyId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Stories> getFollowedStories(int userId) {
        List<Stories> list = new ArrayList<>();

        String sql = """
            SELECT 
                s.id,
                s.title,
                s.coverImageURL,
                s.last_update
            FROM Reading_List rl
            JOIN Stories s ON rl.story_id = s.id
            WHERE rl.user_id = ?
            ORDER BY s.last_update DESC
        """;

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Stories s = new Stories();
                    s.setId(rs.getInt("id"));
                    s.setTitle(rs.getString("title"));
                    s.setCoverImageURL(rs.getString("coverImageURL"));

                    
                    Timestamp ts = rs.getTimestamp("last_update");
                    if (ts != null) {
                        s.setLastUpdate(ts.toLocalDateTime());
                    }

                    list.add(s);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


}
