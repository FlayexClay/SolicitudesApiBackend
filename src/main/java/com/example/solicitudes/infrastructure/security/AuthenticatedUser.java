package com.example.solicitudes.infrastructure.security;

import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class AuthenticatedUser {
    private AuthenticatedUser() {}

    public static Mono<UUID> currentUserId(){
        return ReactiveSecurityContextHolder.getContext()
                .map(ctx -> ctx.getAuthentication().getPrincipal())
                .cast(UUID.class);
    }
}
