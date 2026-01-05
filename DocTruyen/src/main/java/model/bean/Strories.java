package model.bean;

import java.time.LocalDateTime;

public  class Strories {
	
	private int id;
	private String title,author,coverImageURL,
				   bigCoverImageURL,description;
	
	private LocalDateTime createdAt,lastUpdate; 
	private int viewCount;
	
	/*Contructors*/
	public Strories() {
		
	}
	public Strories(int id, String title, String author, String coverImageURL, String bigCoverImageURL,
			String description, LocalDateTime createdAt, LocalDateTime lastUpdate, int viewCount) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.coverImageURL = coverImageURL;
		this.bigCoverImageURL = bigCoverImageURL;
		this.description = description;
		this.createdAt = createdAt;
		this.lastUpdate = lastUpdate;
		this.viewCount = viewCount;
	}
	
	/*Getter và setter*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCoverImageURL() {
		return coverImageURL;
	}
	public void setCoverImageURL(String coverImageURL) {
		this.coverImageURL = coverImageURL;
	}
	public String getBigCoverImageURL() {
		return bigCoverImageURL;
	}
	public void setBigCoverImageURL(String bigCoverImageURL) {
		this.bigCoverImageURL = bigCoverImageURL;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	/*toString*/
	@Override
	public String toString() {
		return "Strories [id=" + id + ", title=" + title + ", author=" + author + ", coverImageURL=" + coverImageURL
				+ ", bigCoverImageURL=" + bigCoverImageURL + ", description=" + description + ", createdAt=" + createdAt
				+ ", lastUpdate=" + lastUpdate + ", viewCount=" + viewCount + "]";
	}
	
	
}
