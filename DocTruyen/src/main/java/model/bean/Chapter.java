package model.bean;

import java.time.LocalDateTime;

public class Chapter {
	private int id;
	private int storyID;
	private String displayNumChapter;
	private int chapterNumber;
	private String title;
	private LocalDateTime publishedAt;
	
	/*Contructors*/
	
	public Chapter() {
    }
	
	public Chapter(int id, int storyID, String displayNumChapter, int chapterNumber, String tittle,
			LocalDateTime publishedAt) {
		super();
		this.id = id;
		this.storyID = storyID;
		this.displayNumChapter = displayNumChapter;
		this.chapterNumber = chapterNumber;
		this.title = tittle;
		this.publishedAt = publishedAt;
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
	public void setStoryId(int storyId) {
		this.storyID = storyId;
	}
	public String getDisplayNumChapter() {
		return displayNumChapter;
	}
	public void setDisplayNumChapter(String displayNumChapter) {
		this.displayNumChapter = displayNumChapter;
	}
	public int getChapterNumber() {
		return chapterNumber;
	}
	public void setChapterNumber(int chapterNumber) {
		this.chapterNumber = chapterNumber;
	}
	public String getTitle() {
		return title;
	}
	public void setTittle(String title) {
		this.title = title;
	}
	public LocalDateTime getPublishedAt() {
		return publishedAt;
	}
	public void setPublishedAt(LocalDateTime publishedAt) {
		this.publishedAt = publishedAt;
	}
	
}
