package com.sainath.ratingsdataservice.restcontroller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sainath.ratingsdataservice.model.Rating;
import com.sainath.ratingsdataservice.model.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsDataRestController {
	
	@GetMapping("/users/{userId}")
	public UserRating getRating(@PathVariable("userId") String userId) {
		List<Rating> ratings = Arrays.asList(new Rating("100", 4), new Rating("550", 3));
		return new UserRating(ratings);
	}
}
