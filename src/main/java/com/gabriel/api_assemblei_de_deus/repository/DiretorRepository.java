package com.gabriel.api_assemblei_de_deus.repository;

import com.gabriel.api_assemblei_de_deus.entity.Diretor;
import com.gabriel.api_assemblei_de_deus.enuns.Departamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiretorRepository extends JpaRepository<Diretor, Long> {

    Optional<Diretor> findByEmail(String email);

    Page<Diretor> findByDepartamentoNot(Departamento departamento, Pageable pageable);
}
