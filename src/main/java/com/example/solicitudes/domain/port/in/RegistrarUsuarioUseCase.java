package com.example.solicitudes.domain.port.in;

import com.example.solicitudes.domain.model.Usuario;
import reactor.core.publisher.Mono;

public interface RegistrarUsuarioUseCase {

    Mono<Usuario> ejecutar(String username, String passwordPlano);
}
