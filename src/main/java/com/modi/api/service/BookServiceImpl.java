package com.modi.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modi.api.model.Book;
import com.modi.api.repository.BookCrudRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookCrudRepository bookRepository;

	@Override
	public Mono<Book> getBook(long id) {
		return bookRepository.findById(id);
	}

	@Override
	public Flux<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public Mono<Book> addBook(Book book) {
		return bookRepository.save(book);
	}

}
