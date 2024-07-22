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

    /**
     * Busca um médico pelo seu email.
     *
     * @param email O email do médico.
     * @return Um Optional contendo o médico encontrado ou um Optional vazio se não encontrar.
     */
    Optional<Medico> findByEmail(String email);

    /**
     * Busca um médico pelo seu CRM.
     *
     * @param crm O CRM do médico.
     * @return Um Optional contendo o médico encontrado ou um Optional vazio se não encontrar.
     */
    Optional<Medico> findByCrm(String crm);

    /**
     * Busca todos os médicos cadastrados no sistema.
     *
     * @return Uma lista de médicos, que pode estar vazia caso não existam médicos cadastrados.
     */
    List<Medico> findAll();

    /**
     * Busca todos os médicos que possuem uma especialidade com o ID fornecido.
     *
     * @param especialidadeId O ID da especialidade a ser buscada.
     * @return Uma lista de médicos que possuem a especialidade com o ID fornecido.
     */
    List<Medico> findByEspecialidadesId(UUID especialidadeId);
}