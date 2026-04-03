CREATE TABLE IF NOT EXISTS diretor(
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100),
    senha VARCHAR(255),
    email VARCHAR(50),
    departamento VARCHAR(100)
)