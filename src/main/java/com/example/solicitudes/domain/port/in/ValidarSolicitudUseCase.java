package com.example.solicitudes.domain.port.in;

import com.example.solicitudes.domain.model.Solicitud;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ValidarSolicitudUseCase {
    enum Decision { APROBAR, RECHAZAR }

    Mono<Solicitud> ejecutar(UUID solicitudId, UUID adminId, Decision decision, String comentario);
}
