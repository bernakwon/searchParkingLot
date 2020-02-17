package com.berna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SearchParkingLotApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SearchParkingLotApplication.class, args);
	}
	
	@Override 
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) { 
		return builder.sources(SearchParkingLotApplication.class);
	}


	@Bean
	public WebClientCustomizer webClientCustomizer() {
		return webClientBuilder ->  webClientBuilder.baseUrl("http://localhost:8080");
	}

}
