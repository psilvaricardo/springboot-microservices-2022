package com.microservicesws.service;

import com.microservicesws.model.Rating;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
public class MovieDataService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Mono<List<Rating>> getResponseFromDataService(String userId) {
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

}
