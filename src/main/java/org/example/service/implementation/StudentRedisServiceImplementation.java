package org.example.service.implementation;

import org.example.models.Student;
import org.example.repository.StudentRedisRepository;
import org.example.service.StudentRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentRedisServiceImplementation implements StudentRedisService {

  private final StudentRedisRepository studentRedisRepository;

  @Autowired
  public StudentRedisServiceImplementation(StudentRedisRepository studentRedisRepository) {
    this.studentRedisRepository = studentRedisRepository;
  }

  @Override
  public Student saveUser(Student student) {
    return studentRedisRepository.save(student);
  }

  @Override
  public Student findUserById(Long studentId) throws Exception {
    return studentRedisRepository.findById(studentId)
        .orElseThrow(() -> new Exception("User doesn't exist in Redis Db"));
  }
}
