package br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Medico extends Pessoa {
    private String crm;
    private List<Especialidade> especialidades;

    public boolean addEspecialidade(Especialidade especialidade) {
        if (Objects.isNull(especialidades)) {
            especialidades = new ArrayList<>();
        }
        if (Objects.isNull(especialidade) || especialidades.contains(especialidade)) {
            return false;
        } else {
            especialidades.add(especialidade);
            return true;
        }
    }
}
