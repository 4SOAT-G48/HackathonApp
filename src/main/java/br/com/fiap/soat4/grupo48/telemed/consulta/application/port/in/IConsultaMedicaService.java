package br.com.fiap.soat4.grupo48.telemed.consulta.application.port.in;

import br.com.fiap.soat4.grupo48.telemed.consulta.application.exception.ConsultaMedicaIllegalArgumentException;
import br.com.fiap.soat4.grupo48.telemed.consulta.application.exception.ConsultaMedicaNotFoundException;
import br.com.fiap.soat4.grupo48.telemed.consulta.application.exception.HorarioDisponivelNotFoundException;
import br.com.fiap.soat4.grupo48.telemed.consulta.domain.model.ConsultaMedica;
import br.com.fiap.soat4.grupo48.telemed.consulta.domain.model.SituacaoConsultaMedica;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface IConsultaMedicaService {

    /**
     * Cria uma nova consulta médica.
     *
     * @param consultaMedica A consulta médica a ser criada.
     * @return A consulta médica criada.
     */
    ConsultaMedica criarConsultaMedica(ConsultaMedica consultaMedica) throws ConsultaMedicaIllegalArgumentException, HorarioDisponivelNotFoundException;

    /**
     * Busca uma consulta médica pelo ID.
     *
     * @param id O identificador único da consulta médica.
     * @return A consulta médica encontrada.
     */
    ConsultaMedica buscarPorId(UUID id) throws ConsultaMedicaNotFoundException;

    /**
     * Lista todas as consultas médicas.
     *
     * @return Uma lista de todas as consultas médicas.
     */
    List<ConsultaMedica> listarTodas();

    /**
     * Atualiza uma consulta médica existente.
     *
     * @param id             O identificador único da consulta médica.
     * @param consultaMedica A consulta médica com os dados atualizados.
     * @return A consulta médica atualizada.
     */
    ConsultaMedica atualizarConsultaMedica(UUID id, ConsultaMedica consultaMedica);

    /**
     * Deleta uma consulta médica pelo ID.
     *
     * @param id O identificador único da consulta médica.
     */
    void deletarPorId(UUID id);

    /**
     * Lista todas as consultas médicas de um paciente específico.
     *
     * @param pacienteId O identificador único do paciente.
     * @return Uma lista de consultas médicas do paciente.
     */
    List<ConsultaMedica> listarPorPaciente(UUID pacienteId);

    /**
     * Lista todas as consultas médicas de um médico específico.
     *
     * @param medicoId O identificador único do médico.
     * @return Uma lista de consultas médicas do médico.
     */
    List<ConsultaMedica> listarPorMedico(UUID medicoId);

    /**
     * Lista todas as consultas médicas em uma data específica.
     *
     * @param data A data específica para a qual as consultas médicas são listadas.
     * @return Uma lista de consultas médicas na data especificada.
     */
    List<ConsultaMedica> listarPorData(Date data);

    /**
     * Lista todas as consultas médicas em um período específico.
     *
     * @param dataInicio A data de início do período.
     * @param dataFim    A data de fim do período.
     * @return Uma lista de consultas médicas no período especificado.
     */
    List<ConsultaMedica> listarPorPeriodo(Date dataInicio, Date dataFim);

    /**
     * Lista consultas médicas de um médico em um período específico e com um status específico.
     *
     * @param medicoId   O ID do médico.
     * @param status     O status da consulta médica.
     * @param dataInicio A data de início do período para buscar as consultas médicas.
     * @param dataFim    A data de fim do período para buscar as consultas médicas.
     * @return Uma lista de consultas médicas do médico com o status especificado dentro do período especificado.
     */
    List<ConsultaMedica> listarPorMedicoStatusPeriodo(UUID medicoId, SituacaoConsultaMedica status, Date dataInicio, Date dataFim);
}