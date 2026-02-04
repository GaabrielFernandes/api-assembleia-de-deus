package com.gabriel.api_assemblei_de_deus.exception;

import jakarta.persistence.Id;

public class MembroNaoEncontradoException extends RuntimeException {

    public MembroNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    public MembroNaoEncontradoException(Long id){super("Membro não encontrado com esse ID: "+ id);}

    public MembroNaoEncontradoException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }
}
