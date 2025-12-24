package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import model.bean.User;
import model.database.DBContext;

public class UserDAO {
	public User loginUser(String email, String password) {

	    String sql = "SELECT * FROM [User] WHERE email = ?";

	    try (Connection conn = DBContext.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, email);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            String hashedPassword = rs.getString("password");

	            // So sánh BCrypt
	            if (BCrypt.checkpw(password, hashedPassword)) {
	                User user = new User();
	                user.setId(rs.getInt("id"));
	                user.setUserName(rs.getString("username"));
	                user.setEmail(rs.getString("email"));
	                user.setRole(rs.getString("role"));
	                return user;
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	public boolean registerUser(User user) {

	    String sql = "INSERT INTO [User] (username, password, email, role) VALUES (?, ?, ?, ?)";

	    try (
	        Connection conn = DBContext.getConnection();
	        PreparedStatement ps = conn.prepareStatement(sql)
	    ) {
	        ps.setString(1, user.getUserName());
	        ps.setString(2, user.getPassword()); 
	        ps.setString(3, user.getEmail());
	        ps.setString(4, user.getRole());

	        int result = ps.executeUpdate();
	        return result > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

}
