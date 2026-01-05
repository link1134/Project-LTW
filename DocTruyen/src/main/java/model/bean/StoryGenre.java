package model.bean;

public class StoryGenre {
	private int storyID,genreID;

	/*Contructors*/
	public StoryGenre(int storyID, int genreID) {
		super();
		this.storyID = storyID;
		this.genreID = genreID;
	}

	/*Getter và setter*/
	public StoryGenre() {
		super();
	}

	public int getStoryID() {
		return storyID;
	}

	public void setStoryID(int storyID) {
		this.storyID = storyID;
	}

	public int getGenreID() {
		return genreID;
	}

	public void setGenreID(int genreID) {
		this.genreID = genreID;
	}
	
	
}
