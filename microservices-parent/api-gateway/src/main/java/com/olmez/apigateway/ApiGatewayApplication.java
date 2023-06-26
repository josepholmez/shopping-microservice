package com.olmez.apigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.olmez.apigateway.utility.SourceUtils;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

	private Logger log = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) {
		SourceUtils.setSpringConfigLocation();
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	CommandLineRunner loadData() {
		return args -> {
			log.info("Loading data");
			log.info("Api Gateway Application is running!");
		};
	}

}