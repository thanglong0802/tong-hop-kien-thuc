package com.dagoras.edu.api.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BusinessException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private HttpStatus status;
    private String message;

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        this.message = message;
    }

    public BusinessException(HttpStatus codeStatus, String message) {
        this.status = codeStatus;
        this.message = message;
    }
}
