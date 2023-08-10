package com.dagoras.edu.api.domain.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class UserCreateAdminRequest extends UserCreateRequest {
    @NotBlank(message = "Role không được để trống")
    private String role;
}
