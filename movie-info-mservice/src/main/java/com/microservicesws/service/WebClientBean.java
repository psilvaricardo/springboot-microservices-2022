package com.microservicesws.service;

import com.microservicesws.util.MovieInfoConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
public class WebClientBean {

    private final WebClient webClient;

    @Autowired
    public WebClientBean(WebClient.Builder builder) {
        log.info("MovieInfoService WebClientBean Ctor...");
        String access_token = MovieInfoConstants.getValueOf("access_token");

        this.webClient =
                builder
                .baseUrl("https://api.themoviedb.org")
                .defaultHeaders( httpHeaders -> {
                            httpHeaders.add("Authorization","Bearer "+access_token);
                        }
                )
                .build();

    }

    @Bean
    @LoadBalanced
    public WebClient loadBalancedWebClientBuilder() { return this.webClient; }

}
