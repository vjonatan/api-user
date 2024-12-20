package com.api.usuario.exception;

public class EmailFormatException extends RuntimeException {
    public EmailFormatException(String message) {
        super(message);
    }
}
