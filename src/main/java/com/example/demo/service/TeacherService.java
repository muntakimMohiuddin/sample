package com.example.demo.service;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TeacherDataAccessService;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;

@Service
public class TeacherService {
    private final TeacherDataAccessService teacherDataAccessService;
    @Autowired
    public TeacherService(TeacherDataAccessService teacherDataAccessService) {
        this.teacherDataAccessService = teacherDataAccessService;
    }
    public boolean takeActionOnRequest(UUID id, UUID studentId,boolean accept){
        return teacherDataAccessService.takeActionOnRequest(id, studentId, accept);
    }
    public List<Student> getAllStudents(UUID teacherId){
        return teacherDataAccessService.getAllStudents(teacherId);

}
    public boolean changePassword(UUID id, String oldPassword, String newPassword){
        return teacherDataAccessService.changePassword(id, oldPassword, newPassword);
}
    public boolean removeStudentFromAdvisorList(UUID teacherId,UUID studentId){
        return teacherDataAccessService.removeStudentFromAdvisorList(teacherId, studentId);
}
    public Teacher editProfile(Teacher teacher){
        return teacherDataAccessService.editProfile(teacher);
}
    public Teacher getTeacher(UUID id){
        return teacherDataAccessService.getTeacher(id);
}
}
