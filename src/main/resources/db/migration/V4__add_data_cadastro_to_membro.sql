-- Adiciona a coluna data_cadastro (se não existir)
ALTER TABLE membro
ADD COLUMN IF NOT EXISTS data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

-- Atualiza registros existentes (caso algum tenha ficado NULL)
UPDATE membro SET data_cadastro = CURRENT_TIMESTAMP WHERE data_cadastro IS NULL;

-- Torna a coluna NOT NULL (opcional, mas recomendado)
ALTER TABLE membro
ALTER COLUMN data_cadastro SET NOT NULL;