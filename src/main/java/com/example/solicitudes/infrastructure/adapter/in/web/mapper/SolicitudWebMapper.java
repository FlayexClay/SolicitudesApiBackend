package com.example.solicitudes.infrastructure.adapter.in.web.mapper;

import com.example.solicitudes.domain.model.Solicitud;
import com.example.solicitudes.infrastructure.adapter.in.web.dto.SolicitudResponse;

public final class SolicitudWebMapper {

    private SolicitudWebMapper() {}

    public static SolicitudResponse toResponse(Solicitud solicitud){
        return new SolicitudResponse(
                solicitud.id(),
                solicitud.descripcion(),
                solicitud.estado().name(),
                solicitud.usuarioId(),
                solicitud.validadorId(),
                solicitud.comentario(),
                solicitud.createdAt(),
                solicitud.validatedAt()
        );
    }
}
