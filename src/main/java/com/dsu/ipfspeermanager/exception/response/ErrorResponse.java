package com.dsu.ipfspeermanager.exception.response;


import org.springframework.http.HttpStatus;

public record ErrorResponse(int httpStatus, String message) {

    public static ErrorResponse of(final HttpStatus httpStatus, final String message) {
        return new ErrorResponse(httpStatus.value(), message);
    }
}
