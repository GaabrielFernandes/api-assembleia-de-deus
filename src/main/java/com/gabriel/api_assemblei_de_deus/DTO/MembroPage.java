package com.gabriel.api_assemblei_de_deus.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MembroPage {
    private Long id;
    private String nomeCompleto;
    private LocalDate dataBatismo;
    private LocalDate dataAdmissao;
}
