package com.sainath.moviecatalogservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sainath.moviecatalogservice.UserRating;
import com.sainath.moviecatalogservice.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class UserRatingService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackUserRating",
                    commandProperties = {
                            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
                    })
    public UserRating getUserRating(@PathVariable("userId") String userId) {
        return restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId, UserRating.class);
    }

    private UserRating getFallbackUserRating(@PathVariable("userId") String userId) {
        return new UserRating(userId, Collections.singletonList(new Rating(0, 0)));
    }
}
