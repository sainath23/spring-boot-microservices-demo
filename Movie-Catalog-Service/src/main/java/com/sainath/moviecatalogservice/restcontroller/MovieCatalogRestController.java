package com.sainath.moviecatalogservice.restcontroller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sainath.moviecatalogservice.model.Movie;
import com.sainath.moviecatalogservice.model.Rating;
import com.sainath.moviecatalogservice.service.MovieInfoService;
import com.sainath.moviecatalogservice.service.UserRatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.sainath.moviecatalogservice.UserRating;
import com.sainath.moviecatalogservice.model.CatalogItem;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogRestController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;

	@Autowired
	private MovieInfoService movieInfoService;

	@Autowired
	private UserRatingService userRatingService;

	private static final Logger logger = LoggerFactory.getLogger(MovieCatalogRestController.class);

	@GetMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		UserRating userRating = userRatingService.getUserRating(userId);
		logger.info("userRating => {}", userRating);
		return userRating.getRatings().stream()
						.map(movieInfoService::getCatalogItem)
						.collect(Collectors.toList());
		/*return ratings.stream().map(rating -> {
			Movie movie = webClientBuilder.build()
					.get()
					.uri("http://localhost:8082/movies/1")
					.retrieve()
					.bodyToMono(Movie.class)
					.block();
			return new CatalogItem(movie.getTitle(), "Movie description", rating.getRating());
		}).collect(Collectors.toList());*/
	}
}
