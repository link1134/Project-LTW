package model.bean;

public class Genres {
	private int id;
	private String name;
	
	/*Contructors*/
	public Genres(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Genres() {
		super();
	}
	
	/*Getter và setter*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
