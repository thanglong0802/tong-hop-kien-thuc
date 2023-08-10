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
public class User extends BaseEntity {
    @Column(name = "user_name")
    private String username;
    private String password;
    private String email;
    private String role;
}
