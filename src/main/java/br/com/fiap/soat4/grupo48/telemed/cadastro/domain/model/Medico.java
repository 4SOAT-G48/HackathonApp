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

    /**
     * Adiciona uma especialidade ao médico
     *
     * @param especialidade especialidade a ser adicionada
     * @return true se a especialidade foi adicionada, false caso contrário
     */
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

    /**
     * Remove uma especialidade do médico
     *
     * @param especialidadeToRemove especialidade a ser removida
     * @return true se a especialidade foi removida, false caso contrário
     */
    public boolean removeEspecialidade(Especialidade especialidadeToRemove) {
        if (Objects.isNull(especialidades)) {
            especialidades = new ArrayList<>();
        }
        if (Objects.isNull(especialidadeToRemove) || !especialidades.contains(especialidadeToRemove)) {
            return false;
        } else {
            especialidades.remove(especialidadeToRemove);
            return true;
        }
    }
}
