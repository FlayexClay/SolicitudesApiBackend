package com.example.solicitudes.domain.port.out;

import com.example.solicitudes.domain.model.Usuario;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UsuarioRepositoryPort {

    Mono<Usuario> guardar(Usuario usuario);

    Mono<Usuario> buscarPorUsername(String username);

    Mono<Usuario> buscarPorId(UUID id);

    Mono<Boolean> existePorUsername(String username);
}
