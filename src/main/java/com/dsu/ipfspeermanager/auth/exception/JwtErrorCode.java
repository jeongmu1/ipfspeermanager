package com.dsu.ipfspeermanager.auth.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum JwtErrorCode {
    MALFORMED_TOKEN(UNAUTHORIZED, "잘못된 토큰 양식입니다."),
    EXPIRED_TOKEN(UNAUTHORIZED, "만료된 토큰입니다."),
    UNSUPPORTED_TOKEN(UNAUTHORIZED, "지원하지 않는 토큰 방식입니다."),
    SIGNATURE_ERROR(UNAUTHORIZED, "토큰 서명에 문제가 있습니다."),
    TOKEN_NOT_EXIST(UNAUTHORIZED, "토큰이 존재하지 않습니다."),
    UNKNOWN_ERROR(INTERNAL_SERVER_ERROR, "알 수 없는 오류");

    private final HttpStatus httpStatus;
    private final String message;
}
