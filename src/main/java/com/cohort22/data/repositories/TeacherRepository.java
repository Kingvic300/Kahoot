package com.cohort22.data.repositories;

import com.cohort22.data.models.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends MongoRepository<Teacher, String> {
    Optional<Teacher> findByUsername(String username);
    List<Teacher> findAllByUsername(String username);

}