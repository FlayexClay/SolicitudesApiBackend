package com.example.solicitudes.infrastructure.adapter.out.persistence.mapper;

import com.example.solicitudes.domain.model.Rol;
import com.example.solicitudes.domain.model.Usuario;
import com.example.solicitudes.infrastructure.adapter.out.persistence.entity.UsuarioEntity;

public final class UsuarioPersistenceMapper {

    private UsuarioPersistenceMapper() {}

    public static UsuarioEntity toEntity(Usuario usuario){
        return new UsuarioEntity(
                usuario.id(),
                usuario.username(),
                usuario.password(),
                usuario.rol().name(),
                usuario.createdAt()
        );
    }

    public static Usuario toDomain(UsuarioEntity entity){
        return new Usuario(
                entity.getId(),
                entity.getUsername(),
                entity.getPassword(),
                Rol.valueOf(entity.getRol()),
                entity.getCreatedAt()
                );
    }
}
