package com.microservicesws.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientBean {

    @Autowired
	WebClient.Builder webClientBuilder;

	@Bean
	public WebClient getWebClient() {
		return webClientBuilder.build();
	}

}