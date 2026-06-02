package com.example.solicitudes.infrastructure.security;

import com.example.solicitudes.domain.port.out.PasswordHasherPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptPasswordHasher implements PasswordHasherPort {

    private  final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String hash(String passwordPlano) {
        return encoder.encode(passwordPlano);
    }

    @Override
    public boolean coincide(String passwordPlano, String hash) {
        return encoder.matches(passwordPlano, hash);
    }
}
