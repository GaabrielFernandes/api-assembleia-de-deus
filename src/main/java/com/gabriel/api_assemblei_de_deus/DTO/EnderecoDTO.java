package com.gabriel.api_assemblei_de_deus.DTO;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {
    @Size(max = 8)
    private String cep;

    @Size(max = 150)
    private String logradouro;

    @Size(max = 120)
    private String bairro;

    @Size(max = 20)
    private String numero;

    @Size(max = 120)
    private String complemento;

    @Size(max = 120)
    private String cidade;

    @Size(max = 2)
    private String uf;
}
