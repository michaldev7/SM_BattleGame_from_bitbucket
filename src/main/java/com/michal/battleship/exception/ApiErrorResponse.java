package com.michal.battleship.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

/*
Custom exception handler for API responses
 */
@AllArgsConstructor
@Getter
public class ApiErrorResponse {

    private final String message;
    private final HttpStatus status;
    private final Instant timestamp;
}
