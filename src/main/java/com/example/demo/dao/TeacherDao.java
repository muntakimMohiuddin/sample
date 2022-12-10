package com.example.demo.dao;

import java.util.List;
import java.util.UUID;

import com.example.demo.model.Student;
import com.example.demo.model.Teacher;

public interface TeacherDao {
    public boolean takeActionOnRequest(UUID id, UUID studentId,boolean accept);
    public List<Student> getAllStudents(UUID teacherId);
    public boolean changePassword(UUID id, String oldPassword, String newPassword);
    public boolean removeStudentFromAdvisorList(UUID teacherId,UUID studentId);
    public Teacher editProfile(Teacher teacher);
    public Teacher getTeacher(UUID id);

}
