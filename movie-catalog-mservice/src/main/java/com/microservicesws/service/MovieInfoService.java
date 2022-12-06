package com.microservicesws.service;

import com.microservicesws.model.MovieInfo;
import com.microservicesws.model.Rating;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
public class MovieInfoService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public MovieInfo getResponseFromMovieInfo(Rating rating) {
        return webClientBuilder.build()
                .get()
                .uri("http://movie-info-mservice/movies/" + rating.getMovieId())
                .retrieve()
                .bodyToMono(MovieInfo.class)
                .block();
    }


}
