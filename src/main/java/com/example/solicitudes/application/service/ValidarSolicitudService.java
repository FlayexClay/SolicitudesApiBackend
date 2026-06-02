package com.example.solicitudes.application.service;

import com.example.solicitudes.domain.exception.SolicitudNoEncontradaException;
import com.example.solicitudes.domain.model.Solicitud;
import com.example.solicitudes.domain.port.in.ValidarSolicitudUseCase;
import com.example.solicitudes.domain.port.out.SolicitudRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ValidarSolicitudService implements ValidarSolicitudUseCase {

    private final SolicitudRepositoryPort repository;

    @Override
    public Mono<Solicitud> ejecutar(UUID solicitudId, UUID adminId, Decision decision, String comentario){
        return repository.buscarPorId(solicitudId)
                .switchIfEmpty(Mono.error(new SolicitudNoEncontradaException(
                        "No se encontro la solicitud: " + solicitudId)))
                .map(solicitud -> aplicarDecision(solicitud, adminId, decision, comentario))
                .flatMap(repository::guardar);
    }

    private Solicitud aplicarDecision(Solicitud solicitud, UUID adminId, Decision decision, String comentario){
        return decision == Decision.APROBAR ? solicitud.aprobar(adminId, comentario) : solicitud.rechazar(adminId, comentario);
    }
}
