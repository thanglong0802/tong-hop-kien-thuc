package com.dagoras.edu.api.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagingRequest {
    private int pageSize;
    private int currentPage;
    private String textSearch;
}
