package com.bridgelabz.fundoonoteapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FundooNoteAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FundooNoteAppApplication.class, args);
	}

}