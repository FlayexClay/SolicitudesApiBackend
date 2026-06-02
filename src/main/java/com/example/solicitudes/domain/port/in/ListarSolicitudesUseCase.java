package com.example.solicitudes.domain.port.in;

import com.example.solicitudes.domain.model.Solicitud;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface ListarSolicitudesUseCase {

    Flux<Solicitud> listarMias(UUID usuarioId);

    Flux<Solicitud> listarPendientes();

    Flux<Solicitud> listarTodas();
}
