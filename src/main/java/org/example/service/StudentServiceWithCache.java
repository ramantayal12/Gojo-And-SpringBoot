package org.example.service;

import org.example.dto.StudentDto;
import org.example.entity.StudentEntity;

public interface StudentServiceWithCache {

  StudentEntity saveStudent(StudentDto studentDto);
  StudentEntity findStudentById(Long id);

}
