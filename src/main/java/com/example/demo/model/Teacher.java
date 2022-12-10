package com.example.demo.model;

import java.util.UUID;

import javax.validation.constraints.NotEmpty;

public class Teacher extends User {
    @NotEmpty(message = "Department Name cannot be empty")
    public String departmentName;

    public Teacher(String name, String phone,
            String email, UUID id, String departmentName) {
        super(name, phone, email, id);
        this.departmentName = departmentName;
    }

    public Teacher(String name, String phone,
            String email, UUID id, String departmentName, String password) {
        super(name, phone, email, id, password);
        this.departmentName = departmentName;
    }

}
