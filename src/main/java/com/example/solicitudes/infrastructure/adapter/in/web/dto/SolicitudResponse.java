package com.example.solicitudes.infrastructure.adapter.in.web.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record SolicitudResponse(
        UUID id,
        String descripcion,
        String estado,
        UUID usuarioId,
        UUID validadorId,
        String comentario,
        LocalDateTime createdAt,
        LocalDateTime validatedAt
)
{}
