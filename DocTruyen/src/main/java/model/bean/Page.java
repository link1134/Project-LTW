package model.bean;

public class Page {
	private int id, chapterId, pageNumber;
	private String pageURL;

	
		/*Contructors*/
	public Page(int id, int chapterId, int pageNumber, String pageURL) {
		super();
		this.id = id;
		this.chapterId = chapterId;
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





	public int getChapterId() {
		return chapterId;
	}

	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
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
