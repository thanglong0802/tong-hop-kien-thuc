package com.dagoras.edu.api.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "tbl_user")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name")
    private String userName;
    private String password;
    private String email;
    private String role;
}
