package com.dsu.ipfspeermanager.auth.service;

import com.dsu.ipfspeermanager.auth.dto.request.LoginRequest;
import com.dsu.ipfspeermanager.auth.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenService tokenService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Transactional
    public TokenResponse processLogin(final LoginRequest dto) {
        final Authentication authentication = createAuthentication(dto);
        final String accessToken = tokenService.createAccessToken(authentication);
        return new TokenResponse(accessToken);
    }

    private Authentication createAuthentication(final LoginRequest dto) {
        final Authentication authToken =
            new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        final Authentication authentication =
            authenticationManagerBuilder.getObject().authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }
}
