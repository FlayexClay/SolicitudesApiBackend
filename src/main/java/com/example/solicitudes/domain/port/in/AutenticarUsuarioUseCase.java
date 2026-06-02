package com.example.solicitudes.domain.port.in;

import reactor.core.publisher.Mono;

public interface AutenticarUsuarioUseCase {

    record TokenResultado(String token, String username, String rol){}

    Mono<TokenResultado> ejecutar(String username, String passwordPlano);
}
