package model.bean;

import java.io.Serializable;

public class User implements Serializable{
	private static final long seriaVersionUID = 1l;
	private int id;
	private String userName,password,email,role;
	private String avartarURL;
	
	/*Contructors*/
	public User(int id, String userName, String password, String email, String role,
			String avartarURL) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.role = role;
		this.avartarURL = avartarURL;
	}
	public User() {
		super();
	}
	
	/*Getter và setter*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getAvartarURL() {
		return avartarURL;
	}
	public void setAvartarURL(String avartarURL) {
		this.avartarURL = avartarURL;
	}
	public static long getSeriaversionuid() {
		return seriaVersionUID;
	}
	
	
	
}
