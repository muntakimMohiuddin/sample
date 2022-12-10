package com.example.demo.dao;

import java.util.List;
import java.util.UUID;

import com.example.demo.model.*;

public interface AdminDao {
    public List<Student> selectAllStudents();
    public List<Teacher> selectAllTeachers();
    public boolean deactivateStudent(UUID id);
    public boolean deactivateTeacher(UUID id);
    public boolean addStudent(Student student);
    public boolean addTeacher(Teacher teacher);


}
