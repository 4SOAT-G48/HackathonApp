package br.com.fiap.soat4.grupo48.telemed.consulta.infra.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HorarioDisponivelDTO {
    private UUID medicoId;
    private Date data;
    private Date horaInicio;
    private Date horaFim;
}
