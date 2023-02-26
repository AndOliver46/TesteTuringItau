package com.andoliver46.testeItau.entities.exceptions;

public class ValueLimitExcpetion extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ValueLimitExcpetion(String msg) {
        super(msg);
    }
}
