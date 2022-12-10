package com.example.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Request;
import com.example.demo.model.Student;
import com.example.demo.service.*;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/me")
    public Student getCurrentUser() {
        return studentService.getStudent(UUID.fromString("82fc1800-d199-48a3-b70d-7ed3e7eb5915"));
    }

    @PostMapping("/request")
    public boolean makeRequest(@RequestBody @Valid Request request) {
        System.out.println(request.toString());
        return true;
        // return studentService.makeRequest(request.studentId,
        //         request.advisorId);
    }

    @GetMapping("/edit_profile")
    public Student editProfile() {
        Student student=new Student("edited_name", "01983620418","muntakimm54@gmail.com",UUID.fromString("82fc1800-d199-48a3-b70d-7ed3e7eb5915"),"editted_department",null);
        return studentService.editProfile(student);
    }
    @GetMapping("/change_password")
    public boolean changePassword() {
        return studentService.changePassword(UUID.fromString("82fc1800-d199-48a3-b70d-7ed3e7eb5915"),"01983620416","01983620417");
    }
}