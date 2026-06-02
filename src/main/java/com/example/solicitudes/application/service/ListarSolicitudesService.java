package com.example.solicitudes.application.service;

import com.example.solicitudes.domain.model.EstadoSolicitud;
import com.example.solicitudes.domain.model.Solicitud;
import com.example.solicitudes.domain.port.in.ListarSolicitudesUseCase;
import com.example.solicitudes.domain.port.out.SolicitudRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ListarSolicitudesService implements ListarSolicitudesUseCase {

    private final SolicitudRepositoryPort repository;

    @Override
    public Flux<Solicitud> listarMias(UUID usuarioId){
        return repository.buscarPorUsuario(usuarioId);
    }

    @Override
    public Flux<Solicitud> listarPendientes(){
        return repository.buscarPorEstado(EstadoSolicitud.PENDIENTE);
    }

    @Override
    public Flux<Solicitud> listarTodas(){
        return repository.buscarTodas();
    }


}
