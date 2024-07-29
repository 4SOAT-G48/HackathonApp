package br.com.fiap.soat4.grupo48.telemed.consulta.application.port.out;

import br.com.fiap.soat4.grupo48.telemed.consulta.domain.model.ConsultaMedica;
import br.com.fiap.soat4.grupo48.telemed.consulta.domain.model.SituacaoConsultaMedica;

import java.util.Date;
import java.util.List;
import java.util.Optional;
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
    ConsultaMedica save(ConsultaMedica consultaMedica);

    /**
     * Busca uma consulta médica pelo seu ID.
     *
     * @param id O ID da consulta médica.
     * @return A consulta médica encontrada.
     */
    Optional<ConsultaMedica> findById(UUID id);

    /**
     * Busca todas as consultas médicas cadastradas no sistema.
     *
     * @return Uma lista contendo todas as consultas médicas cadastradas.
     */
    List<ConsultaMedica> findByPaciente(UUID pacienteId);

    /**
     * Busca todas as consultas médicas de um médico.
     *
     * @param medicoId O ID do médico.
     * @return Uma lista contendo todas as consultas médicas do médico.
     */
    List<ConsultaMedica> findByMedico(UUID medicoId);

    /**
     * Busca consultas médicas pela data específica.
     *
     * @param data A data para buscar as consultas médicas.
     * @return Uma lista de consultas médicas agendadas para a data especificada.
     */
    List<ConsultaMedica> findByData(Date data);

    /**
     * Busca consultas médicas em um período específico.
     *
     * @param dataInicio A data de início do período para buscar as consultas médicas.
     * @param dataFim    A data de fim do período para buscar as consultas médicas.
     * @return Uma lista de consultas médicas agendadas dentro do período especificado.
     */
    List<ConsultaMedica> findByPeriodo(Date dataInicio, Date dataFim);

    /**
     * Deleta uma consulta médica do banco de dados.
     *
     * @param id O ID da consulta médica a ser deletada.
     */
    void deleteById(UUID id);

    /**
     * Busca todas as consultas médicas cadastradas no sistema.
     *
     * @return Uma lista contendo todas as consultas médicas cadastradas.
     */
    List<ConsultaMedica> findAll();

    /**
     * Verifica se uma consulta médica com o ID fornecido existe no banco de dados.
     *
     * @param id O ID da consulta médica.
     * @return {@code true} se a consulta médica existir, {@code false} caso contrário.
     */
    boolean existsById(UUID id);

    /**
     * Verifica se existe uma consulta médica associada a um horário específico.
     *
     * @param id O ID do horário.
     * @return {@code true} se existir uma consulta médica associada ao horário, {@code false} caso contrário.
     */
    boolean existsByHorarioId(UUID id);

    /**
     * Busca consultas médicas de um médico em um período específico e com um status específico.
     *
     * @param medicoId   O ID do médico.
     * @param status     O status da consulta médica.
     * @param dataInicio A data de início do período para buscar as consultas médicas.
     * @param dataFim    A data de fim do período para buscar as consultas médicas.
     * @return Uma lista de consultas médicas do médico com o status especificado dentro do período especificado.
     */
    List<ConsultaMedica> findByMedicoAndStatusAndPeriodo(UUID medicoId, SituacaoConsultaMedica status, Date dataInicio, Date dataFim);
}
