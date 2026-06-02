package com.example.solicitudes.infrastructure.adapter.out.persistence.repository;

import com.example.solicitudes.infrastructure.adapter.out.persistence.entity.UsuarioEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UsuarioR2dbcRepository extends ReactiveCrudRepository<UsuarioEntity, UUID> {

    Mono<UsuarioEntity> findByUsername(String username);

    Mono<Boolean> existsByUsername(String username);
}
