package org.example.service;

import org.example.models.Student;

public interface StudentRedisService {

  public Student saveUser(Student student);
  public Student findUserById(Long userId) throws Exception;
}
