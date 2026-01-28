package com.florianbardin.vitisapi.exception;

import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ProblemDetail> handleResponseStatusException(ResponseStatusException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                e.getStatusCode(), e.getReason()
        );
        problemDetail.setProperty("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(problemDetail, e.getStatusCode());
    }
}
