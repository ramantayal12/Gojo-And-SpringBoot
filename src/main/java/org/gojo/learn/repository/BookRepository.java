package org.gojo.learn.repository;

import org.gojo.learn.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, Long> {

  public Book findByFirstName(String firstName);
  public Book findByLastName(String lastName);
}
