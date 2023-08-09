package com.dagoras.edu.api.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "tbl_subject")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subject extends BaseEntity {
    private String name;
}
