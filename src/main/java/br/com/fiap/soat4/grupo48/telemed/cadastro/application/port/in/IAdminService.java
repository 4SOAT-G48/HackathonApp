package br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.in;

import br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception.AdminNotFoundException;
import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Admin;

import java.util.UUID;

/**
 * Interface IAdminService define os serviços disponíveis para operações relacionadas a administradores.
 * Inclui funcionalidades para cadastrar, atualizar, excluir, buscar por ID e buscar por email de administradores.
 */
public interface IAdminService {

    /**
     * Cadastra um novo administrador com o nome e email fornecidos.
     *
     * @param nome  O nome do administrador a ser cadastrado.
     * @param email O email do administrador a ser cadastrado.
     * @return O objeto Admin cadastrado.
     */
    Admin cadastrarAdmin(String nome, String email);

    /**
     * Atualiza um administrador existente, identificado pelo ID fornecido, com um novo nome e email.
     *
     * @param id    O identificador único do administrador a ser atualizado.
     * @param nome  O novo nome para o administrador.
     * @param email O novo email para o administrador.
     * @return O objeto Admin atualizado.
     */
    Admin atualizarAdmin(UUID id, String nome, String email) throws AdminNotFoundException;

    /**
     * Exclui um administrador do sistema, identificado pelo ID fornecido.
     *
     * @param id O identificador único do administrador a ser excluído.
     * @return O objeto Admin excluído.
     */
    Admin excluirAdmin(UUID id) throws AdminNotFoundException;

    /**
     * Recupera um administrador pelo seu identificador único.
     *
     * @param id O identificador único do administrador a ser recuperado.
     * @return O objeto Admin encontrado, ou null caso não seja encontrado.
     */
    Admin buscarAdmin(UUID id) throws AdminNotFoundException;

    /**
     * Busca um administrador pelo seu email.
     *
     * @param email O email do administrador a ser buscado.
     * @return O objeto Admin encontrado, ou null caso não seja encontrado.
     */
    Admin buscarAdminPorEmail(String email) throws AdminNotFoundException;
}