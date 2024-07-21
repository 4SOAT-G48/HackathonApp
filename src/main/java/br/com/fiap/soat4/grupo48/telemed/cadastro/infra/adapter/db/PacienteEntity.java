package br.com.fiap.soat4.grupo48.telemed.cadastro.infra.adapter.db;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("PACIENTE")
@Table(name = "PACIENTE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PacienteEntity extends PessoaEntity {
    private String cpf;
}
