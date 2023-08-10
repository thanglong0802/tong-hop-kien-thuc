package com.dagoras.edu.api.controller;

import com.dagoras.edu.api.domain.student.StudentCreateRequest;
import com.dagoras.edu.api.domain.student.StudentResponse;
import com.dagoras.edu.api.domain.student.StudentUpdateRequest;
import com.dagoras.edu.api.entity.Student;
import com.dagoras.edu.api.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody @Valid StudentCreateRequest request) {
        return ResponseEntity.ok().body(studentService.create(request));
    }

    @PutMapping
    public ResponseEntity<Student> update(@RequestBody @Valid StudentUpdateRequest request) {
        return ResponseEntity.ok().body(studentService.update(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(studentService.findById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(studentService.delete(id));
    }
}
