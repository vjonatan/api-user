package com.api.usuario.exception;

public class EmailExistsException extends RuntimeException {
    public EmailExistsException(String message){
      super(message);
    }
}
