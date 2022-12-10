package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Student;
import com.example.demo.model.Teacher;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AdminDataAccessService implements AdminDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AdminDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Student> selectAllStudents() {
        String sql = "" +
                "SELECT *" +
                " FROM student";

        return jdbcTemplate.query(sql, mapStudentFomDb());
    }

    @Override
    public List<Teacher> selectAllTeachers() {
        String sql = "" +
                "SELECT *" +
                "FROM teacher";
        return jdbcTemplate.query(sql, mapTeacherFomDb());
    }

    @Override
    public boolean deactivateStudent(UUID id) {
        String sql = "SELECT * FROM student where id= \"" + id.toString()+"\"";
        List<Student> students = jdbcTemplate.query(sql, mapStudentFomDb());
        long count = students.stream().count();
        if (count == 0)
            return false;
        sql = "UPDATE student SET active=false where id= \"" + id.toString()+"\"";
        return jdbcTemplate.update(sql)==1;
       

    }

    @Override
    public boolean deactivateTeacher(UUID id) {
        String sql = "SELECT * FROM teacher where id= \"" + id.toString()+"\"";
        List<Teacher> teachers = jdbcTemplate.query(sql, mapTeacherFomDb());
        long count = teachers.stream().count();
        if (count == 0)
            return false;
        sql = "UPDATE teacher SET active=false where id= \"" + id.toString()+"\"";
        return jdbcTemplate.update(sql)==1;
    }

    private RowMapper<Student> mapStudentFomDb() {
        return (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String phone = resultSet.getString("phone");
            String departmentName = resultSet.getString("departmentName");
            String advisorIdStr = resultSet.getString("advisorId");
            UUID advisorId;
            if (advisorIdStr == null) {
                advisorId = null;
            } else {
                advisorId = UUID.fromString(advisorIdStr);
            }
            return new Student(name, phone, email, id, departmentName, advisorId,null);
        };
    }

    private RowMapper<Teacher> mapTeacherFomDb() {
        return (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String phone = resultSet.getString("phone");
            String departmentName = resultSet.getString("departmentName");
            return new Teacher(name, phone, email, id, departmentName);
        };
    }

    @Override
    public boolean addStudent(Student student) {
        String sql = "INSERT INTO student(id,name,email,phone,departmentName,password) VALUES (?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, student.id.toString(), student.name, student.email, student.phone,
                student.departmentName, student.password) == 1;
    }

    @Override
    public boolean addTeacher(Teacher teacher) {
        String sql = "INSERT INTO teacher(id,name,email,phone,departmentName,password) VALUES (?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, teacher.id.toString(), teacher.name, teacher.email, teacher.phone,
                teacher.departmentName, teacher.password) == 1;
    }
}
