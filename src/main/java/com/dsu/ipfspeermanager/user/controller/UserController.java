package com.dsu.ipfspeermanager.user.controller;

import static com.dsu.ipfspeermanager.global.util.HttpStatusResponseEntity.*;

import com.dsu.ipfspeermanager.user.dto.request.UserRegistration;
import com.dsu.ipfspeermanager.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userServices;

    @PostMapping
    public ResponseEntity<HttpStatus> createUser(@RequestBody final UserRegistration dto) {
        userServices.addUser(dto);
        return RESPONSE_CREATED;
    }
}
