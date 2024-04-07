package org.gojo.learn.repository;

import org.gojo.learn.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepoWithRedis extends JpaRepository<StudentEntity, Long> {

}
