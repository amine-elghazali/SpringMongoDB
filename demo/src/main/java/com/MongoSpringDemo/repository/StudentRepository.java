package com.MongoSpringDemo.repository;

import com.MongoSpringDemo.document.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends MongoRepository<Student,String> {

    @Query("{'firstName':?0}")
    Optional<Student> findByFirstName(String firstName);

    /*@Query("{'age':0?}")
    Optional<List<Student>> findByAge(int age);*/

   /* @Query("{'firstName':0?}")
    Optional<List<Student>> findByFirstName(String firstName);*/
}
