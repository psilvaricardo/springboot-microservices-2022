package com.microservicesws.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientBean {

	@Bean
	@LoadBalanced
	public WebClient.Builder loadBalancedWebClientBuilder() {
		// https://stackoverflow.com/questions/72569019/java-net-unknownhostexception-failed-to-resolve-inventory-service-after-4-que
		return WebClient.builder();
	}

}
