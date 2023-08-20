package com.dagoras.edu.api.service;

public interface JobService {
    Integer countStudentById();
    Integer countTeacherById();
    Integer numberOfPeopleOfTheSameAge(Integer age);
    Integer studyTheSameSubject(String subjectName);
}
