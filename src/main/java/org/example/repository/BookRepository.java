package org.example.repository;

import org.example.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {

  public Book findByFirstName(String firstName);

  public Book findByLastName(String lastName);
}
