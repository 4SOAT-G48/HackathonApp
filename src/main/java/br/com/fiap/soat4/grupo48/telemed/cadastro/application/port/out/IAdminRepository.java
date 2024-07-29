package br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.out;

import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Admin;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Interface IAdminRepository define os métodos para interação com o banco de dados para a entidade Admin.
 * Permite operações de salvar, buscar por ID, buscar por email e deletar um Admin.
 */
public interface IAdminRepository {
    /**
     * Salva um novo admin ou atualiza um existente no banco de dados.
     *
     * @param admin O objeto Admin a ser salvo.
     * @return O objeto Admin salvo.
     */
    Admin save(Admin admin);

    /**
     * Busca um admin pelo seu identificador único.
     *
     * @param id O identificador único do admin.
     * @return Um Optional contendo o Admin encontrado, ou um Optional vazio se nenhum for encontrado.
     */
    Optional<Admin> findById(UUID id);

    /**
     * Busca um admin pelo seu email.
     *
     * @param email O email do admin a ser buscado.
     * @return Um Optional contendo o Admin encontrado, ou um Optional vazio se nenhum for encontrado.
     */
    Optional<Admin> findByEmail(String email);

    /**
     * Deleta um admin pelo seu identificador único.
     *
     * @param id O identificador único do admin a ser deletado.
     */
    void deleteById(UUID id);

    /**
     * Busca todos os admins cadastrados no banco de dados.
     *
     * @return Uma lista com todos os admins cadastrados.
     */
    List<Admin> findAll();
}
