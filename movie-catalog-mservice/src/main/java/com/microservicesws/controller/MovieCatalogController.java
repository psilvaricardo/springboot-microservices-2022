package com.microservicesws.controller;

import com.microservicesws.model.CatalogItem;
import com.microservicesws.model.MovieInfo;
import com.microservicesws.model.Rating;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        log.info("MovieCatalogController calling getCatalog {}", userId);

        // https://www.baeldung.com/spring-webclient-json-list
        // making a REST API call to the data ms
        Mono<List<Rating>> response = getResponseFromDataService(userId);

        // get all the rated movie IDs
        List<Rating> ratings = response.block();


        // for each movie ID, call the movie info service and get details
        return ratings
                .stream()
                .map( rating -> {
                    // making the API call
                    MovieInfo movieInfo = getResponseFromMovieInfo(rating);
                    return new CatalogItem(movieInfo.getName(), movieInfo.getDescription(), rating.getRating());
                    }
                )
                .collect(Collectors.toList()); // lastly, put them all together
    }

    private Mono<List<Rating>> getResponseFromDataService(String userId) {
        return webClientBuilder.build()
                .get()
                .uri("http://movie-data-mservice/ratingsdata/users/" + userId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                /*
                    .onStatus(HttpStatus::isError, resp -> {
                        if (resp.statusCode().is4xxClientError()) {
                            log.error("Error: 4xx response from movie-data-mservice");
                            return Mono.error(new Exception("Error: 4xx response from movie-data-mservice"));
                        } else if (resp.statusCode().is5xxServerError()) {
                            log.error("Error: 5xx response from movie-data-mservice");
                            return Mono.error(new Exception("Error: 5xx response from movie-data-mservice"));
                        }
                    log.error("Error: HTTP " + resp.statusCode() + " response from movie-data-mservice");
                    return Mono.error(new Exception("Error: HTTP " + resp.statusCode() + " response from movie-data-mservice"));
                    })*/
                .bodyToMono(new ParameterizedTypeReference<List<Rating>>() {
                });
    }

    private MovieInfo getResponseFromMovieInfo(Rating rating) {
        return webClientBuilder.build()
                .get()
                .uri("http://movie-info-mservice/movies/" + rating.getMovieId())
                .retrieve()
                .bodyToMono(MovieInfo.class)
                .block();
    }

}
