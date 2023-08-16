package com.charot.dhwebflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class DhWebfluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(DhWebfluxApplication.class, args);
	}

}
