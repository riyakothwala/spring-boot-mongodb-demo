package com.modi.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modi.api.model.Book;
import com.modi.api.repository.BookCrudRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookCrudRepository bookRepository;

	@Override
	public Optional<Book> getBook(String id) {
		return bookRepository.findById(id);
	}

	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public Book addBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public void deleteBook(String id) {
		bookRepository.deleteById(id);
	}

}
