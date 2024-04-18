package com.dsu.ipfspeermanager.auth.config;

import com.dsu.ipfspeermanager.auth.exception.JwtErrorCode;
import com.dsu.ipfspeermanager.auth.service.TokenService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String EXCEPTION_KEY = "exception";

    @Override
    protected void doFilterInternal(
        @NonNull final HttpServletRequest request,
        @NonNull final HttpServletResponse response,
        @NonNull final FilterChain filterChain) throws ServletException, IOException {
        final String token = getAccessToken(request);

        if (validToken(request, token)) {
            final Authentication authentication = tokenService.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private boolean validToken(final HttpServletRequest request, final String token) {
        if (!StringUtils.hasText(token)) {
            request.setAttribute(EXCEPTION_KEY, JwtErrorCode.TOKEN_NOT_EXIST);
        }

        try {
            tokenService.validToken(token);
            return true;
        } catch (MalformedJwtException e) {
            request.setAttribute(EXCEPTION_KEY, JwtErrorCode.MALFORMED_TOKEN);
        } catch (ExpiredJwtException e) {
            request.setAttribute(EXCEPTION_KEY, JwtErrorCode.EXPIRED_TOKEN);
        } catch (UnsupportedJwtException e) {
            request.setAttribute(EXCEPTION_KEY, JwtErrorCode.UNSUPPORTED_TOKEN);
        } catch (IllegalArgumentException e) {
            request.setAttribute(EXCEPTION_KEY, JwtErrorCode.TOKEN_NOT_EXIST);
        } catch (Exception e) {
            request.setAttribute(EXCEPTION_KEY, JwtErrorCode.UNKNOWN_ERROR);
        }

        return false;
    }

    private String getAccessToken(final HttpServletRequest request) {
        final String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
            return authorizationHeader.substring(TOKEN_PREFIX.length());
        }
        return request.getParameter("token");
    }
}
