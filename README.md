# API Assembleia de Deus

API REST para um sistema de gestão (membros e futuras movimentações financeiras) de uma igreja.

## Stack
- Java + Spring Boot
- Maven
- JPA / Hibernate
- PostgreSQL
- Flyway (migrations)

## Status
🚧 Em desenvolvimento

## Como rodar local
1. Configure o banco (PostgreSQL)
2. Crie um arquivo `application.properties` (não versionado) com as configs
3. Rode:
```bash
./mvnw spring-boot:run
