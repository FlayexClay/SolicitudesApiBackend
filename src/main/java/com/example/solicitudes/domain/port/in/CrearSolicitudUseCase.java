package com.example.solicitudes.domain.port.in;

import com.example.solicitudes.domain.model.Solicitud;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CrearSolicitudUseCase {

    Mono<Solicitud> ejecutar(String descripccion, UUID usuarioId);
}
