package com.dsu.ipfspeermanager.exception.handler;

import com.dsu.ipfspeermanager.exception.response.ErrorResponse;
import com.dsu.ipfspeermanager.peergroup.exception.ForbiddenAccessException;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
        final IllegalArgumentException e
    ) {
        final HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(ErrorResponse.of(status, e.getMessage()));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchElementException(
        final NoSuchElementException e
    ) {
        final HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(ErrorResponse.of(status, e.getMessage()));
    }

    @ExceptionHandler(ForbiddenAccessException.class)
    public ResponseEntity<ErrorResponse> handleForbiddenAccessException(
        final ForbiddenAccessException e
    ) {
        final HttpStatus status = HttpStatus.FORBIDDEN;
        return ResponseEntity.status(status).body(ErrorResponse.of(status, e.getMessage()));
    }
}