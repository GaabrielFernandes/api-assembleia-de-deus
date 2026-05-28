package com.gabriel.api_assemblei_de_deus.DTO.page;

import com.gabriel.api_assemblei_de_deus.enuns.Departamento;
import lombok.Data;

@Data
public class DiretorPage {
    private Long id;
    private String nome;
    private String email;
    private Departamento departamento;
}
