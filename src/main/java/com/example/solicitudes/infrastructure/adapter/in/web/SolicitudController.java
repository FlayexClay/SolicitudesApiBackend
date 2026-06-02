package com.example.solicitudes.infrastructure.adapter.in.web;

import com.example.solicitudes.domain.port.in.CrearSolicitudUseCase;
import com.example.solicitudes.domain.port.in.ListarSolicitudesUseCase;
import com.example.solicitudes.domain.port.in.ValidarSolicitudUseCase;
import com.example.solicitudes.infrastructure.adapter.in.web.dto.CrearSolicitudRequest;
import com.example.solicitudes.infrastructure.adapter.in.web.dto.SolicitudResponse;
import com.example.solicitudes.infrastructure.adapter.in.web.dto.ValidarSolicitudRequest;
import com.example.solicitudes.infrastructure.adapter.in.web.mapper.SolicitudWebMapper;
import com.example.solicitudes.infrastructure.security.AuthenticatedUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/solicitudes")
@RequiredArgsConstructor
public class SolicitudController {

    private final CrearSolicitudUseCase crearSolicitudUseCase;
    private final ValidarSolicitudUseCase validarSolicitudUseCase;
    private final ListarSolicitudesUseCase listarSolicitudesUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<SolicitudResponse> crear(@Valid @RequestBody CrearSolicitudRequest request){
        return AuthenticatedUser.currentUserId()
                .flatMap(userId -> crearSolicitudUseCase.ejecutar(request.descripcion(), userId))
                .map(SolicitudWebMapper::toResponse);
    }

    @GetMapping("/mias")
    public Flux<SolicitudResponse> listarMias(){
        return AuthenticatedUser.currentUserId()
                .flatMapMany(listarSolicitudesUseCase::listarMias)
                .map(SolicitudWebMapper::toResponse);
    }

    @GetMapping("/pendientes")
    public Flux<SolicitudResponse> listarPendientes(){
        return listarSolicitudesUseCase.listarPendientes()
                .map(SolicitudWebMapper::toResponse);
    }

    @GetMapping("/todas")
    public Flux<SolicitudResponse> listarTodas(){
        return listarSolicitudesUseCase.listarTodas()
                .map(SolicitudWebMapper::toResponse);
    }

    @PutMapping("/{id}/validar")
    public Mono<SolicitudResponse> validar(@PathVariable UUID id,
                                           @Valid @RequestBody ValidarSolicitudRequest request
    ){
        return AuthenticatedUser.currentUserId()
                .flatMap(adminId -> validarSolicitudUseCase.ejecutar(
                        id, adminId, request.decision(), request.comentario()))
                .map(SolicitudWebMapper::toResponse);
    }
}
