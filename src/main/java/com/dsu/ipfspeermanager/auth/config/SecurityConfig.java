package com.dsu.ipfspeermanager.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CorsFilter corsFilter;
    private final AccessDeniedHandlerImpl accessDeniedHandler;
    private final TokenAuthenticationFilter tokenAuthenticationFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
            .addFilterBefore(corsFilter, CorsFilter.class)
            .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .headers(headers -> headers
                .frameOptions(config -> config.sameOrigin().disable()))
            .exceptionHandling(e -> {
                e.accessDeniedHandler(accessDeniedHandler);
                e.authenticationEntryPoint(jwtAuthenticationEntryPoint);
            })
            .sessionManagement(e -> e.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(registry -> registry
                .requestMatchers(HttpMethod.POST, "/users", "/auths/login").permitAll()
                .requestMatchers("/", "/**").authenticated()
            )
            .csrf(AbstractHttpConfigurer::disable)
            .getOrBuild();
    }
}
