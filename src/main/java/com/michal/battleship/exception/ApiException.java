package com.michal.battleship.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

public class ApiException extends RuntimeException {

    private final HttpStatus status;

    public ApiException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    /*
     * I verified sonar hints, but BindingResult will never be null and first line need to call super,
     * so I decided to write own exceptions in below way
     */
    public ApiException(HttpStatus status, BindingResult error) {
        super(error.getAllErrors().stream().findFirst().get().getDefaultMessage());
        this.status = status;
    }

    public HttpStatus getStatus() {
        return this.status;
    }
}
