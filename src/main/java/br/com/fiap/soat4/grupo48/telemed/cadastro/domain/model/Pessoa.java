package br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Pessoa {
    private UUID id;
    private String nome;
    private String email;
}
