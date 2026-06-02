package com.example.solicitudes.infrastructure.adapter.in.web.dto;

public record LoginResponse(
        String token,
        String tipoToken,
        String username,
        String rol
) {
    public static LoginResponse of(String token, String username, String rol){
        return new LoginResponse(token, "Bearer", username, rol);
    }
}
