package com.modi.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.modi.api.model.Book;
import com.modi.api.repository.BookCrudRepository;
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

	@Bean
	public CommandLineRunner initializeData(BookCrudRepository repository) {

		return (args) -> {
			// save a few Books
			repository.save(new Book(1L, "Book1"));
			repository.save(new Book(2L, "Book2"));
		};
	}

}
