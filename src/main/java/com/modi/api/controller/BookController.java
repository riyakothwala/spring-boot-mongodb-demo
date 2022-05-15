package com.modi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modi.api.model.Book;
import com.modi.api.service.BookService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("book")
public class BookController {

	// TODO: fix return type to be ResponseEntity

	@Autowired
	private BookService bookService;

	@GetMapping(value = "{id}")
	public Mono<Book> getBook(@PathVariable("id") long id) {
		return bookService.getBook(id);
	}

	@GetMapping
	public Flux<Book> getAllEmployee() {
		return bookService.getAllBooks();
	}

	@PostMapping
	public Mono<Book> addBook(Book book) {
		return bookService.addBook(book);
	}
}
