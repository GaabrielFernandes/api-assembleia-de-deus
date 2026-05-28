package com.gabriel.api_assemblei_de_deus.enuns;

import lombok.Getter;

@Getter
public enum Departamento {
    ADMINISTRADOR("Administrador"),
    SECRETARIA("Secretaria"),
    TESOURARIA("tesouraria"),
    CONSELHO_FISCAL("conselho fiscal");

    private String nome;

    Departamento(final String nome){
        this.nome = nome;
    }
}
