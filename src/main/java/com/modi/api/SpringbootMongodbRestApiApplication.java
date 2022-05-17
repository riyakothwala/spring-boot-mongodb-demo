package com.modi.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.modi.api.model.Book;
import com.modi.api.repository.BookCrudRepository;

@SpringBootApplication
public class SpringbootMongodbRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMongodbRestApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner initializeDatabase(BookCrudRepository repository) {

		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				// save a few Books
				repository.save(new Book("Book1"));
				repository.save(new Book("Book2"));
			}
		};
	}

}
