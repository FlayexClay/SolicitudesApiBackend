package com.example.solicitudes.infrastructure.adapter.out.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("usuarios")
public class UsuarioEntity {

    @Id
    private UUID id;
    private String username;
    private String password;
    private String rol;

    @Column("creado_en")
    private LocalDateTime createdAt;

    public UsuarioEntity() {}

    public UsuarioEntity(UUID id, String username, String password, String rol, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.createdAt = createdAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

}
