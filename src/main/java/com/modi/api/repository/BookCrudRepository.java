package com.modi.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.modi.api.model.Book;

@Repository
public interface BookCrudRepository extends MongoRepository<Book, String> {

}
