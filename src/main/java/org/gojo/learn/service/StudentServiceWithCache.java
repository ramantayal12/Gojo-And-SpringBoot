package org.gojo.learn.service;

import org.gojo.learn.dto.StudentDto;
import org.gojo.learn.entity.StudentEntity;

public interface StudentServiceWithCache {

  StudentEntity saveStudent(StudentDto studentDto);
  StudentEntity findStudentById(Long id);

}
