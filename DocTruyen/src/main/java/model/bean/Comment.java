package model.bean;

import java.time.LocalDateTime;

public class Comment {
    private int id;
    private int userId;
    private int chapterId;
    private Integer parentCommentId; 
    private String content;
    private LocalDateTime createdAt;
    private String username;
    private String parentUsername;

    
    public Comment() {
    }

    public Comment(int userId, int chapterId, Integer parentCommentId, String content) {
        this.userId = userId;
        this.chapterId = chapterId;
        this.parentCommentId = parentCommentId;
        this.content = content;
    }
    public String getTimeAgo() {
        // Gọi hàm format từ Utils để tự động tính toán
        return TimeAgoUtils.format(this.createdAt);
    }
    // ===== Getter & Setter =====
    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public String getParentUsername() {
        return parentUsername;
    }

    public void setParentUsername(String parentUsername) {
        this.parentUsername = parentUsername;
    }
}
