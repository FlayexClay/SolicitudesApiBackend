package com.example.solicitudes.application.service;

import com.example.solicitudes.domain.exception.UsuarioYaExisteException;
import com.example.solicitudes.domain.model.Rol;
import com.example.solicitudes.domain.model.Usuario;
import com.example.solicitudes.domain.port.in.RegistrarUsuarioUseCase;
import com.example.solicitudes.domain.port.out.PasswordHasherPort;
import com.example.solicitudes.domain.port.out.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RegistrarUsuarioService implements RegistrarUsuarioUseCase {

    private final UsuarioRepositoryPort usuarioRepository;
    private final PasswordHasherPort passwordHasher;

    @Override
    public Mono<Usuario> ejecutar(String username, String passwordPlano){
        return usuarioRepository.existePorUsername(username)
                .flatMap(existe -> existe
                        ? Mono.<Usuario>error(new UsuarioYaExisteException(
                        "El username '" + username + "' ya está registrado"))
                        : crearUsuarioConRolUser(username, passwordPlano));
    }

    private Mono<Usuario> crearUsuarioConRolUser(String username, String passwordPlano){
        String hash = passwordHasher.hash(passwordPlano);
        Usuario nuevo = Usuario.nuevo(username, hash, Rol.USER);
        return usuarioRepository.guardar(nuevo);
    }
}
