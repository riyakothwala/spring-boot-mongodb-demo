package com.modi.api.service;

import com.modi.api.model.Book;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {

	Mono<Book> getBook(long id);

	Flux<Book> getAllBooks();

	Mono<Book> addBook(Book book);

}
