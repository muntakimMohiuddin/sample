package com.example.demo.dao;

import java.util.List;
import java.util.UUID;

import com.example.demo.model.Student;

public interface StudentDao {
    public boolean makeRequest(UUID studentId, UUID advisorId);
    public boolean changePassword(UUID id, String oldPassword, String newPassword);
    public Student editProfile(Student student);
    public Student getStudent(UUID id);
}