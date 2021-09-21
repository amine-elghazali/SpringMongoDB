package com.MongoSpringDemo.exception;

public class StudentException extends Exception{
    public StudentException(String message){
        super(message);
    }

    public static String NotFoundException(String id){
        return "Student with "+id+" not found !";
    }

    public static String StudentAlreadyExist(){
        return "student with given name already exist";
    }
}
