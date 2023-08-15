package com.dagoras.edu.api.mapper;

import com.dagoras.edu.api.domain.student.StudentCreateRequest;
import com.dagoras.edu.api.domain.student.StudentResponse;
import com.dagoras.edu.api.entity.Student;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class StudentMapper {
    public Student convertValue(StudentCreateRequest request) {
        // Kiểm tra trong đoạn giới thiệu có email không, nếu có thì tách ra và thêm vào cột email
        String emailPattern = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
        Pattern pattern = Pattern.compile(emailPattern);
        String email = "";

        if (request.getIntro() != null) {
            Matcher matcher = pattern.matcher(request.getIntro());
            while (matcher.find()) {
                email = matcher.group();
            }
        }

        Student student = new Student();
        student.setName(request.getName().trim());
        student.setAge(request.getAge());
        student.setPhoneNumber(request.getPhoneNumber());
        student.setDob(request.getDob());
        student.setEmail(request.getEmail());
        if (email != "") {
            student.setEmail(email);
        }
        student.setIntro(request.getIntro());
        student.setMajors(request.getMajors());
        return student;
    }

    public StudentResponse convertToResponse(Student student) {
        byte[] decryptPhoneNumber = Base64.getDecoder().decode(student.getPhoneNumber());
        String phoneNumber = new String(decryptPhoneNumber);

        StudentResponse res = new StudentResponse();
        res.setName(student.getName());
        res.setAge(student.getAge());
        res.setId(student.getId());
        res.setPhoneNumber(phoneNumber);
        res.setDob(student.getDob());
        res.setIntro(student.getIntro());
        res.setEmail(student.getEmail());
        res.setMajors(student.getMajors());
        return res;
    }
}
