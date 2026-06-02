package com.example.solicitudes.domain.port.out;

public interface PasswordHasherPort {

    String hash(String passwordPlano);

    boolean coincide(String passwordPlano, String hash);
}
