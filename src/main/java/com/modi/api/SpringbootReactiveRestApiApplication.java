package com.modi.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.mongodb.reactivestreams.client.MongoClient;

@SpringBootApplication
public class SpringbootReactiveRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootReactiveRestApiApplication.class, args);
	}

	@Autowired
	MongoClient mongoClient;

	@Bean
	public ReactiveMongoTemplate reactiveMongoTemplate() {
		return new ReactiveMongoTemplate(mongoClient, "bookdb");
	}

}
