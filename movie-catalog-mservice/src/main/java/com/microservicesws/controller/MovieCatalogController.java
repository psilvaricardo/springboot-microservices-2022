package com.microservicesws.controller;

import com.microservicesws.model.CatalogItem;
import com.microservicesws.model.Movie;
import com.microservicesws.model.Rating;
import com.microservicesws.model.UserRating;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @Autowired
    WebClient webClient;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        log.info("MovieCatalogController calling getCatalog {}", userId);

        // https://www.baeldung.com/spring-webclient-json-list
        // making a REST API call to the data ms
        Mono<List<Rating>> response =
                webClient
                        .get()
                        .uri("http://localhost:8083/ratingsdata/users/"+ userId)
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<List<Rating>>() {});
        // get all the rated movie IDs
        List<Rating> ratings = response.block();


        // for each movie ID, call the movie info service and get details
        return ratings
               // .block()
               // .getRatings()
                .stream()
                .map( rating -> {
                    // making the API call
                    Movie movie =
                            webClient
                            .get()
                            .uri("http://localhost:8082/movies/"+ rating.getMovieId())
                            .retrieve()
                            .bodyToMono(Movie.class)
                            .block();
                    return new CatalogItem(movie.getName(), "Test Desc", rating.getRating());
                    }
                )
                .collect(Collectors.toList()); // lastly, put them all together
    }

}
