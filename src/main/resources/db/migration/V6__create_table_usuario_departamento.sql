CREATE TABLE IF NOT EXISTS usuario_departamento(
    usuario_id BIGINT NOT NULL,
    departamentos VARCHAR(100),
    PRIMARY KEY(usuario_id, departamentos),
    CONSTRAINT fk_usuario_departamento_id FOREIGN KEY(usuario_id) REFERENCES diretor(id) ON DELETE CASCADE
);