package com.dsu.ipfspeermanager.auth.config;

import com.dsu.ipfspeermanager.auth.exception.JwtErrorCode;
import com.dsu.ipfspeermanager.exception.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final String EXCEPTION_KEY = "exception";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) throws IOException {
        final JwtErrorCode code = (JwtErrorCode) request.getAttribute(EXCEPTION_KEY);
        setResponse(response, code);
    }

    private void setResponse(
        final HttpServletResponse response,
        final JwtErrorCode errorCode) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(errorCode.getHttpStatus().value());

        final ErrorResponse errorResponse = ErrorResponse
            .of(errorCode.getHttpStatus(), errorCode.getMessage());
        final String result = new ObjectMapper().writeValueAsString(errorResponse);
        response.getWriter().write(result);
    }
}
