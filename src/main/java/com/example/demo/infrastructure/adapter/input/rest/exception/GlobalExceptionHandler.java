package com.example.demo.infrastructure.adapter.input.rest.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResponseStatusException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleGenericException(ResponseStatusException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getReason());

        logger.info("Business error - Code: {}, Reason: {}, message: {}",
                ex.getStatusCode(),
                ex.getReason(), ex.getMessage());

        return Mono.just(ResponseEntity.status(ex.getStatusCode()).body(errorResponse));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ErrorResponse>> handleGenericException(Exception ex) {

        logger.info("class error: {}", ex.getClass());
        logger.error(ex.getMessage(), ex);

        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());

        return Mono.just(ResponseEntity.status(500).body(errorResponse));
    }
}
