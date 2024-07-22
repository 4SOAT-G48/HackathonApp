package br.com.fiap.soat4.grupo48.telemed.consulta.application.port.out;

import br.com.fiap.soat4.grupo48.telemed.consulta.domain.model.ConsultaMedica;
import br.com.fiap.soat4.grupo48.telemed.consulta.domain.model.SituacaoConsultaMedica;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Interface que define os métodos de consulta de consultas médicas
 */
public interface IConsultaMedicaRepository {
    /**
     * Salva uma nova consulta médica no banco de dados.
     *
     * @param consultaMedica A consulta médica a ser salva.
     * @return A consulta médica salva.
     */
    ConsultaMedica salvarConsulta(ConsultaMedica consultaMedica);

    /**
     * Busca uma consulta médica pelo seu ID.
     *
     * @param id O ID da consulta médica.
     * @return A consulta médica encontrada.
     */
    ConsultaMedica buscarConsultaPorId(UUID id);

    /**
     * Busca todas as consultas médicas cadastradas no sistema.
     *
     * @return Uma lista contendo todas as consultas médicas cadastradas.
     */
    List<ConsultaMedica> buscarConsultasPorPaciente(UUID pacienteId);

    /**
     * Busca todas as consultas médicas de um médico.
     *
     * @param medicoId O ID do médico.
     * @return Uma lista contendo todas as consultas médicas do médico.
     */
    List<ConsultaMedica> buscarConsultasPorMedico(UUID medicoId);

    List<ConsultaMedica> buscarConsultasPorData(Date data);
    
    List<ConsultaMedica> buscarConsultasPorPeriodo(Date dataInicio, Date dataFim);

    void cancelarConsulta(UUID id, String justificativa);

    void confirmarConsulta(UUID id);

    void recusarConsulta(UUID id);

    void realizarConsulta(UUID id);

    void deletarConsulta(UUID id);

    List<ConsultaMedica> buscarConsultasPorStatus(SituacaoConsultaMedica status);

    List<ConsultaMedica> buscarConsultasPorStatusEPaciente(SituacaoConsultaMedica status, UUID pacienteId);

    List<ConsultaMedica> buscarConsultasPorStatusEMedico(SituacaoConsultaMedica status, UUID medicoId);

    List<ConsultaMedica> buscarConsultasPorStatusEData(SituacaoConsultaMedica status, Date data);

    List<ConsultaMedica> buscarConsultasPorStatusEPeriodo(SituacaoConsultaMedica status, Date dataInicio, Date dataFim);

    List<ConsultaMedica> buscarConsultasPorStatusEPacienteEData(SituacaoConsultaMedica status, UUID pacienteId, Date data);

    List<ConsultaMedica> buscarConsultasPorStatusEMedicoEData(SituacaoConsultaMedica status, UUID medicoId, Date data);

    List<ConsultaMedica> buscarConsultasPorStatusEPacienteEPeriodo(SituacaoConsultaMedica status, UUID pacienteId, Date dataInicio, Date dataFim);

    List<ConsultaMedica> buscarConsultasPorStatusEMedicoEPeriodo(SituacaoConsultaMedica status, UUID medicoId, Date dataInicio, Date dataFim);

    List<ConsultaMedica> buscarConsultasPorStatusEPacienteEMedico(SituacaoConsultaMedica status, UUID pacienteId, UUID medicoId);

    List<ConsultaMedica> buscarConsultasPorStatusEPacienteEMedicoEData(SituacaoConsultaMedica status, UUID pacienteId, UUID medicoId, Date data);
}
