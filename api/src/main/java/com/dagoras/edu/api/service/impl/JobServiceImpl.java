package com.dagoras.edu.api.service.impl;

import com.dagoras.edu.api.entity.Job;
import com.dagoras.edu.api.repository.JobRepository;
import com.dagoras.edu.api.repository.StudentRepository;
import com.dagoras.edu.api.service.JobService;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {
    private final StudentRepository studentRepository;
    private final JobRepository jobRepository;
    private Job job = new Job();

    public JobServiceImpl(StudentRepository studentRepository, JobRepository jobRepository) {
        this.studentRepository = studentRepository;
        this.jobRepository = jobRepository;
    }

    @Override
    public Integer countStudentById() {
        Integer countStudent = studentRepository.countStudentById();
        job.setCountStudent(countStudent);
        jobRepository.save(job);
        return countStudent;
    }

    @Override
    public Integer countTeacherById() {
        Integer countTeacher = studentRepository.countTeacherById();
        job.setCountTeacher(countTeacher);
        jobRepository.save(job);
        return countTeacher;
    }

    @Override
    public Integer numberOfPeopleOfTheSameAge(Integer age) {
        Integer numberOfPeopleOfTheSameAge = studentRepository.countStudentByAge(age);
        job.setNumberOfPeopleOfTheSameAge(numberOfPeopleOfTheSameAge);
        jobRepository.save(job);
        return numberOfPeopleOfTheSameAge;
    }

    @Override
    public Integer studyTheSameSubject(String subjectName) {
        Integer studyTheSameSubject = studentRepository.studyTheSameSubject(subjectName);
        job.setStudyTheSameSubject(studyTheSameSubject);
        jobRepository.save(job);
        return studyTheSameSubject;
    }
}
