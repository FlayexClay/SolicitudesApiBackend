package com.example.solicitudes.infrastructure.adapter.out.persistence.adapter;

import com.example.solicitudes.domain.model.Usuario;
import com.example.solicitudes.domain.port.out.UsuarioRepositoryPort;
import com.example.solicitudes.infrastructure.adapter.out.persistence.entity.UsuarioEntity;
import com.example.solicitudes.infrastructure.adapter.out.persistence.mapper.UsuarioPersistenceMapper;
import com.example.solicitudes.infrastructure.adapter.out.persistence.repository.UsuarioR2dbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UsuarioPersistenceAdapter implements UsuarioRepositoryPort {

    private final UsuarioR2dbcRepository repository;
    private final R2dbcEntityTemplate template;

    @Override
    public Mono<Usuario> guardar(Usuario usuario){
        UsuarioEntity entity = UsuarioPersistenceMapper.toEntity(usuario);
        return repository.existsById(entity.getId())
                .flatMap(existe -> existe
                        ? template.update(entity)
                        : template.insert(entity))
                .map(UsuarioPersistenceMapper::toDomain);
    }

    @Override
    public Mono<Usuario> buscarPorUsername(String username){
        return repository.findByUsername(username).map(UsuarioPersistenceMapper::toDomain);
    }

    @Override
    public Mono<Usuario> buscarPorId(UUID id){
        return repository.findById(id).map(UsuarioPersistenceMapper::toDomain);
    }

    @Override
    public Mono<Boolean> existePorUsername(String username){
        return repository.existsByUsername(username);
    }

}
