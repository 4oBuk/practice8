package com.chornobuk.practice8.pepapi;

import com.chornobuk.practice8.pepapi.services.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

// for uploading files was used code from https://github.com/spring-guides/gs-uploading-files/tree/main

@SpringBootApplication
public class PepApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PepApiApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}
}
