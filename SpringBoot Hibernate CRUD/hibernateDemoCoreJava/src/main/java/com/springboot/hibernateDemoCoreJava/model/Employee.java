package com.springboot.hibernateDemoCoreJava.model;

import jakarta.persistence.*;
import lombok.Data;

@Data  //Please see its declaration
@Entity //This is Jpa Annotation, specifies that the class is an entity
@Table(name = "employees")
public class Employee {

    @Id //Specifies the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "email", nullable = false)
    private String email;
}
