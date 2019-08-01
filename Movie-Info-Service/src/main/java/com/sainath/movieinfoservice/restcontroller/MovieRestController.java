package com.sainath.movieinfoservice.restcontroller;

import java.util.HashMap;
import java.util.Map;

import com.sainath.movieinfoservice.model.MovieSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sainath.movieinfoservice.model.Movie;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieRestController {

	@Value("${api.key}")
	private String apiKey;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
		MovieSummary movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey, MovieSummary.class);
		return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
		/*Map<Integer, Movie> movieMap = new HashMap<>();
		movieMap.put(1, new Movie(1, "The Prestige"));
		movieMap.put(2, new Movie(2, "Titanic"));
		movieMap.put(3, new Movie(3, "Shutter Island"));
		return movieMap.get(movieId);*/
	}
}
