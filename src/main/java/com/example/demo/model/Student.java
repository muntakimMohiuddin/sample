package com.example.demo.model;
import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.example.demo.validators.Patterns;

public class Student extends User {
    @NotEmpty(message="Department Name cannot be empty")
    public String departmentName;
    @Pattern(regexp = Patterns.UUID_PATTERN,message = "Advisor id has to be a UUID")
    public UUID advisorId;
    // public Student(String name, String phone,String email,UUID id,String departmentName) {
    //     super(name, phone, email,id);
    //     this.departmentName=departmentName;
    // }
    public Student(String name, String phone,String email,UUID id,String departmentName,UUID advisorID) {
        super(name, phone, email,id);
        this.departmentName=departmentName;
        this.advisorId=advisorID;
    }
    public Student(String name, String phone,String email,UUID id,String departmentName,UUID advisorID,String password) {
        super(name, phone, email, id, password);
        this.departmentName=departmentName;
        this.advisorId=advisorID;
    }

     
}
