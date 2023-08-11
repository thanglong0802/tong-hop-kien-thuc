package com.dagoras.edu.api.service;

import com.dagoras.edu.api.domain.PagingRequest;
import com.dagoras.edu.api.domain.student.StudentCreateRequest;
import com.dagoras.edu.api.domain.student.StudentResponse;
import com.dagoras.edu.api.domain.student.StudentUpdateRequest;
import com.dagoras.edu.api.entity.Student;
import org.springframework.data.domain.Page;

public interface StudentService {
    Page<StudentResponse> getAllOrSearch(PagingRequest request);
    Student create(StudentCreateRequest request);
    StudentResponse createEnvironmentDev(StudentCreateRequest request);
    Student update(StudentUpdateRequest request);
    StudentResponse findById(Long id);
    String delete(Long id);

}
