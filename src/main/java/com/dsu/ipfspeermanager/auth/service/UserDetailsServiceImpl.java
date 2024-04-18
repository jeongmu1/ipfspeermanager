package com.dsu.ipfspeermanager.auth.service;

import com.dsu.ipfspeermanager.auth.util.UserDetailsImpl;
import com.dsu.ipfspeermanager.user.domain.User;
import com.dsu.ipfspeermanager.user.repository.UserRepository;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new NoSuchElementException("해당 user를 찾을 수 없습니다 : " + username));
        return new UserDetailsImpl(user);
    }
}
