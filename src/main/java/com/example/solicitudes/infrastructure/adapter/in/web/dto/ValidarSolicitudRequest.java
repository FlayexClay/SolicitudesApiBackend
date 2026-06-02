package com.example.solicitudes.infrastructure.adapter.in.web.dto;

import com.example.solicitudes.domain.port.in.ValidarSolicitudUseCase.Decision;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ValidarSolicitudRequest(
        @NotNull(message = "La decision es obligatoria (APROBAR o RECHAZAR)")
        Decision decision,

        @Size(max = 500, message = "El comentario no puede superar 500 caracteres")
        String comentario
) {}
