package com.example.solicitudes.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements WebFilter {

    private static final String BEARER_PREFIX = "Bearer ";

    private final JwtService jwtService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String token = extraerToken(exchange.getRequest());
        if (token == null){
            return chain.filter(exchange);
        }
        try {
            Claims claims = jwtService.parsear(token);
            Authentication auth = construirAuthentication(claims);
            return chain.filter(exchange)
                    .contextWrite(ReactiveSecurityContextHolder.withAuthentication(auth));
        }catch (JwtException | IllegalArgumentException ex){
            return chain.filter(exchange);
        }
    }

    private String extraerToken(ServerHttpRequest request){
        String header = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith(BEARER_PREFIX)){
            return null;
        }
        return header.substring(BEARER_PREFIX.length());
    }

    private Authentication construirAuthentication(Claims claims){
        UUID userId = UUID.fromString(claims.getSubject());
        String rol = claims.get("rol", String.class);
        var authorities = List.of(new SimpleGrantedAuthority("ROLE_" + rol));
        return new UsernamePasswordAuthenticationToken(userId, null, authorities);
    }



}
