package com.gabriel.api_assemblei_de_deus.repository;

import com.gabriel.api_assemblei_de_deus.entity.Diretor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiretorRepository extends JpaRepository<Diretor, Long> {

    Optional<Diretor> findByEmail(String email);
}
