package com.mico.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mico.project.storage.StorageService;

@SpringBootApplication
public class MicoProjectApplication {

	private static final Logger logger = LoggerFactory.getLogger(MicoProjectApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(MicoProjectApplication.class, args);
	}
	
	@Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
        	logger.debug("@@@ debug test");
        	logger.info("@@@ info test");
//            storageService.deleteAll();
            storageService.init();
        };
    }
}
