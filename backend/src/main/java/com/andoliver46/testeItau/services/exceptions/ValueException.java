package com.andoliver46.testeItau.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValueException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ValueException(String msg) {
        super(msg);
    }

}
