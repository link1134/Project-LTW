package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.bean.User;
import model.database.DBConnection;

public class UserDAO {
	public User loginUser(String username, String password) {
		String sql = "select * from users where username=? and password=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet result = ps.executeQuery();

			if (result.next()) {
				User user = new User();
				user.setId(result.getInt("id"));
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("password"));
				user.setEmail(result.getString("email"));
				user.setName(result.getString("name"));
				return user;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public boolean registerUser(User user) {
		String sql = "Insert into users(username,password,email,role)";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(2, user.getEmail());
			ps.setString(2, user.getName());

			int result = ps.executeUpdate();
			return result > 0;

		}

		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;

		}
	}
}
