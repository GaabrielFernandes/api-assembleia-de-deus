package com.gabriel.api_assemblei_de_deus.entity;

import com.gabriel.api_assemblei_de_deus.enuns.EstadoCivil;
import com.gabriel.api_assemblei_de_deus.enuns.Sexo;
import com.gabriel.api_assemblei_de_deus.enuns.TipoAdmissao;
import com.gabriel.api_assemblei_de_deus.enuns.TipoMembro;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "membro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Membro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 150)
    @Column(name = "nome_completo", nullable = false, length = 150)
    private String nomeCompleto;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Sexo sexo;

    private LocalDate dataNascimento;

    @Size(max = 120)
    @Column(name = "cidade_nascimento", length = 120)
    private String cidadeNascimento;

    /* =========================
       ENDEREÇO ATUAL
       ========================= */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "cep", column = @Column(name = "cep_atual", length = 8)),
            @AttributeOverride(name = "logradouro", column = @Column(name = "logradouro_atual", length = 150)),
            @AttributeOverride(name = "bairro", column = @Column(name = "bairro_atual", length = 120)),
            @AttributeOverride(name = "numero", column = @Column(name = "numero_atual", length = 20)),
            @AttributeOverride(name = "complemento", column = @Column(name = "complemento_atual", length = 120)),
            @AttributeOverride(name = "cidade", column = @Column(name = "cidade_atual", length = 120)),
            @AttributeOverride(name = "uf", column = @Column(name = "uf_atual", length = 2))
    })

    private Endereco enderecoAtual = new Endereco();

    /* =========================
       DADOS PESSOAIS
       ========================= */
    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private EstadoCivil estadoCivil;

    private LocalDate dataCasamento;

    @Size(max = 150)
    private String conjuge;

    @Size(max = 150)
    private String mae;

    @Size(max = 150)
    private String pai;

    @Size(max = 11)
    private String celular;

    @Email
    @Size(max = 150)
    private String email;

    /* =========================
       DADOS ECLESIÁSTICOS
       ========================= */
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private TipoMembro tipoMembro;

    @Size(max = 120)
    private String situacao;

    private LocalDate dataBatismo;

    @Size(max = 120)
    private String pastorOficiante;

    private LocalDate dataBatismoEspiritoSanto;

    private LocalDate dataAdmissao;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private TipoAdmissao admitidoPor;

    @Size(max = 120)
    private String admitidoPorOutro;

    @Size(max = 120)
    private String ministerioPrincipal;

    @Size(max = 120)
    private String funcaoMinisterial;

    @Size(max = 120)
    private String departamentoPrincipal;

    @Size(max = 120)
    private String funcaoDepartamental;

    private LocalDate dataRemocao;

    @Size(max = 150)
    private String removidoPor;

    @Column(columnDefinition = "text")
    private String observacoes;
}
