package model.bean;

import java.time.LocalDateTime;

public class ReadingHistory {
	private int id,userID,storyID,chapterID;
	private LocalDateTime readAt ;
	
	/*Contructors*/
	public ReadingHistory(int id, int userID, int storyID, int chapterID, LocalDateTime readAt) {
		super();
		this.id = id;
		this.userID = userID;
		this.storyID = storyID;
		this.chapterID = chapterID;
		this.readAt = readAt;
	}
	
	/*Getter và setter*/
	public ReadingHistory() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public int getChapterID() {
		return chapterID;
	}
	public void setChapterID(int chapterID) {
		this.chapterID = chapterID;
	}
	public LocalDateTime getReadAt() {
		return readAt;
	}
	public void setReadAt(LocalDateTime readAt) {
		this.readAt = readAt;
	}
	
	
}
