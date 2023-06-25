package com.olmez.productservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.olmez.productservice.utility.SourceUtils;

@SpringBootApplication
public class ProductServiceApplication {

	private Logger log = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) {
		SourceUtils.setSpringConfigLocation();
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner loadData() {
		return args -> {

			log.info("Loading data");
			log.info("Product Service Application is running!");
		};
	}

}