package com.example.solicitudes.domain.port.out;

import com.example.solicitudes.domain.model.EstadoSolicitud;
import com.example.solicitudes.domain.model.Solicitud;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface SolicitudRepositoryPort {

    Mono<Solicitud> guardar(Solicitud solicitud);

    Mono<Solicitud> buscarPorId(UUID id);

    Flux<Solicitud> buscarPorUsuario(UUID usuarioId);

    Flux<Solicitud> buscarPorEstado(EstadoSolicitud estado);

    Flux<Solicitud> buscarTodas();
}
