package com.example.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.example.userservice.repository")
public class UserServiceApplication {
	//TODO set up env for mongo connection
	//TODO move from mongo repo to mongo client
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
