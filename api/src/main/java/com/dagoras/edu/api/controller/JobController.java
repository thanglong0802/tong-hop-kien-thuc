package com.dagoras.edu.api.controller;

import com.dagoras.edu.api.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job")
@CrossOrigin(origins = "http://localhost:4200")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/student-quantity")
    public ResponseEntity<Integer> studentQuantity() {
        return ResponseEntity.ok().body(jobService.countStudentById());
    }

    @GetMapping("/teacher-quantity")
    public ResponseEntity<Integer> teacherQuantity() {
        return ResponseEntity.ok().body(jobService.countTeacherById());
    }

    @GetMapping("/{age}")
    public ResponseEntity<Integer> numberOfPeopleOfTheSameAge(@PathVariable Integer age) {
        return ResponseEntity.ok().body(jobService.numberOfPeopleOfTheSameAge(age));
    }

    @GetMapping
    public ResponseEntity<Integer> studyTheSameSubject(@RequestParam("subjectName") String subjectName) {
        return ResponseEntity.ok().body(jobService.studyTheSameSubject(subjectName));
    }
}
