package br.com.fiap.soat4.grupo48.telemed.consulta.infra.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaMedicaDTO {
    private UUID medicoId;
    private UUID pacienteId;
    private UUID horarioDisponivelId;
    private String justificativaCancelamento;
}
