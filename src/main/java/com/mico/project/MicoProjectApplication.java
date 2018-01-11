package com.mico.project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mico.project.storage.StorageService;

@SpringBootApplication
public class MicoProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicoProjectApplication.class, args);
	}
	
	@Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
//            storageService.deleteAll();
            storageService.init();
        };
    }
}
