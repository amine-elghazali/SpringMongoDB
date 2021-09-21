package com.MongoSpringDemo.service;

import com.MongoSpringDemo.document.Student;
import com.MongoSpringDemo.exception.StudentException;
import com.MongoSpringDemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }



    @Override
    public void addStudent(Student student) throws ConstraintViolationException,StudentException {
        Optional<Student> studentOptional = studentRepository.findByFirstName(student.getFirstName());

        if(studentOptional.isPresent()){
            throw new StudentException(StudentException.StudentAlreadyExist());
        }
        else{
            studentRepository.save(student);
        }

    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = studentRepository.findAll();

        if(students.size()>0){
            return students;
        }
        else{
            return new ArrayList<Student>();
        }
    }

    @Override
    public Student getOneStudent(String id) throws StudentException{
        Optional<Student> student = studentRepository.findById(id);

        if(!student.isPresent()){
            throw new StudentException(StudentException.NotFoundException(id));
        }
        else {
            return student.get();
        }
    }

    @Override
    public void deleteStudent(String id) throws StudentException {
        Optional<Student> stundentToDelete = studentRepository.findById(id);
        if(stundentToDelete.isPresent()){
            studentRepository.deleteById(id);
        }
        else{
            throw new StudentException(StudentException.NotFoundException(id));
        }
    }

    @Override
    public void updateStudent(Student student) throws ConstraintViolationException,StudentException {
            studentRepository.save(student);
    }
}
