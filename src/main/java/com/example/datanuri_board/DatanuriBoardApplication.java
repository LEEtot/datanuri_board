package com.example.datanuri_board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DatanuriBoardApplication {
	public static void main(String[] args) {
		SpringApplication.run(DatanuriBoardApplication.class, args);
	}
}
