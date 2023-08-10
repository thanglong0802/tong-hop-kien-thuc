package com.dagoras.edu.api.domain.student;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@ToString
public class StudentCreateRequest {
    @NotBlank(message = "Tên không được để trống")
    private String name;
    @NotNull(message = "Tuổi không được để trống")
    private Integer age;
    @NotBlank(message = "Số điện thoại không được để trống")
    private String phoneNumber;
    private Date dob;
    @NotBlank(message = "Giới thiệu không được để trống")
    @Size(min = 10, message = "Giới thiệu tối thiểu phải 10 ký tự")
    private String intro;
    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    private String email;
    private String majors;
}
