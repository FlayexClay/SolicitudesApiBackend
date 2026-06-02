package com.example.solicitudes.infrastructure.adapter.out.persistence.mapper;

import com.example.solicitudes.domain.model.EstadoSolicitud;
import com.example.solicitudes.domain.model.Solicitud;
import com.example.solicitudes.infrastructure.adapter.out.persistence.entity.SolicitudEntity;

public final class SolicitudPersistenceMapper {

    private SolicitudPersistenceMapper() {}

    public static SolicitudEntity toEntity(Solicitud solicitud){
        return new SolicitudEntity(
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

    public static Solicitud toDomain(SolicitudEntity entity){
        return new Solicitud(
                entity.getId(),
                entity.getDescripcion(),
                EstadoSolicitud.valueOf(entity.getEstado()),
                entity.getUsuarioId(),
                entity.getValidadorId(),
                entity.getComentario(),
                entity.getCreatedAt(),
                entity.getValidatedAt()
        );
    }
}
