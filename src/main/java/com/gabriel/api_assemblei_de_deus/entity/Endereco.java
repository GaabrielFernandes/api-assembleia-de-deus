package com.gabriel.api_assemblei_de_deus.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    @Column(length = 8)
    private String cep;

    @Column(length = 150)
    private String logradouro;

    @Column(length = 120)
    private String bairro;

    @Column(length = 20)
    private String numero;

    @Column(length = 120)
    private String complemento;

    @Column(length = 120)
    private String cidade;

    @Column(length = 2)
    private String uf;
}
