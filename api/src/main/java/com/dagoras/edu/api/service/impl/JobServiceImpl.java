package com.dagoras.edu.api.service.impl;

import com.dagoras.edu.api.repository.StudentRepository;
import com.dagoras.edu.api.service.JobService;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {
    private final StudentRepository studentRepository;

    public JobServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Integer countStudentById() {
        return studentRepository.countStudentById();
    }

    @Override
    public Integer countTeacherById() {
        return studentRepository.countTeacherById();
    }

    @Override
    public Integer numberOfPeopleOfTheSameAge(Integer age) {
        return studentRepository.countStudentByAge(age);
    }

    @Override
    public Integer studyTheSameSubject(String subjectName) {
        return studentRepository.studyTheSameSubject(subjectName);
    }
}
