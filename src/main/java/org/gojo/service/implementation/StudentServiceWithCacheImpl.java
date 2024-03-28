package org.gojo.service.implementation;

import org.gojo.dto.StudentDto;
import org.gojo.exception.StudentAlreadyExistsException;
import org.gojo.exception.StudentNotFoundException;
import org.gojo.entity.StudentEntity;
import org.gojo.repository.StudentRepoWithRedis;
import org.gojo.service.StudentServiceWithCache;
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
  public StudentEntity saveStudent(StudentDto studentDto) {
    var existingEntity = studentRepoWithRedis.findById(studentDto.getId());

    // if entity already exists in db then don't insert it
    if(existingEntity.isPresent()){
      throw new StudentAlreadyExistsException("Student Already Exists in Db");
    }
    StudentEntity studentEntity = StudentEntity.builder()
        .id(studentDto.getId())
        .name(studentDto.getName())
        .contact(studentDto.getContact())
        .email(studentDto.getEmail())
        .build();
    return studentRepoWithRedis.save(studentEntity);
  }

  @Override
  public StudentEntity findStudentById(Long id) {
    return studentRepoWithRedis.findById(id)
        .orElseThrow(() -> new StudentNotFoundException("Student not found in DB"));
  }
}
