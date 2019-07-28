package com.sainath.movieinfoservice.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sainath.movieinfoservice.model.Movie;

@RestController
@RequestMapping("/movies")
public class MovieRestController {

	@GetMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") Integer movieId) {
		Map<Integer, Movie> movieMap = new HashMap<>();
		movieMap.put(1, new Movie(1, "The Prestige"));
		movieMap.put(2, new Movie(2, "Titanic"));
		movieMap.put(3, new Movie(3, "Shutter Island"));
		return movieMap.get(movieId);
	}
}
