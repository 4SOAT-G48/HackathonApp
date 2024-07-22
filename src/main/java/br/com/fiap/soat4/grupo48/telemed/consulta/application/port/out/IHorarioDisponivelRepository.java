package br.com.fiap.soat4.grupo48.telemed.consulta.application.port.out;

import br.com.fiap.soat4.grupo48.telemed.consulta.domain.model.HorarioDisponivel;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Interface que define os métodos de acesso a dados de horários disponíveis
 */
public interface IHorarioDisponivelRepository {
    /**
     * Salva um novo horário disponível no banco de dados.
     *
     * @param horarioDisponivel O horário disponível a ser salvo.
     * @return O horário disponível salvo.
     */
    HorarioDisponivel save(HorarioDisponivel horarioDisponivel);

    /**
     * Busca um horário disponível pelo seu ID.
     *
     * @param id O ID do horário disponível.
     * @return Um Optional contendo o horário disponível encontrado ou um Optional vazio se não encontrar.
     */
    Optional<HorarioDisponivel> findById(UUID id);

    /**
     * Exclui um horário disponível do banco de dados pelo seu ID.
     *
     * @param id O ID do horário disponível a ser excluído.
     */
    void deleteById(UUID id);

    /**
     * Busca todos os horários disponíveis cadastrados no sistema.
     *
     * @return Uma lista contendo todos os horários disponíveis cadastrados.
     */
    List<HorarioDisponivel> findAll();

    /**
     * Busca todos os horários disponíveis de um médico em uma data específica.
     *
     * @param medicoId O ID do médico.
     * @param data     A data dos horários disponíveis.
     * @return Uma lista contendo todos os horários disponíveis do médico na data especificada.
     */
    List<HorarioDisponivel> findByMedicoIdAndData(UUID medicoId, Date data);
}
