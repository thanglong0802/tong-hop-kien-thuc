package com.dagoras.edu.api.scheduled;

import com.dagoras.edu.api.entity.Job;
import com.dagoras.edu.api.repository.JobRepository;
import com.dagoras.edu.api.repository.StudentRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class CountStudentUpdate {
    private final StudentRepository studentRepository;
    private final JobRepository jobRepository;
    private Job job = new Job();

    public CountStudentUpdate(StudentRepository studentRepository, JobRepository jobRepository) {
        this.studentRepository = studentRepository;
        this.jobRepository = jobRepository;
    }

    @Scheduled(cron = "0 0/2 * * * ?") // Mỗi 2 phút --- second, minute, hour, dayOfMonth, month, dayOfWeek
    public void countStudentUpdateInElasticSearch() {
        Integer countStudent = studentRepository.countStudentById();
        job.setCountStudent(countStudent);
        jobRepository.save(job);
    }
}
