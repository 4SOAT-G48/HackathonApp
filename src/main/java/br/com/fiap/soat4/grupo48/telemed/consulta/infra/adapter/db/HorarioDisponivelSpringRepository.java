package br.com.fiap.soat4.grupo48.telemed.consulta.infra.adapter.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Repositório Spring Data JPA para entidades HorarioDisponivel.
 * <p>
 * Este repositório facilita operações de banco de dados para a entidade HorarioDisponivel,
 * como buscar, salvar e deletar horários disponíveis no sistema.
 */
public interface HorarioDisponivelSpringRepository extends JpaRepository<HorarioDisponivelEntity, UUID> {

    /**
     * Busca horários disponíveis de um médico específico em uma data específica.
     *
     * @param medicoId O identificador único do médico.
     * @param data     A data específica para a qual os horários disponíveis são buscados.
     * @return Uma lista de entidades HorarioDisponivel que correspondem aos critérios de busca.
     */
    List<HorarioDisponivelEntity> findByMedicoIdAndData(UUID medicoId, Date data);

    /**
     * Busca horários disponíveis de um médico específico em uma data posterior ou igual à data especificada.
     *
     * @param id   O identificador único do médico.
     * @param date A data a partir da qual os horários disponíveis são buscados.
     * @return Uma lista de entidades HorarioDisponivel que correspondem aos critérios de busca.
     */
    List<HorarioDisponivelEntity> findByMedicoIdAndDataGreaterThanEqual(UUID id, Date date);

    /**
     * Busca horários disponíveis de um médico específico em uma data específica, exceto o horário com o ID especificado.
     *
     * @param medicoId O identificador único do médico.
     * @param data     A data específica para a qual os horários disponíveis são buscados.
     * @param id       O identificador único do horário disponível a ser excluído da busca.
     * @return Uma lista de entidades HorarioDisponivel que correspondem aos critérios de busca.
     */
    List<HorarioDisponivelEntity> findByMedicoIdAndDataAndIdNot(UUID medicoId, Date data, UUID id);
}
