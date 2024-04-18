package com.dsu.ipfspeermanager.auth.service;

import com.dsu.ipfspeermanager.auth.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private static final String AUTHORITIES_KEY = "authorities";
    private final JwtProperties jwtProperties;
    private final Key key;

    public TokenService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecretKey()));
    }

    public String createAccessToken(final Authentication authentication) {
        final Date now = new Date();
        final Date expirationDate = new Date(
            now.getTime() + jwtProperties.getAccessTokenExpiration());
        final Set<String> authorities = authentication.getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toSet());

        return Jwts.builder()
            .setSubject(authentication.getName())
            .setIssuedAt(now)
            .setExpiration(expirationDate)
            .claim(AUTHORITIES_KEY, authorities)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    public Authentication getAuthentication(final String token) {
        final Claims claims = getClaims(token);
        final String authoritiesStr = claims.get(AUTHORITIES_KEY).toString()
            .replaceAll("[\\[\\]\\s]", "");
        final Collection<? extends GrantedAuthority> authorities =
            Arrays.stream(authoritiesStr.split(","))
                .map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
        final var user = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(user, token, authorities);
    }

    public boolean validToken(final String token) {
        getClaims(token);
        return true;
    }

    private Claims getClaims(final String token) {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
}
