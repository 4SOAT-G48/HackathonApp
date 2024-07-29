package br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@EqualsAndHashCode
public abstract class Pessoa {
    private UUID id;
    private String nome;
    private String email;
    private Date dataCriacao;
    private Date dataAtualizacao;
}
