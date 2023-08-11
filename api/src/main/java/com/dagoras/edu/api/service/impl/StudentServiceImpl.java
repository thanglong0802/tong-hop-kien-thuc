package com.dagoras.edu.api.service.impl;

import com.dagoras.edu.api.domain.PagingRequest;
import com.dagoras.edu.api.domain.student.StudentCreateRequest;
import com.dagoras.edu.api.domain.student.StudentResponse;
import com.dagoras.edu.api.domain.student.StudentUpdateRequest;
import com.dagoras.edu.api.entity.Student;
import com.dagoras.edu.api.exception.BusinessException;
import com.dagoras.edu.api.mapper.StudentMapper;
import com.dagoras.edu.api.repository.StudentRepository;
import com.dagoras.edu.api.service.StudentService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public Page<StudentResponse> getAllOrSearch(PagingRequest request) {
        return null;
    }

    @Override
    public Student create(StudentCreateRequest request) {
        // Kiểm tra email và phoneNumber đã tồn tại chưa
        checkEmailExists(request.getEmail());
        checkPhoneNumberExists(request.getPhoneNumber());

        Student student = studentMapper.convertValue(request);
        student.setCreateDate(LocalDateTime.now());
        student.setIsDelete(false);
        studentRepository.save(student);
        return student;
    }

    @Override
    public StudentResponse createEnvironmentDev(StudentCreateRequest request) {
        checkEmailExists(request.getEmail());
        checkPhoneNumberEncodeExists(request.getPhoneNumber());

        String encryptPhoneNumber = Base64.getEncoder().encodeToString(request.getPhoneNumber().getBytes());

        Student student = new Student();
        student.setName(request.getName());
        student.setAge(request.getAge());
        student.setPhoneNumber(encryptPhoneNumber);
        student.setDob(request.getDob());
        student.setIntro(request.getIntro());
        student.setEmail(request.getEmail());
        student.setMajors(request.getMajors());
        student.setCreateDate(LocalDateTime.now());
        student.setIsDelete(false);
        studentRepository.save(student);
        return studentMapper.convertToResponse(student);
    }

    @Override
    public Student update(StudentUpdateRequest request) {
        Student student = getStudentById(request.getId());
        student.setAge(request.getAge());
        student.setName(request.getName());
        student.setPhoneNumber(request.getPhoneNumber());
        student.setDob(request.getDob());
        student.setIntro(request.getIntro());
        student.setEmail(request.getEmail());
        student.setMajors(request.getMajors());
        student.setLastUpdate(LocalDateTime.now());
        studentRepository.save(student);
        return student;
    }

    @Override
    public StudentResponse findById(Long id) {
        Student student = getStudentById(id);
        return studentMapper.convertToResponse(student);
    }

    @Override
    public String delete(Long id) {
        Student student = getStudentById(id);
        student.setIsDelete(true);
        studentRepository.save(student);
        return "Xóa thành công";
    }

    private Student getStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (!student.isPresent()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Student ID " + id + " Not Found");
        }
        return student.get();
    }

    private boolean checkEmailExists(String email) {
        Student st;
        st = studentRepository.findByEmail(email);
        if (!ObjectUtils.allNull(st)) {
            throw new BusinessException(HttpStatus.CONFLICT, "Email đã tồn tại");
        }
        return true;
    }

    private boolean checkPhoneNumberExists(String phoneNumber) {
        Student st = studentRepository.findByPhoneNumber(phoneNumber);
        if (!ObjectUtils.allNull(st)) {
            throw new BusinessException(HttpStatus.CONFLICT, "Số điện thoại đã tồn tại");
        }
        return true;
    }

    private boolean checkPhoneNumberEncodeExists(String phoneNumber) {
        Student st;
        String encryptPhoneNumber = Base64.getEncoder().encodeToString(phoneNumber.getBytes());
        st = studentRepository.findByPhoneNumber(encryptPhoneNumber);
        if (!ObjectUtils.allNull(st)) {
            throw new BusinessException(HttpStatus.CONFLICT, "Số điện thoại đã tồn tại");
        }
        return true;
    }
}
