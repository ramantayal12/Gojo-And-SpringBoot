package org.gojo.learn.service.service;

import org.gojo.learn.service.dto.StudentDto;
import org.gojo.learn.service.entity.StudentEntity;
import org.gojo.learn.service.exception.StudentAlreadyExistsException;
import org.gojo.learn.service.exception.StudentNotFoundException;

public interface StudentServiceWithCache {

  StudentEntity saveStudent(StudentDto studentDto) throws StudentAlreadyExistsException;

  StudentEntity findStudentById(Long id) throws StudentNotFoundException;

}
