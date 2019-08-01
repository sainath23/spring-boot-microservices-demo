package com.sainath.moviecatalogservice;

import java.util.List;

import com.sainath.moviecatalogservice.model.Rating;

public class UserRating {
	private String userId;
	private List<Rating> ratings;

	public UserRating() {
	}

	public UserRating(String userId, List<Rating> ratings) {
		this.userId = userId;
		this.ratings = ratings;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserRating{" +
				"ratings=" + ratings +
				'}';
	}
}
