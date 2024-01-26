package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import org.example.models.Book;
import org.example.repository.BookRepository;
import org.example.repository.UserRepository;
import org.example.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// use REST-CONTROLLER so that it can be picked up by swagger3
// controller doesn't get picked by swagger
@RestController // This means that this class is a Controller
@RequestMapping(path = "/db") // This means URL's start with /db (after Application path)
public class DbController {

  private final UserRepository userRepository;
  private final BookRepository bookRepository;
  private final ObjectMapper objectMapper;

  /**
   * This means to get the bean called userRepository Which is auto-generated by Spring, we will use
   * it to handle the data
   */
  @Autowired
  public DbController(UserRepository userRepository, BookRepository bookRepository) {
    this.userRepository = userRepository;
    this.bookRepository = bookRepository;
    objectMapper = new ObjectMapper();

  }

  @PostMapping(path = "/user/add") // Map ONLY POST Requests
  public ResponseEntity<User> addNewUser(
      @RequestBody User user
  ) {
    userRepository.save(user);
    return ResponseEntity.ok(user);
  }

  @GetMapping(path = "/user/get/{userId}")
  public ResponseEntity<User> getUser(@PathVariable("userId") Long userId) {
    Optional<User> user = userRepository.findById(userId);
    return ResponseEntity.ok(user.orElse(null));
  }

  // Note : direct return type like User/String was not working but it was working
  // when wrapped inside ResponseEntity
  @GetMapping(path = "/user/getAllUsers")
  public ResponseEntity<String> getAllUsers() throws JsonProcessingException {
    return ResponseEntity.ok(objectMapper.writeValueAsString(userRepository.findAll()));
  }

  @PostMapping(path = "/book/add")
  public ResponseEntity<Book> addNewBook(
      @RequestBody Book book
  ){
    return ResponseEntity.ok(bookRepository.save(book));
  }

  @GetMapping(path = "/book/{lastName}")
  public ResponseEntity<Book> getBookByLastName(@RequestParam("lastName") String lastName) {
    return ResponseEntity.ok(bookRepository.findByLastName(lastName));
  }
}
