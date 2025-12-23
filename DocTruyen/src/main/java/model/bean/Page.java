package model.bean;

public class Page {
	private int id, storyID, pageNumber;
	private String pageURL;

	
		/*Contructors*/
	public Page(int id, int storyID, int pageNumber, String pageURL) {
		super();
		this.id = id;
		this.storyID = storyID;
		this.pageNumber = pageNumber;
		this.pageURL = pageURL;
	}

	public Page() {
		
	}
		/*Getter và setter*/

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStoryID() {
		return storyID;
	}

	public void setStoryID(int storyID) {
		this.storyID = storyID;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getPageURL() {
		return pageURL;
	}

	public void setPageURL(String pageURL) {
		this.pageURL = pageURL;
	}

}
