package com.modi.api.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.modi.api.model.Book;

@Repository
public interface BookCrudRepository extends ReactiveCrudRepository<Book, Long> {

}
