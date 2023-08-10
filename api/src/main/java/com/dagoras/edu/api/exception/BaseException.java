package com.dagoras.edu.api.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseException {
    private HttpStatus status;
    private Object message;
}
