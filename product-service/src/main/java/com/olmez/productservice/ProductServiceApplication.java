package com.olmez.productservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductServiceApplication {

	private Logger log = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) {
		System.setProperty("spring.config.location", "classpath:/application.yml");
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner init() {
		return args -> {

			log.info("Loading data");
			log.info("Product Service Application is running!");
		};
	}
}
