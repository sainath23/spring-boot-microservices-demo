package com.sainath.moviecatalogservice.model;

public class CatalogItem {

	private String title;
	private String description;
	private Integer ratings;
	
	public CatalogItem() {
	}

	public CatalogItem(String title, String description, Integer ratings) {
		super();
		this.title = title;
		this.description = description;
		this.ratings = ratings;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getRatings() {
		return ratings;
	}
	
	public void setRatings(Integer ratings) {
		this.ratings = ratings;
	}
}
