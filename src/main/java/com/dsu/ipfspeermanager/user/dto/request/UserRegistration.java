package com.dsu.ipfspeermanager.user.dto.request;

import com.dsu.ipfspeermanager.user.domain.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UserRegistration(
    @NotBlank
    @Min(5)
    String username,
    @NotBlank
    @Min(5)
    String password
) {

    public User toEntity() {
        return User.builder()
            .username(username)
            .password(password)
            .build();
    }
}
