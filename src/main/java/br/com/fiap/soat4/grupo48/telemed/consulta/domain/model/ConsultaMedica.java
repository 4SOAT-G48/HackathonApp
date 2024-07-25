package br.com.fiap.soat4.grupo48.telemed.consulta.domain.model;

import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Medico;
import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Paciente;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ConsultaMedica {
    private UUID id;
    private Medico medico;
    private Paciente paciente;
    private HorarioDisponivel horario;
    private SituacaoConsultaMedica status;
    private String justificativaCancelamento;
    private Date dataCriacao;
    private Date dataAtualizacao;

}
