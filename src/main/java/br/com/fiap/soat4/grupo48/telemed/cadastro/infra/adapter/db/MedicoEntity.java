package br.com.fiap.soat4.grupo48.telemed.cadastro.infra.adapter.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@DiscriminatorValue("MEDICO")
@Table(name = "MEDICO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicoEntity extends PessoaEntity {

    private String crm;
    @ManyToMany(
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL
    )
    @JoinTable(
        name = "medico_especialidade",
        joinColumns = @JoinColumn(name = "medico_id"),
        inverseJoinColumns = @JoinColumn(name = "especialidade_id")
    )
    private List<EspecialidadeEntity> especialidades;
}
