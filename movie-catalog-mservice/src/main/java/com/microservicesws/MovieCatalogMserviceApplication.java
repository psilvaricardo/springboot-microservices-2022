package com.microservicesws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MovieCatalogMserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogMserviceApplication.class, args);
	}

}
