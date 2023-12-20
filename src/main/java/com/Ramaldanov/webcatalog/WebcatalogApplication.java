package com.Ramaldanov.webcatalog;

import com.Ramaldanov.webcatalog.properties.StorageProperties;
import com.Ramaldanov.webcatalog.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class WebcatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebcatalogApplication.class, args);
	}

}
