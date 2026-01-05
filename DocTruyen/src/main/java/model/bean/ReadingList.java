package model.bean;

public class ReadingList {
	private int userID,storyID;
	
	/*Contructors*/
	public ReadingList(int userID, int storyID) {
		super();
		this.userID = userID;
		this.storyID = storyID;
	}

	public ReadingList() {
		super();
	}

	/*Getter và setter*/
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getStoryID() {
		return storyID;
	}

	public void setStoryID(int storyID) {
		this.storyID = storyID;
	}
	
	
}
