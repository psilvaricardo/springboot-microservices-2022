package com.microservicesws.service;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import reactor.netty.http.client.HttpClient;

@Slf4j
@Service
public class WebClientBean {

	// adding two seconds of timeout in milliseconds
	private int SECONDS = 2000;

	@Bean
	@LoadBalanced
	public WebClient.Builder WebClientBuilder() {
		log.info("MovieCatalogService WebClientBuilder() ...");

		return WebClient
				.builder()
				.clientConnector(new ReactorClientHttpConnector(HttpClient.create()
						.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, SECONDS)
						.doOnConnected(c ->
								c.addHandlerLast(new ReadTimeoutHandler(SECONDS))
								.addHandlerLast(new WriteTimeoutHandler(SECONDS)))))
				.defaultHeaders( httpHeaders -> {
					httpHeaders.add("Content-Type","application/json;charset=utf-8");
				});
	}

}
