package com.modi.api.service;

import java.util.List;
import java.util.Optional;

import com.modi.api.model.Book;

public interface BookService {

	Optional<Book> getBook(String id);

	List<Book> getAllBooks();

	Book addBook(Book book);

	void deleteBook(String id);
}
