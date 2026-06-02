CREATE TABLE IF NOT EXISTS usuarios (
    id          UUID PRIMARY KEY,
    username    VARCHAR(50) UNIQUE NOT NULL,
    password    VARCHAR(255) NOT NULL,
    rol         VARCHAR(20) NOT NULL,
    creado_en   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS solicitudes (
    id            UUID PRIMARY KEY,
    descripcion   VARCHAR(500) NOT NULL,
    estado        VARCHAR(20) NOT NULL,
    usuario_id    UUID NOT NULL REFERENCES usuarios(id),
    validador_id  UUID REFERENCES usuarios(id),
    comentario    VARCHAR(500),
    creado_en     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    validado_en   TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_solicitudes_usuario   ON solicitudes(usuario_id);
CREATE INDEX IF NOT EXISTS idx_solicitudes_estado    ON solicitudes(estado);