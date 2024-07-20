package br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.out;

import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Especialidade;

import java.util.Optional;
import java.util.UUID;

/**
 * Interface IEspecialidadeRepository define os métodos de acesso ao banco de dados para as especialidades médicas.
 */
public interface IEspecialidadeRepository {

    /**
     * Salva uma nova especialidade no banco de dados.
     *
     * @param especialidade A especialidade a ser salva.
     * @return A especialidade salva.
     */
    Especialidade save(Especialidade especialidade);

    /**
     * Busca uma especialidade pelo seu ID.
     *
     * @param id O ID da especialidade.
     * @return Um Optional contendo a especialidade encontrada ou um Optional vazio se não encontrar.
     */
    Optional<Especialidade> findById(UUID id);

    /**
     * Exclui uma especialidade do banco de dados pelo seu ID.
     *
     * @param id O ID da especialidade a ser excluída.
     */
    void deleteById(UUID id);
}