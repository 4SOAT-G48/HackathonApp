package br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.out;

import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Medico;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Interface IMedicoRepository define os métodos de acesso ao banco de dados para os médicos.
 */
public interface IMedicoRepository {

    /**
     * Salva um novo médico no banco de dados.
     *
     * @param medico O médico a ser salvo.
     * @return O médico salvo.
     */
    Medico save(Medico medico);

    /**
     * Busca um médico pelo seu ID.
     *
     * @param id O ID do médico.
     * @return Um Optional contendo o médico encontrado ou um Optional vazio se não encontrar.
     */
    Optional<Medico> findById(UUID id);

    /**
     * Exclui um médico do banco de dados pelo seu ID.
     *
     * @param id O ID do médico a ser excluído.
     */
    void deleteById(UUID id);

    Optional<Medico> findByEmail(String email);

    Optional<Medico> findByCrm(String crm);

    List<Medico> findAll();
}