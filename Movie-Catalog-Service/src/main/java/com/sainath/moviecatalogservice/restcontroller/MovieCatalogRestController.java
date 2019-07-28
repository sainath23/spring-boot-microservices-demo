package com.sainath.moviecatalogservice.restcontroller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.sainath.moviecatalogservice.UserRating;
import com.sainath.moviecatalogservice.model.CatalogItem;
import com.sainath.moviecatalogservice.model.Movie;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogRestController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;

	@GetMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId, UserRating.class);
		return userRating.getRatings().stream()
						.map(rating -> {
							Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
							return new CatalogItem(movie.getTitle(), "Movie description", rating.getRating());
						})
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
