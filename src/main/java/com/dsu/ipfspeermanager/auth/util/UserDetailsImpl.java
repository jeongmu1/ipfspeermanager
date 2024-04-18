package com.dsu.ipfspeermanager.auth.util;

import com.dsu.ipfspeermanager.user.domain.User;
import java.util.Collection;
import java.util.HashSet;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private final transient User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final HashSet<GrantedAuthority> authorities = new HashSet<>();
        authorities.add((GrantedAuthority) () -> "ROLE_USER");
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
