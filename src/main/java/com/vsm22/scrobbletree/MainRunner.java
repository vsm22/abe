package com.vsm22.scrobbletree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MainRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(MainRunner.class, args);
	}
}
