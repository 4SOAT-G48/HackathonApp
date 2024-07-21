package br.com.fiap.soat4.grupo48.telemed.cadastro.infra.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EspecialidadeDTO {
    private UUID id;
    private Long codigo;
    private String descricao;

}
