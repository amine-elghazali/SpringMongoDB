package com.MongoSpringDemo.controller;


import com.MongoSpringDemo.document.Student;
import com.MongoSpringDemo.exception.StudentException;
import com.MongoSpringDemo.repository.StudentRepository;
import com.MongoSpringDemo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllStudents(){
        List<Student> students = studentService.getAllStudents();
            return new ResponseEntity<>(students, students.size()>0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    /*
     @GetMapping("/allByAge/{age}")

     public ResponseEntity<?> getAllByAgeStudents(@PathVariable("age") int age){
         Optional<List<Student>> students = studentRepository.findByAge(age);
         if(students.isPresent())
             return new ResponseEntity<>(students,HttpStatus.OK);
         else
             return new ResponseEntity<>("Students not available !",HttpStatus.NOT_FOUND);


     @GetMapping("/allByfn/{firstName}")
     public ResponseEntity<?> getAllByAgeStudents(@PathVariable("firstName") String firstName){
         Optional<List<Student>> students = studentRepository.findByFirstName(firstName);
         if(students.isPresent())
             return new ResponseEntity<>(students,HttpStatus.OK);
         else
             return new ResponseEntity<>("Students not available !",HttpStatus.NOT_FOUND);
     }
 */
    @PostMapping("/add")
    public ResponseEntity<Student> addStudent(@RequestBody Student student){

        try {
            studentService.addStudent(student);
            return new ResponseEntity<Student>(student,HttpStatus.OK);
        }
        catch(ConstraintViolationException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch(StudentException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @GetMapping("get/{id}")
    public ResponseEntity<?> getOneStudent(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(studentService.getOneStudent(id),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student){
        try {
            studentService.updateStudent(student);
            return new ResponseEntity<Student>(student,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") String id){
        try {
            studentService.deleteStudent(id);
            return new ResponseEntity<>("id :"+id+"successfully deleted ",HttpStatus.OK);
        }
        catch(StudentException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
