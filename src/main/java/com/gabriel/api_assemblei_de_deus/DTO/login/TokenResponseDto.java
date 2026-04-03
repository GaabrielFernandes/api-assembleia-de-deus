package com.gabriel.api_assemblei_de_deus.DTO.login;

import com.gabriel.api_assemblei_de_deus.enuns.Departamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponseDto {
    private String nome;
    private String token;
    private String tipo;
    private String email;
    private Departamento departamento;
}
