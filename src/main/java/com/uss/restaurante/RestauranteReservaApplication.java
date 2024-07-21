package com.uss.restaurante;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RestauranteReservaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestauranteReservaApplication.class, args);
	}

}
