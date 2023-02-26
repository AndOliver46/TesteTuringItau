package com.andoliver46.testeItau.entities.exceptions;

public class InsuficientBalanceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InsuficientBalanceException(String msg) {
        super(msg);
    }
}
