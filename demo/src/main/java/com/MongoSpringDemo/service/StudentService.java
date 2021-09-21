package com.MongoSpringDemo.service;

import com.MongoSpringDemo.document.Student;
import com.MongoSpringDemo.exception.StudentException;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

public interface StudentService {

    public void addStudent(Student student) throws StudentException, ConstraintViolationException;

    public List<Student> getAllStudents();

    public Student getOneStudent(String id) throws StudentException;

    public void deleteStudent(String id) throws StudentException;

    void updateStudent(Student student) throws ConstraintViolationException,StudentException;
}
