package com.dsu.ipfspeermanager.global.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpStatusResponseEntity {

    public static final ResponseEntity<HttpStatus> RESPONSE_OK =
        ResponseEntity.status(HttpStatus.OK).build();
    public static final ResponseEntity<HttpStatus> RESPONSE_CONFLICT =
        ResponseEntity.status(HttpStatus.CONFLICT).build();
    public static final ResponseEntity<HttpStatus> RESPONSE_BAD_REQUEST =
        ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    public static final ResponseEntity<HttpStatus> RESPONSE_NOT_FOUND =
        ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    public static final ResponseEntity<HttpStatus> RESPONSE_UNAUTHORIZED =
        ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    public static final ResponseEntity<HttpStatus> RESPONSE_FORBIDDEN =
        ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    public static final ResponseEntity<HttpStatus> RESPONSE_PAYLOAD_TOO_LARGE =
        ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).build();
    public static final ResponseEntity<HttpStatus> RESPONSE_NO_CONTENT =
        ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    public static final ResponseEntity<HttpStatus> RESPONSE_CREATED =
        ResponseEntity.status(HttpStatus.CREATED).build();
}