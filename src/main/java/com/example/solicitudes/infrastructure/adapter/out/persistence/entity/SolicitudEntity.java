package com.example.solicitudes.infrastructure.adapter.out.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("solicitudes")
public class SolicitudEntity {

    @Id
    private UUID id;
    private String descripcion;
    private String estado;

    @Column("usuario_id")
    private UUID usuarioId;

    @Column("validador_id")
    private UUID validadorId;

    private String comentario;

    @Column("creado_en")
    private LocalDateTime createdAt;

    @Column("validado_en")
    private LocalDateTime validatedAt;

    public SolicitudEntity() {}

    public SolicitudEntity(UUID id, String descripcion, String estado, UUID usuarioId,
                           UUID validadorId, String comentario,
                           LocalDateTime createdAt, LocalDateTime validatedAt) {
        this.id = id;
        this.descripcion = descripcion;
        this.estado = estado;
        this.usuarioId = usuarioId;
        this.validadorId = validadorId;
        this.comentario = comentario;
        this.createdAt = createdAt;
        this.validatedAt = validatedAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public UUID getUsuarioId() { return usuarioId; }
    public void setUsuarioId(UUID usuarioId) { this.usuarioId = usuarioId; }
    public UUID getValidadorId() { return validadorId; }
    public void setValidadorId(UUID validadorId) { this.validadorId = validadorId; }
    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getValidatedAt() { return validatedAt; }
    public void setValidatedAt(LocalDateTime validatedAt) { this.validatedAt = validatedAt; }
}




