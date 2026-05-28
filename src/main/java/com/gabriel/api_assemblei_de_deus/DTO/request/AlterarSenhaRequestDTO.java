package com.gabriel.api_assemblei_de_deus.DTO.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlterarSenhaRequestDTO {
    @NotBlank
    private String novaSenha;
}
