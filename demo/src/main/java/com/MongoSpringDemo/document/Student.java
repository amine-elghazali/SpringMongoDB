package com.MongoSpringDemo.document;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "StudentsDocument")
public class Student {

    @Id
    private String id;

    @NotNull(message="First name cannot be null !")
    @NotBlank(message = "Enter a first name !")
    private String firstName;

    @NotNull(message="last name cannot be null !")
    @NotBlank(message = "Enter a last name !")
    private String lastName;
    private int age;
}
