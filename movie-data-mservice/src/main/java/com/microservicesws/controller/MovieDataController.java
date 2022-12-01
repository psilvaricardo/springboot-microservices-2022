package com.microservicesws.controller;

import com.microservicesws.model.Rating;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/ratingsdata")
public class MovieDataController {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId) {
        log.info("MovieDataController calling getRating for Id: {}", movieId);

        // dummy API, hardcoded temporally for the proof of concept
        return new Rating(movieId, 4);
    }

    @RequestMapping("/users/{userId}")
    public List<Rating> getRatingByUserId(@PathVariable("userId") String userId) {
        log.info("MovieDataController calling getRatingByUserId for Id: {}", userId);

        // dummy API, hardcoded temporally for the proof of concept
        List<Rating> ratings = Arrays.asList(
                new Rating("76340", 4),
                new Rating("76341", 3)
        );

        return ratings;
    }

}
