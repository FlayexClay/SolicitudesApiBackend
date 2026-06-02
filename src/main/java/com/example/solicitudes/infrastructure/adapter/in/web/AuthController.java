package com.example.solicitudes.infrastructure.adapter.in.web;

import com.example.solicitudes.domain.port.in.AutenticarUsuarioUseCase;
import com.example.solicitudes.domain.port.in.RegistrarUsuarioUseCase;
import com.example.solicitudes.infrastructure.adapter.in.web.dto.LoginRequest;
import com.example.solicitudes.infrastructure.adapter.in.web.dto.LoginResponse;
import com.example.solicitudes.infrastructure.adapter.in.web.dto.RegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RegistrarUsuarioUseCase registrarUsuarioUseCase;
    private final AutenticarUsuarioUseCase autenticarUsuarioUseCase;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<LoginResponse> register(@Valid @RequestBody RegisterRequest request){
        return registrarUsuarioUseCase.ejecutar(request.username(), request.password())
                .flatMap(usuario -> autenticarUsuarioUseCase.ejecutar(
                        request.username(), request.password()))
                .map(resultado -> LoginResponse.of(
                        resultado.token(), resultado.username(), resultado.rol()));
    }

    @PostMapping("/login")
    public Mono<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        return autenticarUsuarioUseCase.ejecutar(request.username(), request.password())
                .map(resultado -> LoginResponse.of(
                        resultado.token(), resultado.username(), resultado.rol()));
    }
}
