package org.gojo.learn.service.service.implementation;

import org.gojo.learn.service.dto.StudentDto;
import org.gojo.learn.service.entity.StudentEntity;
import org.gojo.learn.service.exception.StudentAlreadyExistsException;
import org.gojo.learn.service.exception.StudentNotFoundException;
import org.gojo.learn.service.repository.StudentRepoWithRedis;
import org.gojo.learn.service.service.StudentServiceWithCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceWithCacheImpl implements StudentServiceWithCache {

  private final StudentRepoWithRedis studentRepoWithRedis;

  @Autowired
  public StudentServiceWithCacheImpl(StudentRepoWithRedis studentRepoWithRedis) {
    this.studentRepoWithRedis = studentRepoWithRedis;
  }

  @Override
  public StudentEntity saveStudent(StudentDto studentDto) throws StudentAlreadyExistsException {
    var existingEntity = studentRepoWithRedis.findById(studentDto.getSid());

    // if entity already exists in db then don't insert it
    if (existingEntity.isPresent()) {
      throw new StudentAlreadyExistsException();
    }
    StudentEntity studentEntity = StudentEntity.builder()
        .name(studentDto.getName())
        .contact(studentDto.getContact())
        .email(studentDto.getEmail())
        .build();
    return studentRepoWithRedis.save(studentEntity);
  }

  @Override
  public StudentEntity findStudentById(Long id) throws StudentNotFoundException {
    return studentRepoWithRedis.findById(id)
        .orElseThrow(StudentNotFoundException::new);
  }
}
