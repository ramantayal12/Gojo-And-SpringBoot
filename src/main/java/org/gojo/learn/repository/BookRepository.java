package org.gojo.learn.repository;

import org.gojo.learn.entity.BookEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<BookEntity, Long> {

  BookEntity findByFirstName(String firstName);

  BookEntity findByLastName(String lastName);
}
