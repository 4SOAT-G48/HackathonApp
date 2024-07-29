package br.com.fiap.soat4.grupo48.telemed.consulta.application.port.in;

import br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception.MedicoIllegalArgumentException;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception.MedicoNotFoundException;
import br.com.fiap.soat4.grupo48.telemed.consulta.application.exception.HorarioDisponivelIllegalArgumentException;
import br.com.fiap.soat4.grupo48.telemed.consulta.domain.model.HorarioDisponivel;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface IHorarioDisponivelService {

    /**
     * Cria um novo horário disponível no banco de dados.
     *
     * @param medicoId
     * @param data
     * @param horaInicio
     * @param horaFim
     * @return O objeto HorarioDisponivel salvo com um ID gerado.
     */
    HorarioDisponivel criarHorarioDisponivel(UUID medicoId, Date data, LocalTime horaInicio, LocalTime horaFim) throws MedicoIllegalArgumentException, MedicoNotFoundException, HorarioDisponivelIllegalArgumentException;

    /**
     * Busca um horário disponível pelo seu identificador único.
     *
     * @param id O UUID do horário disponível a ser buscado.
     * @return O objeto HorarioDisponivel correspondente ao ID fornecido, se encontrado.
     */
    HorarioDisponivel buscarPorId(UUID id) throws HorarioDisponivelIllegalArgumentException;

    /**
     * Lista todos os horários disponíveis cadastrados no sistema.
     *
     * @return Uma lista contendo todos os objetos HorarioDisponivel cadastrados.
     */
    List<HorarioDisponivel> listarTodos();

    /**
     * Busca horários disponíveis de um determinado médico em uma data específica.
     *
     * @param medicoId O UUID do médico cujos horários disponíveis são buscados.
     * @param data     A data específica para a qual os horários disponíveis são buscados.
     * @return Uma lista de objetos HorarioDisponivel que correspondem aos critérios de busca.
     */
    List<HorarioDisponivel> buscarPorMedicoEData(UUID medicoId, Date data);

    /**
     * Atualiza um horário disponível no sistema.
     *
     * @param id         O UUID do horário disponível a ser atualizado.
     * @param data       A nova data do horário disponível.
     * @param horaInicio A nova hora de início do horário disponível.
     * @param horaFim    A nova hora de fim do horário disponível.
     * @return O objeto HorarioDisponivel atualizado.
     */
    HorarioDisponivel atualizarHorarioDisponivel(UUID id, Date data, LocalTime horaInicio, LocalTime horaFim) throws HorarioDisponivelIllegalArgumentException;

    /**
     * Remove um horário disponível do sistema pelo seu identificador único.
     *
     * @param id O UUID do horário disponível a ser removido.
     */
    void deletarPorId(UUID id) throws HorarioDisponivelIllegalArgumentException;

    /**
     * Lista todos os horários disponíveis de um determinado médico.
     *
     * @param id O UUID do médico cujos horários disponíveis são buscados.
     * @return Uma lista de objetos HorarioDisponivel que correspondem ao médico fornecido.
     */
    List<HorarioDisponivel> listarPorMedico(UUID id);
}