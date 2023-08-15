package com.dagoras.edu.api.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class PagingRequest {
    @NotNull(message = "Page Size không  được để trống")
    private int pageSize;
    @NotNull(message = "Current Page không được để trống")
    private int currentPage;
    private String textSearch;
}
