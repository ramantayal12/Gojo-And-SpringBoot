package org.gojo.learn.controller.database;

import static org.gojo.learn.constants.RedisConstants.CACHE_NAME;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.gojo.learn.dto.StudentDto;
import org.gojo.learn.entity.StudentEntity;
import org.gojo.learn.exception.BaseGojoException;
import org.gojo.learn.serialisation.SerialisationUtil;
import org.gojo.learn.service.StudentServiceWithCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // restController can be read by swagger but not controller
@RequestMapping(path = "/cache")
public class StudentRestController {

  private final StudentServiceWithCache studentServiceWithCache;

  @Autowired
  public StudentRestController(StudentServiceWithCache studentServiceWithCache) {
    this.studentServiceWithCache = studentServiceWithCache;
  }

  @PostMapping(path = "/save")
  public ResponseEntity<String> saveUser(@RequestBody StudentDto student)
      throws BaseGojoException, JsonProcessingException {

    StudentEntity responseEntity = studentServiceWithCache.saveStudent(student);
    String response = SerialisationUtil.serialize(responseEntity);

    return ResponseEntity.ok(response);

  }

  /**
   * @Cacheable is employed to fetch data from the database, storing it in the cache. Upon future
   * invocations, the method retrieves the cached value directly, eliminating the need to execute
   * the method again.
   */
  @GetMapping(path = "/find-by-id/{id}")
  @Cacheable(value = CACHE_NAME, key = "#id")
  public ResponseEntity<String> findUser(@RequestParam Long id)
      throws BaseGojoException, JsonProcessingException {

    StudentEntity responseEntity = studentServiceWithCache.findStudentById(id);
    String response = SerialisationUtil.serialize(responseEntity);
    return ResponseEntity.ok(response);

  }
}
