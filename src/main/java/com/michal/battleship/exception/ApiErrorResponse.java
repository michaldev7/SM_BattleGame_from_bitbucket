package com.michal.battleship.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

/*
Custom exception handler. Below properties will be visible in response for customer API.
 */
@AllArgsConstructor
@Getter
public class ApiErrorResponse {

    private String message;
    private HttpStatus status;
    private Instant timestamp;
}
