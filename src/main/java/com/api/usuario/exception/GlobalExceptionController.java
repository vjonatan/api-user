package com.api.usuario.exception;

import com.api.usuario.exception.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerUserNotFoundException(UserNotFoundException exception) {
        String message = exception.getMessage();

        ApiResponse response = ApiResponse.builder()
                .message(message)
                .status(HttpStatus.NOT_FOUND)
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailFormatException.class)
    public ResponseEntity<ApiResponse> handlerEmailFormatIncorrectException(EmailFormatException exception) {
        String message = exception.getMessage();

        ApiResponse response = ApiResponse.builder()
                .message(message)
                .status(HttpStatus.BAD_REQUEST)
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailExistsException.class)
    public ResponseEntity<ApiResponse> handlerEmailExistsException(EmailExistsException exception) {
        String message = exception.getMessage();

        ApiResponse response = ApiResponse.builder()
                .message(message)
                .status(HttpStatus.CONFLICT)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

}
