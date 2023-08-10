package com.dagoras.edu.api.domain.student;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class StudentResponse {
    private Long id;
    private String name;
    private Integer age;
    private String phoneNumber;
    private Date dob;
    private String intro;
    private String email;
    private String majors;
}
