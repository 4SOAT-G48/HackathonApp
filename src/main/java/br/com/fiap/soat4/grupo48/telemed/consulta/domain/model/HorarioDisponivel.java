package br.com.fiap.soat4.grupo48.telemed.consulta.domain.model;

import lombok.*;

import java.time.LocalTime;
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
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private Date dataCriacao;
    private Date dataAtualizacao;

    
}
