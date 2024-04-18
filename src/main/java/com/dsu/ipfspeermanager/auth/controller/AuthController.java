package com.dsu.ipfspeermanager.auth.controller;

import com.dsu.ipfspeermanager.auth.dto.request.LoginRequest;
import com.dsu.ipfspeermanager.auth.dto.response.TokenResponse;
import com.dsu.ipfspeermanager.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auths")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> processLogin(
        @RequestBody final LoginRequest dto
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.processLogin(dto));
    }
}
