package br.com.fiap.soat4.grupo48.telemed.cadastro.infra.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MedicoDTO {
    private String nome;
    private String email;
    private String crm;
    private List<EspecialidadeDTO> especialidades;
}
