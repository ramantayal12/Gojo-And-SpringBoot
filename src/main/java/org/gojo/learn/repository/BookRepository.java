package org.gojo.learn.repository;

import org.gojo.learn.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, Long> {

  Book findByFirstName(String firstName);

  Book findByLastName(String lastName);
}
