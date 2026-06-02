package com.example.solicitudes.application.service;

import com.example.solicitudes.domain.model.Solicitud;
import com.example.solicitudes.domain.port.in.CrearSolicitudUseCase;
import com.example.solicitudes.domain.port.out.SolicitudRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CrearSolicitudService implements CrearSolicitudUseCase {

    private final SolicitudRepositoryPort repositoryPort;

    @Override
    public Mono<Solicitud> ejecutar(String descripcion, UUID usuarioId){
        Solicitud nueva = Solicitud.crear(descripcion, usuarioId);
        return repositoryPort.guardar(nueva);
    }
}
