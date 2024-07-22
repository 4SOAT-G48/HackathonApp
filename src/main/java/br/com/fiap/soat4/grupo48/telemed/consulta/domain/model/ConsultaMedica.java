package br.com.fiap.soat4.grupo48.telemed.consulta.domain.model;

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
    private UUID medicoId;
    private UUID pacienteId;
    private Date dataHoraConsulta;
    private SituacaoConsultaMedica status;
    private String justificativaCancelamento;
    private Date dataCriacao;
    private Date dataAtualizacao;

}
