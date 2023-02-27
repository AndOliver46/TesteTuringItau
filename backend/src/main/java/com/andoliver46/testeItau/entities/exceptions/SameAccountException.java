package com.andoliver46.testeItau.entities.exceptions;

public class SameAccountException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public SameAccountException(String msg) {
        super(msg);
    }
}
