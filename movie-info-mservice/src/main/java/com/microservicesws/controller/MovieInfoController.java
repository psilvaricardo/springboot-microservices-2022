package com.microservicesws.controller;

import com.microservicesws.model.MovieInfo;
import com.microservicesws.model.themoviedb.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@RestController
@RequestMapping("/movies")
public class MovieInfoController {

    @Autowired
    private WebClient webClient;

    @RequestMapping("/{movieId}")
    public MovieInfo getMovieInfo(@PathVariable("movieId") String movieId) {
        log.info("MovieInfoController calling getMovieInfo from external MovieDB service {}", movieId);

        String urlStr = "/3/movie/"+movieId;
        log.info("MovieInfoController calling MovieDB service: {}", urlStr);

        // making a REST API call to the external movie db service
        Movie movie = webClient
                       .get()
                       .uri("/3/movie/{movieId}", movieId)
                       .accept(MediaType.APPLICATION_JSON)
                       .retrieve()
                       .bodyToMono(Movie.class)
                       .block();

        // log.info("MovieInfoController returning: {}", movie);
        return new MovieInfo(movieId, movie.getOriginalTitle(), movie.getOverview());
    }

}
