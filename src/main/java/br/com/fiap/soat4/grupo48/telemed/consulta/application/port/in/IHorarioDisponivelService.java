package br.com.fiap.soat4.grupo48.telemed.consulta.application.port.in;

import br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception.MedicoIllegalArgumentException;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception.MedicoNotFoundException;
import br.com.fiap.soat4.grupo48.telemed.consulta.application.exception.HorarioDisponivelIllegalArgumentException;
import br.com.fiap.soat4.grupo48.telemed.consulta.domain.model.HorarioDisponivel;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface IHorarioDisponivelService {

    /**
     * Cria um novo horário disponível no banco de dados.
     *
     * @param horarioDisponivel O objeto HorarioDisponivel contendo as informações do novo horário.
     * @return O objeto HorarioDisponivel salvo com um ID gerado.
     */
    HorarioDisponivel criarHorarioDisponivel(HorarioDisponivel horarioDisponivel) throws MedicoIllegalArgumentException, MedicoNotFoundException, HorarioDisponivelIllegalArgumentException;

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
     * Atualiza as informações de um horário disponível existente.
     *
     * @param id                O UUID do horário disponível a ser atualizado.
     * @param horarioDisponivel O objeto HorarioDisponivel contendo as novas informações.
     * @return O objeto HorarioDisponivel atualizado.
     */
    HorarioDisponivel atualizarHorarioDisponivel(UUID id, HorarioDisponivel horarioDisponivel) throws HorarioDisponivelIllegalArgumentException;

    /**
     * Remove um horário disponível do sistema pelo seu identificador único.
     *
     * @param id O UUID do horário disponível a ser removido.
     */
    void deletarPorId(UUID id) throws HorarioDisponivelIllegalArgumentException;
}