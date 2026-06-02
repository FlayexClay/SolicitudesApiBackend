package com.example.solicitudes.domain.port.out;

import com.example.solicitudes.domain.model.Usuario;

public interface TokenProviderPort {

    String generar(Usuario usuario);
}
