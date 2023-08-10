package com.dagoras.edu.api.domain.student;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class StudentUpdateRequest extends StudentCreateRequest {
    @NotNull
    private Long id;
}
