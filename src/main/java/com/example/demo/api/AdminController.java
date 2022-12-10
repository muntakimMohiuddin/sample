package com.example.demo.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import  com.example.demo.service.*;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/admin")
public class AdminController {
    private final AdminService adminService;
    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    @GetMapping("/add_student")
    public boolean addStudent(){
    Student student=new Student("muntakim", "01983620416", 
    "muntakim54@gmail.com", UUID.randomUUID(), "CSE",null);
    System.out.println(student);
    return adminService.addStudent(student);
    }
    @GetMapping("/add_teacher")
    public boolean addTeacher(){
    Teacher teacher=new Teacher("teacher1", "01111111111", 
    "teacher1@gmail.com", UUID.randomUUID(), "CSE");
    System.out.println(teacher);
    return adminService.addTeacher(teacher);
    }
    @GetMapping("/deactivate_student/{id}")
    public boolean deactivateStudent(@PathVariable("id") UUID id){
    return adminService.deactivateStudent(id);
    }
    @GetMapping("/deactivate_teacher/{id}")
    public boolean deactivateTeacher(@PathVariable("id") UUID id){
    return adminService.deactivateTeacher(id);
    }
    @GetMapping("/get_student")
    public List<Student> getStudent(){
    return adminService.selectAllStudents();
    }
    @GetMapping("/get_teacher")
    public List<Teacher> getTeacher(){
    return adminService.selectAllTeachers();
    }
}
