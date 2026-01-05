package model.bean;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public  class Stories {
	private int id;
	private String title,author,coverImageURL,
				   bigCoverImageURL,description;
	
	private LocalDateTime createdAt,lastUpdate; 
	private int viewCount;
	private int numChapter;
	
	
	/*Contructors*/
	public Stories() {
		
	}
	public Stories(int id, String title, String author, String coverImageURL, String bigCoverImageURL,
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
	public String getTimeAgo() {
        // Gọi hàm format từ Utils để tự động tính toán
        return TimeAgoUtils.format(this.lastUpdate);
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
	public String getLastUpdateDaysAgo() {
	    if (lastUpdate == null) return "";

	    long days = ChronoUnit.DAYS.between(lastUpdate, LocalDateTime.now());

	    if (days <= 0)
	        return "hôm nay";

	    return days + " ngày trước";
	}
	
	public int getNumChapter() { return numChapter; }
    public void setNumChapter(int numChapter) { this.numChapter = numChapter; }
	
	
}
