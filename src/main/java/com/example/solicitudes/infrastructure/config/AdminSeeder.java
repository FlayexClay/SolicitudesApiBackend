package com.example.solicitudes.infrastructure.config;

import com.example.solicitudes.domain.model.Rol;
import com.example.solicitudes.domain.model.Usuario;
import com.example.solicitudes.domain.port.out.PasswordHasherPort;
import com.example.solicitudes.domain.port.out.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class AdminSeeder implements ApplicationRunner {

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

    private final UsuarioRepositoryPort usuarioRepository;
    private final PasswordHasherPort passwordHasher;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        usuarioRepository.existePorUsername(ADMIN_USERNAME)
                .flatMap(existe -> existe ? Mono.empty() : crearAdmin())
                .subscribe(
                        usuario -> log.info("Admin '{}' creado exitosamente", usuario.username()),
                        error -> log.error("Error al inicializar admin", error),
                        () -> log.debug("Seed de admin completado")
                );

    }

    private Mono<Usuario> crearAdmin(){
        String hash = passwordHasher.hash(ADMIN_PASSWORD);
        Usuario admin = Usuario.nuevo(ADMIN_USERNAME, hash, Rol.ADMIN);
        return usuarioRepository.guardar(admin);
    }
}
