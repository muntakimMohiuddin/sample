package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.model.Request;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TeacherDataAccessService implements TeacherDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TeacherDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public boolean takeActionOnRequest(UUID advisorId, UUID studentId, boolean accept) {
        String sql = "SELECT * from request where advisorId = \"" + advisorId.toString() + "\" and studentId= \""
                + studentId.toString() + "\" and resolved= false";
        List<Request> requests = jdbcTemplate.query(sql, mapRequestFomDb());
        if (requests.size() == 0) {
            return false;
        }
        Request request = (Request) requests.get(0);
        sql = "UPDATE request SET resolved=true where advisorId = \"" + advisorId.toString() + "\" and studentId = \""
                + request.studentId.toString()+"\"";
        jdbcTemplate.update(sql);
        if (accept) {
            sql = "UPDATE student SET advisorId=\"" + advisorId.toString() + "\" where id = \""
                    + request.studentId.toString() + "\"";
            return jdbcTemplate.update(sql) == 1;
        }
        return true;

    }

    @Override
    public List<Student> getAllStudents(UUID teacherId) {
        String sql = "" +
                "SELECT * FROM student " +
                "where advisorId= \"" + teacherId.toString() + "\"";

        return jdbcTemplate.query(sql, mapStudentFomDb());
    }

    @Override
    public boolean changePassword(UUID id, String oldPassword, String newPassword) {
        String sql = "" +
                "SELECT *" +
                "FROM teacher where id=\"" + id.toString() + "\"";

        Teacher teacher = jdbcTemplate.query(sql, mapTeachertWithPasswordFomDb()).stream().findFirst().orElse(null);
        if (teacher == null) {
            return false;
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (!bCryptPasswordEncoder.matches(oldPassword, teacher.getPassword())) {
            return false;
        }
        teacher.setPassword(newPassword);
        sql = "UPDATE teacher SET password = \"" + teacher.getPassword() + "\" WHERE id = \"" + id.toString() + "\"";
        return jdbcTemplate.update(sql) == 1;
    }

    @Override
    public boolean removeStudentFromAdvisorList(UUID advisorId, UUID studentId) {
        String sql = "UPDATE student SET advisorId= "+null+" where id = ? and advisorId = ?";
        System.out.println(advisorId+" "+studentId);
        return jdbcTemplate.update(sql,studentId.toString(),advisorId.toString()) == 1;
 
    }

    @Override
    public Teacher editProfile(Teacher teacher) {
        String sql = "UPDATE teacher SET name=?, email=?, phone=?, departmentName=? where id=?";
        int count=jdbcTemplate.update(sql,teacher.name,teacher.email,teacher.phone,teacher.departmentName,teacher.id.toString());
        System.out.println(count);
        return getTeacher(teacher.id);
    }

    @Override
    public Teacher getTeacher(UUID id) {
        String sql = "SELECT * FROM teacher where id= \"" + id.toString() + "\"";
        List<Teacher> teachers = jdbcTemplate.query(sql, mapTeacherFomDb());
        return teachers.stream().findFirst().orElse(null);
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
            return new Teacher(name, phone, email, id, departmentName,null);
        };
    }
    private RowMapper<Teacher> mapTeachertWithPasswordFomDb() {
        return (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String phone = resultSet.getString("phone");
            String departmentName = resultSet.getString("departmentName");
            String password =resultSet.getString("password");
            return new Teacher(name, phone, email, id, departmentName,password);
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
