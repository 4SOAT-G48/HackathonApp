package br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Especialidade {
    private UUID id;
    private Long codigo;
    private String descricao;
}
