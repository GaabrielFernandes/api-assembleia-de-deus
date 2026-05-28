package com.gabriel.api_assemblei_de_deus.repository;

import com.gabriel.api_assemblei_de_deus.entity.Membro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface MembroRepository extends JpaRepository<Membro, Long> {
    List<Membro> findByNomeCompletoIgnoreCaseContaining(String nome);

    @Query("SELECT COUNT(m) FROM Membro m WHERE m.dataCadastro <= :data")
    Long countByDataCadastroBefore(@Param("data") LocalDateTime data);

    // Alternativa com JPQL (pode funcionar dependendo do dialeto)
    @Query("SELECT COUNT(m) FROM Membro m WHERE EXTRACT(MONTH FROM m.dataCadastro) = :mes AND EXTRACT(YEAR FROM m.dataCadastro) = :ano")
    Long countNovosMembrosPorMes(@Param("mes") Integer mes, @Param("ano") Integer ano);

    @Query("SELECT COUNT(m) FROM Membro m WHERE EXTRACT(DAY FROM m.dataNascimento) = :dia AND EXTRACT(MONTH FROM m.dataNascimento) = :mes")
    Long countAniversariantesHoje(@Param("dia") Integer dia, @Param("mes") Integer mes);

    @Query("SELECT COUNT(m) FROM Membro m WHERE EXTRACT(MONTH FROM m.dataNascimento) = :mes")
    Long countAniversariantesNoMes(@Param("mes") Integer mes);
}