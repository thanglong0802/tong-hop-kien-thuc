package com.dagoras.edu.api.mapper;

import com.dagoras.edu.api.domain.student.StudentCreateRequest;
import com.dagoras.edu.api.domain.student.StudentResponse;
import com.dagoras.edu.api.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    public Student convertValue(StudentCreateRequest request) {
        Student student = new Student();
        student.setName(request.getName());
        student.setAge(request.getAge());
        student.setPhoneNumber(request.getPhoneNumber());
        student.setDob(request.getDob());
        student.setIntro(request.getIntro());
        student.setEmail(request.getEmail());
        student.setMajors(request.getMajors());
        return student;
    }

    public StudentResponse convertToResponse(Student student) {
        StudentResponse res = new StudentResponse();
        res.setName(student.getName());
        res.setAge(student.getAge());
        res.setId(student.getId());
        res.setPhoneNumber(student.getPhoneNumber());
        res.setDob(student.getDob());
        res.setIntro(student.getIntro());
        res.setEmail(student.getEmail());
        res.setMajors(student.getMajors());
        return res;
    }
}
