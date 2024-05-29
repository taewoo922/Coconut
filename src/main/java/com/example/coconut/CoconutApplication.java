package com.example.coconut;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CoconutApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoconutApplication.class, args);
	}

}
