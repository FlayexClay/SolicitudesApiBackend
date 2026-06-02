package com.example.solicitudes.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "El username es obligatorio")
        String username,

        @NotBlank(message = "El password es obligatorrio")
        String password
) {
}
