package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Request;
import com.example.demo.model.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class StudentDataAccessService implements StudentDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean makeRequest(UUID studentId, UUID advisorId) {
        String sql = "INSERT INTO request VALUES (?,?,?)";
        int count = jdbcTemplate.update(sql,studentId.toString() , advisorId.toString(),false);
        return count==1;
    }

    @Override
    public boolean changePassword(UUID id, String oldPassword, String newPassword) {
        String sql = "" +
                "SELECT *" +
                "FROM student where id=\""+id.toString()+"\"";

        Student student = jdbcTemplate.query(sql, mapStudentWithPasswordFomDb()).stream().findFirst().orElse(null);
        if (student == null) {
            return false;
        }
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        if (!bCryptPasswordEncoder.matches(oldPassword, student.getPassword())) {
            return false;
        }
        student.setPassword(newPassword);
        sql = "UPDATE student SET password = \""+student.getPassword()+"\" WHERE id = \"" + id.toString()+"\"";
        return jdbcTemplate.update(sql)==1;
    }

    @Override
    public Student editProfile(Student student) {
        String sql = "UPDATE student SET name=?, email=?, phone=?, departmentName=? where id=?";
        jdbcTemplate.update(sql,student.name,student.email,student.phone,student.departmentName,student.id.toString());
        return getStudent(student.id);
    }

    @Override
    public Student getStudent(UUID id) {
        String sql = "SELECT * FROM student where id= \"" + id.toString()+"\"";
        List<Student> students = jdbcTemplate.query(sql, mapStudentFomDb());
        return students.stream().findFirst().orElse(null);

    }

    private RowMapper<Student> mapStudentWithPasswordFomDb() {
        return (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String phone = resultSet.getString("phone");
            String departmentName = resultSet.getString("departmentName");
            String password=resultSet.getString("password");
            String advisorIdStr = resultSet.getString("advisorId");
            UUID advisorId;
            if (advisorIdStr == null) {
                advisorId = null;
            } else {
                advisorId = UUID.fromString(advisorIdStr);
            }
            Student student= new Student(name, phone, email, id, departmentName, advisorId, password);
            return student;


        };
    }

    private RowMapper<Student> mapStudentFomDb() {
        return (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String phone = resultSet.getString("phone");
            String departmentName = resultSet.getString("departmentName");
            String password=null;
            String advisorIdStr = resultSet.getString("advisorId");
            UUID advisorId;
            if (advisorIdStr == null) {
                advisorId = null;
            } else {
                advisorId = UUID.fromString(advisorIdStr);
            }
            return new Student(name, phone, email, id, departmentName, advisorId, password);

        };
    }

    private RowMapper<Request> mapRequestFomDb() {
        return (resultSet, i) -> {
            UUID advisorId = UUID.fromString(resultSet.getString("advisorId"));
            UUID studentId = UUID.fromString(resultSet.getString("studentId"));
            boolean resolved = resultSet.getBoolean("resolved");
            return new Request(studentId, advisorId, resolved);
        };
    }
}
