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
    ConsultaMedica saveConsulta(ConsultaMedica consultaMedica);

    /**
     * Busca uma consulta médica pelo seu ID.
     *
     * @param id O ID da consulta médica.
     * @return A consulta médica encontrada.
     */
    ConsultaMedica findConsultaById(UUID id);

    /**
     * Busca todas as consultas médicas cadastradas no sistema.
     *
     * @return Uma lista contendo todas as consultas médicas cadastradas.
     */
    List<ConsultaMedica> findConsultasByPaciente(UUID pacienteId);

    /**
     * Busca todas as consultas médicas de um médico.
     *
     * @param medicoId O ID do médico.
     * @return Uma lista contendo todas as consultas médicas do médico.
     */
    List<ConsultaMedica> findConsultasByMedico(UUID medicoId);

    /**
     * Busca consultas médicas pela data específica.
     *
     * @param data A data para buscar as consultas médicas.
     * @return Uma lista de consultas médicas agendadas para a data especificada.
     */
    List<ConsultaMedica> findConsultasByData(Date data);

    /**
     * Busca consultas médicas em um período específico.
     *
     * @param dataInicio A data de início do período para buscar as consultas médicas.
     * @param dataFim    A data de fim do período para buscar as consultas médicas.
     * @return Uma lista de consultas médicas agendadas dentro do período especificado.
     */
    List<ConsultaMedica> findConsultasByPeriodo(Date dataInicio, Date dataFim);

    /**
     * Deleta uma consulta médica do banco de dados.
     *
     * @param id O ID da consulta médica a ser deletada.
     */
    void deleteConsulta(UUID id);

    /**
     * Busca consultas médicas com base no status.
     *
     * @param status O status da consulta médica.
     * @return Uma lista de consultas médicas que correspondem ao status fornecido.
     */
    List<ConsultaMedica> findConsultasByStatus(SituacaoConsultaMedica status);

    /**
     * Busca consultas médicas com base no status e no ID do paciente.
     *
     * @param status     O status da consulta médica.
     * @param pacienteId O ID do paciente.
     * @return Uma lista de consultas médicas que correspondem ao status fornecido e ao ID do paciente.
     */
    List<ConsultaMedica> findConsultasByStatusAndPaciente(SituacaoConsultaMedica status, UUID pacienteId);

    /**
     * Busca consultas médicas com base no status e no ID do médico.
     *
     * @param status   O status da consulta médica.
     * @param medicoId O ID do médico.
     * @return Uma lista de consultas médicas que correspondem ao status fornecido e ao ID do médico.
     */
    List<ConsultaMedica> findConsultasByStatusAndMedico(SituacaoConsultaMedica status, UUID medicoId);

    /**
     * Busca consultas médicas com base no status e em uma data específica.
     *
     * @param status O status da consulta médica.
     * @param data   A data específica das consultas.
     * @return Uma lista de consultas médicas que correspondem ao status fornecido e à data especificada.
     */
    List<ConsultaMedica> findConsultasByStatusAndData(SituacaoConsultaMedica status, Date data);

    /**
     * Busca consultas médicas com base no status e em um período de datas.
     *
     * @param status     O status da consulta médica.
     * @param dataInicio A data de início do período.
     * @param dataFim    A data de fim do período.
     * @return Uma lista de consultas médicas que correspondem ao status fornecido e ao período de datas especificado.
     */
    List<ConsultaMedica> findConsultasByStatusAndPeriodo(SituacaoConsultaMedica status, Date dataInicio, Date dataFim);

    /**
     * Busca consultas médicas com base no status, no ID do paciente e em uma data específica.
     *
     * @param status     O status da consulta médica.
     * @param pacienteId O ID do paciente.
     * @param data       A data específica das consultas.
     * @return Uma lista de consultas médicas que correspondem ao status fornecido, ao ID do paciente e à data especificada.
     */
    List<ConsultaMedica> findConsultasByStatusAndPacienteAndData(SituacaoConsultaMedica status, UUID pacienteId, Date data);

    /**
     * Busca consultas médicas com base no status, no ID do médico e em uma data específica.
     *
     * @param status   O status da consulta médica.
     * @param medicoId O ID do médico.
     * @param data     A data específica das consultas.
     * @return Uma lista de consultas médicas que correspondem ao status fornecido, ao ID do médico e à data especificada.
     */
    List<ConsultaMedica> findConsultasByStatusAndMedicoAndData(SituacaoConsultaMedica status, UUID medicoId, Date data);

    /**
     * Busca consultas médicas com base no status, no ID do paciente e em um período de datas.
     *
     * @param status     O status da consulta médica.
     * @param pacienteId O ID do paciente.
     * @param dataInicio A data de início do período.
     * @param dataFim    A data de fim do período.
     * @return Uma lista de consultas médicas que correspondem ao status fornecido, ao ID do paciente e ao período de datas especificado.
     */
    List<ConsultaMedica> findConsultasByStatusAndPacienteAndPeriodo(SituacaoConsultaMedica status, UUID pacienteId, Date dataInicio, Date dataFim);

    /**
     * Busca consultas médicas com base no status, no ID do médico e em um período de datas.
     *
     * @param status     O status da consulta médica.
     * @param medicoId   O ID do médico.
     * @param dataInicio A data de início do período.
     * @param dataFim    A data de fim do período.
     * @return Uma lista de consultas médicas que correspondem ao status fornecido, ao ID do médico e ao período de datas especificado.
     */
    List<ConsultaMedica> findConsultasByStatusAndMedicoAndPeriodo(SituacaoConsultaMedica status, UUID medicoId, Date dataInicio, Date dataFim);

    /**
     * Busca consultas médicas com base no status, no ID do paciente e no ID do médico.
     *
     * @param status     O status da consulta médica.
     * @param pacienteId O ID do paciente.
     * @param medicoId   O ID do médico.
     * @return Uma lista de consultas médicas que correspondem ao status fornecido, ao ID do paciente e ao ID do médico.
     */
    List<ConsultaMedica> findConsultasByStatusAndPacienteAndMedico(SituacaoConsultaMedica status, UUID pacienteId, UUID medicoId);

    /**
     * Busca consultas médicas com base no status, no ID do paciente, no ID do médico e em uma data específica.
     *
     * @param status     O status da consulta médica.
     * @param pacienteId O ID do paciente.
     * @param medicoId   O ID do médico.
     * @param data       A data específica das consultas.
     * @return Uma lista de consultas médicas que correspondem ao status fornecido, ao ID do paciente, ao ID do médico e à data especificada.
     */
    List<ConsultaMedica> findConsultasByStatusAndPacienteAndMedicoAndData(SituacaoConsultaMedica status, UUID pacienteId, UUID medicoId, Date data);
}
