package com.example.datanuri_board;

import com.example.datanuri_board.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
@EnableJpaAuditing
public class DatanuriBoardApplication {
	public static void main(String[] args) {
		SpringApplication.run(DatanuriBoardApplication.class, args);
		System.out.println(org.springframework.core.SpringVersion.getVersion());
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
