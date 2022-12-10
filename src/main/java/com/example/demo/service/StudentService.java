package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.dao.StudentDataAccessService;
import com.example.demo.model.Student;

@Service
public class StudentService {
    private final StudentDataAccessService studentDataAccessService;

    @Autowired
    public StudentService(StudentDataAccessService studentDataAccessService) {
        this.studentDataAccessService = studentDataAccessService;
    }

    public boolean makeRequest(UUID studentId, UUID advisorId) {
        return studentDataAccessService.makeRequest(studentId, advisorId);
    }

    public boolean changePassword(UUID id, String oldPassword, String newPassword) {
        return studentDataAccessService.changePassword(id, oldPassword, newPassword);
    }

    public Student editProfile(Student student) {
        return studentDataAccessService.editProfile(student);
    }

    public Student getStudent(UUID id) {
        return studentDataAccessService.getStudent(id);
    }
}
