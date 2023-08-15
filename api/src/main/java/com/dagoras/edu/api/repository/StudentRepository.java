package com.dagoras.edu.api.repository;

import com.dagoras.edu.api.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmail(String email);
    Student findByPhoneNumber(String phoneNumber);
    Page<Student> findAllByNameContaining(String textSearch, Pageable pageable);
}
