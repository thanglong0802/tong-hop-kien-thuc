package com.dagoras.edu.api.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tbl_teacher")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Teacher extends BaseEntity {
    private String name;
    private Integer age;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "date_of_birth")
    private Date dob;
    private String intro;
    private String email;
    @Column(name = "subject_id")
    private Long subjectId;
}
