package com.dagoras.edu.api.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String message;

    public MessageException() {
        super();
    }

    public MessageException(String msg) {
        message = msg;
    }

}
