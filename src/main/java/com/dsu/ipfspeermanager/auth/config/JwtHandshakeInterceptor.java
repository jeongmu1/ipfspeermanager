package com.dsu.ipfspeermanager.auth.config;

import com.dsu.ipfspeermanager.auth.service.TokenService;
import java.util.Map;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class JwtHandshakeInterceptor extends HttpSessionHandshakeInterceptor {
    private final TokenService tokenService;

    @Override
    public boolean beforeHandshake(
        @NonNull final ServerHttpRequest request,
        @NonNull final ServerHttpResponse response,
        @NonNull final WebSocketHandler wsHandler,
        @NonNull final Map<String, Object> attributes
    ) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
            final String token = UriComponentsBuilder.fromUri(request.getURI()).build()
                    .getQueryParams().getFirst("token");

            if (StringUtils.hasText(token) && tokenService.validToken(token)) {
                // TODO 예외처리 구현
                Authentication authentication = tokenService.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                attributes.put("token", token);
                return true;
            }
        }

        return false;
    }

}
