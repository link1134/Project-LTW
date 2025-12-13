package model.bean;

public class User {
	private int id;
	private String username,password,email;
	private int role;//0 : admin -- 1:user
	private String name;
	public User( String username,String password, String email,String name) {
		super();
		
		this.username=username;
		this.password = password;
		this.email = email;
		this.role = 1;
		this.name=name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public User() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
		this.email=email;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
