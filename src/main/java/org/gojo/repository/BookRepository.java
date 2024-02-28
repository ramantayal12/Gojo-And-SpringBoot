package org.gojo.repository;

import org.gojo.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {

  public Book findByFirstName(String firstName);
  public Book findByLastName(String lastName);
}
