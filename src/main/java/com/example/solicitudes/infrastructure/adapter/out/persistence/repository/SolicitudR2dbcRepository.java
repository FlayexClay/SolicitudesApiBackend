package com.example.solicitudes.infrastructure.adapter.out.persistence.repository;

import com.example.solicitudes.infrastructure.adapter.out.persistence.entity.SolicitudEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface SolicitudR2dbcRepository extends ReactiveCrudRepository<SolicitudEntity, UUID> {
    Flux<SolicitudEntity> findByUsuarioId(UUID usuarioId);
    Flux<SolicitudEntity> findByEstado(String estado);
}
