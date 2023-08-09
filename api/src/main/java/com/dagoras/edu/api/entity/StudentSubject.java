package com.dagoras.edu.api.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "tbl_student_subject")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "student_id")
    private Long studentId;
    @Column(name = "subject_id")
    private Long subjectId;
}
