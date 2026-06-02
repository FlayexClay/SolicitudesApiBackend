package com.example.solicitudes.application.service;

import com.example.solicitudes.domain.exception.CredencialesInvalidasException;
import com.example.solicitudes.domain.port.in.AutenticarUsuarioUseCase;
import com.example.solicitudes.domain.port.out.PasswordHasherPort;
import com.example.solicitudes.domain.port.out.TokenProviderPort;
import com.example.solicitudes.domain.port.out.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AutenticarUsuarioService implements AutenticarUsuarioUseCase {

    private static final String MENSAJE_CREDENCIALES = "Username o password incorrectos";

    private final UsuarioRepositoryPort usuarioRepository;
    private final PasswordHasherPort passwordHasher;
    private final TokenProviderPort tokenProvider;

    @Override
    public Mono<TokenResultado> ejecutar(String username, String passwordPlano){
        return usuarioRepository.buscarPorUsername(username)
                .switchIfEmpty(Mono.error(new CredencialesInvalidasException(MENSAJE_CREDENCIALES)))
                .filter(usuario -> passwordHasher.coincide(passwordPlano, usuario.password()))
                .switchIfEmpty(Mono.error(new CredencialesInvalidasException(MENSAJE_CREDENCIALES)))
                .map(usuario -> new TokenResultado(
                        tokenProvider.generar(usuario),
                        usuario.username(),
                        usuario.rol().name()
                ));
    }
}
