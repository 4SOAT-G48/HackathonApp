package br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Especialidade {
    private UUID id;
    private String codigo;
    private String descricao;
    private Date dataCriacao;
    private Date dataAtualizacao;
}
