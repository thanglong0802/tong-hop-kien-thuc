package com.dagoras.edu.api.service;

import com.dagoras.edu.api.domain.PagingRequest;
import com.dagoras.edu.api.domain.student.StudentCreateRequest;
import com.dagoras.edu.api.domain.student.StudentResponse;
import com.dagoras.edu.api.domain.student.StudentUpdateRequest;
import com.dagoras.edu.api.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {
    List<Student> findAll();
    Page<Student> getAllOrSearch(PagingRequest request);
    List<Student> findByUserName(String username);
    Student create(StudentCreateRequest request);
    StudentResponse createEnvironmentDev(StudentCreateRequest request);
    Student update(StudentUpdateRequest request);
    Student findById(Long id);
    Boolean delete(Long id);

}
