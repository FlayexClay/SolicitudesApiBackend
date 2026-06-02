package com.example.solicitudes.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public record Usuario(UUID id, String username, String password, Rol rol, LocalDateTime createdAt) {

    public static Usuario nuevo(String username, String passwordHasheado, Rol rol){
        return new Usuario(
                UUID.randomUUID(),
                username,
                passwordHasheado,
                rol,
                LocalDateTime.now()
        );
    }
}
