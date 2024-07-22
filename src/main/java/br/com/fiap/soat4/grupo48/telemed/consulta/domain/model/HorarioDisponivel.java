package br.com.fiap.soat4.grupo48.telemed.consulta.domain.model;

import lombok.*;

import java.util.Date;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
/**
 * Classe que representa um horário disponível para consulta
 */
public class HorarioDisponivel {
    private UUID id;
    private UUID medicoId;
    private Date data;
    private Date horaInicio;
    private Date horaFim;
    private Date dataCriacao;
    private Date dataAtualizacao;
}
