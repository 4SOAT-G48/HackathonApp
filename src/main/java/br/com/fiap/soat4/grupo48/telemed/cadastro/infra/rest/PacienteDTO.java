package br.com.fiap.soat4.grupo48.telemed.cadastro.infra.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PacienteDTO {
    private String nome;
    private String email;
    private String cpf;
}
