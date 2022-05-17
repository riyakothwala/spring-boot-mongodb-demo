package com.modi.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modi.api.model.Book;
import com.modi.api.service.BookService;

@RestController
@RequestMapping("book")
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping(value = "{id}")
	public ResponseEntity<Book> getBook(@PathVariable("id") String id) {
		Optional<Book> book = bookService.getBook(id);

		ResponseEntity<Book> response;

		if (book.isPresent()) {
			response = ResponseEntity.ok(book.get());
		} else {
			response = ResponseEntity.notFound().build();
		}

		return response;
	}

	@GetMapping
	public ResponseEntity<List<Book>> getAllBooks() {
		return ResponseEntity.ok(bookService.getAllBooks());
	}

	@PostMapping
	public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {

		String id = book.getId();
		if (id != null && !id.isBlank()) {
			Optional<Book> bookOptional = bookService.getBook(id);
			if (bookOptional.isPresent()) {
				return new ResponseEntity<Book>(HttpStatus.CONFLICT);
			}
		}

		return new ResponseEntity<Book>(bookService.addBook(book), HttpStatus.CREATED);
	}

	@PutMapping(value = "{id}")
	public ResponseEntity<Book> updateBook(@PathVariable String id, @Valid @RequestBody Book book) {
		Optional<Book> bookOptional = bookService.getBook(id);

		if (bookOptional.isPresent()) {
			Book bookMatch = bookOptional.get();
			bookMatch.setName(book.getName());
			bookService.addBook(bookMatch);
			return ResponseEntity.ok(bookMatch);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<Book> deleteBook(@PathVariable String id) {

		Optional<Book> bookOptional = bookService.getBook(id);

		if (bookOptional.isPresent()) {
			bookService.deleteBook(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
