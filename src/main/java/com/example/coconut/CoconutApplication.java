package com.example.coconut;

import com.example.coconut.domain.category.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import com.example.coconut.domain.category.entity.Category;

@EnableJpaAuditing
@SpringBootApplication
public class CoconutApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoconutApplication.class, args);
	}

}
