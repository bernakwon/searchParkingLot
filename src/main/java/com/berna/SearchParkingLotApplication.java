package com.berna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SearchParkingLotApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SearchParkingLotApplication.class, args);
	}


	@Override 
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) { 
		return builder.sources(SearchParkingLotApplication.class);
	}

}
