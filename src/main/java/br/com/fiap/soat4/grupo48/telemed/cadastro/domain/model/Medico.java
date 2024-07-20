package br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medico extends Pessoa {
    private String crm;
    private List<Especialidade> especialidades;

}
