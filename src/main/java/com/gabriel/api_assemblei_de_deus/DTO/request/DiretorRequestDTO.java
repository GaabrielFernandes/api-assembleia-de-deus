package com.gabriel.api_assemblei_de_deus.DTO.request;

import com.gabriel.api_assemblei_de_deus.enuns.Departamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiretorRequestDTO {
    private String nome;
    private String email;
    private String senha;
    private Set<Departamento> departamentos;
}
