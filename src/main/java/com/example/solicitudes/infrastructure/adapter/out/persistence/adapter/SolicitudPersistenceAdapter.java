package com.example.solicitudes.infrastructure.adapter.out.persistence.adapter;

import com.example.solicitudes.domain.model.EstadoSolicitud;
import com.example.solicitudes.domain.model.Solicitud;
import com.example.solicitudes.domain.port.out.SolicitudRepositoryPort;
import com.example.solicitudes.infrastructure.adapter.out.persistence.entity.SolicitudEntity;
import com.example.solicitudes.infrastructure.adapter.out.persistence.mapper.SolicitudPersistenceMapper;
import com.example.solicitudes.infrastructure.adapter.out.persistence.repository.SolicitudR2dbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SolicitudPersistenceAdapter implements SolicitudRepositoryPort {

    private final SolicitudR2dbcRepository repository;
    private final R2dbcEntityTemplate template;

    @Override
    public Mono<Solicitud> guardar(Solicitud solicitud){
        SolicitudEntity entity =
                SolicitudPersistenceMapper.toEntity(solicitud);
        return repository.existsById(entity.getId())
                .flatMap(existe -> existe
                        ? template.update(entity)
                        : template.insert(entity))
                .map(SolicitudPersistenceMapper::toDomain);
    }

    @Override
    public Mono<Solicitud> buscarPorId(UUID id){
        return repository.findById(id).map(SolicitudPersistenceMapper::toDomain);
    }

    @Override
    public Flux<Solicitud> buscarPorUsuario(UUID usuarioId){
        return repository.findByUsuarioId(usuarioId).map(SolicitudPersistenceMapper::toDomain);
    }

    @Override
    public Flux<Solicitud> buscarPorEstado(EstadoSolicitud estado){
        return repository.findByEstado(estado.name()).map(SolicitudPersistenceMapper::toDomain);
    }

    @Override
    public Flux<Solicitud> buscarTodas(){
        return repository.findAll().map(SolicitudPersistenceMapper::toDomain);
    }
}
