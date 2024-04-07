package org.gojo.learn.service;

import org.gojo.learn.dto.StudentDto;
import org.gojo.learn.entity.StudentEntity;
import org.gojo.learn.exception.StudentAlreadyExistsException;
import org.gojo.learn.exception.StudentNotFoundException;

public interface StudentServiceWithCache {

  StudentEntity saveStudent(StudentDto studentDto) throws StudentAlreadyExistsException;

  StudentEntity findStudentById(Long id) throws StudentNotFoundException;

}
