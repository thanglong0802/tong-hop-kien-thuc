package com.dagoras.edu.api.controller;

import com.dagoras.edu.api.domain.PagingRequest;
import com.dagoras.edu.api.domain.student.StudentCreateRequest;
import com.dagoras.edu.api.domain.student.StudentResponse;
import com.dagoras.edu.api.domain.student.StudentUpdateRequest;
import com.dagoras.edu.api.entity.Student;
import com.dagoras.edu.api.service.StudentService;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        return ResponseEntity.ok().body(studentService.findAll());
    }

    @PostMapping("/search")
    public ResponseEntity<Page<Student>> getAllOrSearch(@RequestBody @Valid PagingRequest request) {
        return ResponseEntity.ok().body(studentService.getAllOrSearch(request));
    }

    @PostMapping
    @Profile("local")
    public ResponseEntity<Student> create(@RequestBody @Valid StudentCreateRequest request) {
        return ResponseEntity.ok().body(studentService.create(request));
    }

    @PostMapping("/dev")
    @Profile("dev")
    public ResponseEntity<StudentResponse> createEnvironmentDev(@RequestBody @Valid StudentCreateRequest request) {
        return ResponseEntity.ok().body(studentService.createEnvironmentDev(request));
    }

    @PutMapping
    public ResponseEntity<Student> update(@RequestBody @Valid StudentUpdateRequest request) {
        return ResponseEntity.ok().body(studentService.update(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(studentService.findById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(studentService.delete(id));
    }
}
