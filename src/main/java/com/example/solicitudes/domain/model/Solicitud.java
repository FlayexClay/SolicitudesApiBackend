package com.example.solicitudes.domain.model;

import com.example.solicitudes.domain.exception.EstadoInvalidoException;

import java.time.LocalDateTime;
import java.util.UUID;

public record Solicitud(
        UUID id,
        String descripcion,
        EstadoSolicitud estado,
        UUID usuarioId,
        UUID validadorId,
        String comentario,
        LocalDateTime createdAt,
        LocalDateTime validatedAt
) {

    public static Solicitud crear(String descripcion, UUID usuarioId){
        return new Solicitud(
                UUID.randomUUID(),
                descripcion,
                EstadoSolicitud.PENDIENTE,
                usuarioId,
                null,
                null,
                LocalDateTime.now(),
                null
        );
    }

    public Solicitud aprobar(UUID adminId, String comentario){
        validarTransicionDesdePendiente();
        return new Solicitud(
                id, descripcion, EstadoSolicitud.APROBADA, usuarioId,
                adminId, comentario, createdAt, LocalDateTime.now()
        );
    }

    public Solicitud rechazar(UUID adminId, String comentario){
        validarTransicionDesdePendiente();
        return new Solicitud(
                id, descripcion, EstadoSolicitud.RECHAZADA, usuarioId,
                adminId, comentario, createdAt, LocalDateTime.now()
        );
    }

    private void validarTransicionDesdePendiente(){
        if (estado != EstadoSolicitud.PENDIENTE) {
            throw new EstadoInvalidoException(
                    "La solicitud ya fue procesada (estado actual: " + estado + ")"
            );
        }
    }

    public boolean perteneceA(UUID userId){
        return usuarioId.equals(userId);
    }
}
