package br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Paciente extends Pessoa {
    private String cpf;
}
