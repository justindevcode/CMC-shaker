package com.example.Heunduljang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableFeignClients
@SpringBootApplication
public class HeunduljangApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeunduljangApplication.class, args);
	}

}
