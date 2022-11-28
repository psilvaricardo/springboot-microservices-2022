package com.microservicesws.controller;

import com.microservicesws.model.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/movies")
public class MovieInfoController {

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
        log.info("MovieInfoController calling getMovieInfo {}", movieId);

        // dummy API, hardcoded temporally for the proof of concept
        return new Movie(movieId, "Test");
    }

}
