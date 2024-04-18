package com.dsu.ipfspeermanager.user.service;

import com.dsu.ipfspeermanager.user.dto.request.UserRegistration;
import com.dsu.ipfspeermanager.user.exception.UsernameDuplicationException;
import com.dsu.ipfspeermanager.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void addUser(final UserRegistration dto) {
        if (userRepository.existsUserByUsername(dto.username())) {
            throw new UsernameDuplicationException("username이 중복되었습니다.");
        }

        userRepository.save(dto.toEntity());
    }
}
