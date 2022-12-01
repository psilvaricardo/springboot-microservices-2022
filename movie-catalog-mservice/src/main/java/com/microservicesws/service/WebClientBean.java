package com.microservicesws.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
public class WebClientBean {

	@Bean
	@LoadBalanced
	public WebClient.Builder WebClientBuilder() {
		log.info("MovieCatalogService WebClientBuilder() ...");

		return WebClient
				.builder()
				.defaultHeaders( httpHeaders -> {
					httpHeaders.add("Content-Type","application/json;charset=utf-8");
				});
	}

}
