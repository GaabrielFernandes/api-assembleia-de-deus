package com.gabriel.api_assemblei_de_deus.DTO;

import com.gabriel.api_assemblei_de_deus.enuns.EstadoCivil;
import com.gabriel.api_assemblei_de_deus.enuns.Sexo;
import com.gabriel.api_assemblei_de_deus.enuns.TipoAdmissao;
import com.gabriel.api_assemblei_de_deus.enuns.TipoMembro;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MembroRequestDTO {
    @NotBlank
    @Size(max = 150



    )
    private String nomeCompleto;
    private Sexo sexo;
    private LocalDate dataNascimento;
    @Size(max = 120)
    private String cidadeNascimento;
    @NotNull
    @Valid
    private EnderecoDTO enderecoAtual;
    private EstadoCivil estadoCivil;
    private LocalDate dataCasamento;
    private String conjuge;
    private String mae;
    private String pai;
    private String celular;
    @Email(message = "E-mail inválido")
    private String email;
    private TipoMembro tipoMembro;
    private String situacao;
    private LocalDate dataBatismo;
    private String pastorOficiante;
    private LocalDate dataBatismoEspiritoSanto;
    private LocalDate dataAdmissao;
    private TipoAdmissao admitidoPor;
    private String admitidoPorOutro;
    private String ministerioPrincipal;
    private String funcaoMinisterial;
    private String departamentoPrincipal;
    private String funcaoDepartamental;
    private LocalDate dataRemocao;
    private String removidoPor;
    private String observacoes;
}
