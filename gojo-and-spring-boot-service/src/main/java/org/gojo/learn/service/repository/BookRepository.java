package org.gojo.learn.service.repository;

import org.gojo.learn.service.entity.BookEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<BookEntity, Long> {

  BookEntity findByFirstName(String firstName);

  BookEntity findByLastName(String lastName);
}
