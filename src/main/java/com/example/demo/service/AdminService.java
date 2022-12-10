package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AdminDataAccessService;
import com.example.demo.model.*;

@Service
public class AdminService {
    private final AdminDataAccessService adminDataAccessService;

    @Autowired
    public AdminService(AdminDataAccessService adminDataAccessService) {
        this.adminDataAccessService = adminDataAccessService;
    }

    public List<Student> selectAllStudents() {
        return adminDataAccessService.selectAllStudents();
    }

    public List<Teacher> selectAllTeachers() {
        return adminDataAccessService.selectAllTeachers();
    }

    public boolean deactivateStudent(UUID id) {
        return adminDataAccessService.deactivateStudent(id);
    }

    public boolean deactivateTeacher(UUID id) {
        return adminDataAccessService.deactivateTeacher(id);
    }

    public boolean addStudent(Student student) {
        return adminDataAccessService.addStudent(student);
    }

    public boolean addTeacher(Teacher teacher) {
        return adminDataAccessService.addTeacher(teacher);
    }
}
