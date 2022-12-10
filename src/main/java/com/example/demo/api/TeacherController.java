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
@RequestMapping("api/teacher")
public class TeacherController { 
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
    @GetMapping("/me")
    public Teacher getCurrentUser() {
        return teacherService.getTeacher(UUID.fromString("482375cd-cbf1-43bd-8f4a-dd040743f361"));
    }
    @GetMapping("/students")
    public List<Student> getStudents() {
        return teacherService.getAllStudents(UUID.fromString("482375cd-cbf1-43bd-8f4a-dd040743f361"));
    }

    @GetMapping("/request")
    public boolean makeRequest() {
        return teacherService.takeActionOnRequest(UUID.fromString("482375cd-cbf1-43bd-8f4a-dd040743f361"),UUID.fromString("82fc1800-d199-48a3-b70d-7ed3e7eb5915"),
                true);
    }

    @GetMapping("/edit_profile")
    public Teacher editProfile() {
        Teacher teacher=new Teacher("edited_teacher_name", "01983620420","teacher1_edited@gmail.com",UUID.fromString("482375cd-cbf1-43bd-8f4a-dd040743f361"),"editted_department");
        return teacherService.editProfile(teacher);
    }
    @GetMapping("/remove_student/{id}")
    public boolean removeStudent(@PathVariable("id") String  studentId) {
        return teacherService.removeStudentFromAdvisorList(UUID.fromString("482375cd-cbf1-43bd-8f4a-dd040743f361"),UUID.fromString(studentId));
    }
    @GetMapping("/change_password")
    public boolean changePassword() {
        return teacherService.changePassword(UUID.fromString("482375cd-cbf1-43bd-8f4a-dd040743f361"),"01111111111","01111111112");
    }
}
