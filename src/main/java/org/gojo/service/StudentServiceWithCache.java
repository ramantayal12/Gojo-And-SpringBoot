package org.gojo.service;

import org.gojo.dto.StudentDto;
import org.gojo.entity.StudentEntity;

public interface StudentServiceWithCache {

  StudentEntity saveStudent(StudentDto studentDto);

  StudentEntity findStudentById(Long id);

}
