package com.olmez.discoveryserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;

import com.olmez.discoveryserver.utility.SourceUtils;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServerApplication {

	private Logger log = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) {
		SourceUtils.setSpringConfigLocation();
		SpringApplication.run(DiscoveryServerApplication.class, args);
	}

	@Bean
	CommandLineRunner loadData() {
		return args -> {
			log.info("Loading data");
			log.info("Discovery Server Application is running!");
		};
	}

}