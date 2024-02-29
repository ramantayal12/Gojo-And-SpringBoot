package org.gojo.controller;

import static org.gojo.constants.RedisConstants.CACHE_NAME;

import org.gojo.dto.StudentDto;
import org.gojo.entity.StudentEntity;
import org.gojo.serialisation.SerialisationUtil;
import org.gojo.service.StudentServiceWithCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // restController can be read by swagger but not controller
@RequestMapping(path = "/cache")
public class StudentRestController {

  private final StudentServiceWithCache studentServiceWithCache;

  @Autowired
  public StudentRestController(StudentServiceWithCache studentServiceWithCache) {
    this.studentServiceWithCache = studentServiceWithCache;
  }

  @PostMapping(path = "/save")
  // take StudentDto as @requestBody and not studentEntity
  public ResponseEntity<String> saveUser(@RequestBody StudentDto student)
      throws Exception {
    StudentEntity responseEntity = studentServiceWithCache.saveStudent(student);
    String response = SerialisationUtil.serialize(responseEntity);
    return ResponseEntity.ok(response);
  }

  /**
   * @Cacheable is employed to fetch data from the database, storing it in the cache. Upon future
   * invocations, the method retrieves the cached value directly, eliminating the need to execute
   * the method again.
   */
  @GetMapping(path = "/find/{id}")
  @Cacheable(value = CACHE_NAME, key = "#id")
  public ResponseEntity<String> findUser(@PathVariable Long id)
      throws Exception {
    StudentEntity responseEntity = studentServiceWithCache.findStudentById(id);
    String response = SerialisationUtil.serialize(responseEntity);
    return ResponseEntity.ok(response);
  }
}
