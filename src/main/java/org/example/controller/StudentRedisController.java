package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.models.Student;
import org.example.serialisation.SerialisationUtil;
import org.example.service.StudentRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/redis")
public class StudentRedisController {

  private final StudentRedisService studentRedisService;

  @Autowired
  public StudentRedisController(StudentRedisService studentRedisService) {
    this.studentRedisService = studentRedisService;
  }

  @PostMapping(path = "/save")
  public ResponseEntity<String> saveUser(@RequestBody Student student)
      throws JsonProcessingException {
    Student responseEntity = studentRedisService.saveUser(student);
    String response = SerialisationUtil.serialize(responseEntity);
    return ResponseEntity.ok(response);
  }

  /**
   * @Cacheable is employed to fetch data from the database, storing it in the cache. Upon future
   * invocations, the method retrieves the cached value directly, eliminating the need to execute
   * the method again.
   */
  @GetMapping(path = "/find/{studentId}")
  @Cacheable(value = "product", key = "#studentId")
  public ResponseEntity<String> findUser(@RequestParam("studentId") Long studentId)
      throws Exception {
    Student responseEntity = studentRedisService.findUserById(studentId);
    String response = SerialisationUtil.serialize(responseEntity);
    return ResponseEntity.ok(response);
  }
}
