package br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.in;

import br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception.PacienteIllegalArgumentException;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception.PacienteNotFoundException;
import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Paciente;

import java.util.UUID;

/**
 * Interface IPacienteService define os serviços disponíveis para operações relacionadas a pacientes.
 * Inclui funcionalidades para cadastrar, atualizar, excluir pacientes, e buscar pacientes por ID, email ou CPF.
 */
public interface IPacienteService {

    /**
     * Cadastra um novo paciente com nome, email e CPF fornecidos.
     *
     * @param nome  O nome do paciente a ser cadastrado.
     * @param email O email do paciente a ser cadastrado.
     * @param cpf   O CPF do paciente a ser cadastrado.
     * @return O objeto Paciente cadastrado.
     */
    Paciente cadastrarPaciente(String nome, String email, String cpf) throws PacienteIllegalArgumentException;

    /**
     * Atualiza um paciente existente, identificado pelo ID, com novos valores de nome, email e CPF.
     *
     * @param id    O identificador único do paciente a ser atualizado.
     * @param nome  O novo nome para o paciente.
     * @param email O novo email para o paciente.
     * @param cpf   O novo CPF para o paciente.
     * @return O objeto Paciente atualizado.
     */
    Paciente atualizarPaciente(UUID id, String nome, String email, String cpf) throws PacienteNotFoundException, PacienteIllegalArgumentException;

    /**
     * Exclui um paciente do sistema, identificado pelo ID fornecido.
     *
     * @param id O identificador único do paciente a ser excluído.
     * @return O objeto Paciente excluído.
     */
    Paciente excluirPaciente(UUID id) throws PacienteNotFoundException, PacienteIllegalArgumentException;

    /**
     * Busca um paciente pelo seu identificador único.
     *
     * @param id O identificador único do paciente a ser buscado.
     * @return O objeto Paciente encontrado, ou null caso não seja encontrado.
     */
    Paciente buscarPaciente(UUID id) throws PacienteNotFoundException;

    /**
     * Busca um paciente pelo seu email.
     *
     * @param email O email do paciente a ser buscado.
     * @return O objeto Paciente encontrado, ou null caso não seja encontrado.
     */
    Paciente buscarPacientePorEmail(String email) throws PacienteNotFoundException;

    /**
     * Busca um paciente pelo seu CPF.
     *
     * @param cpf O CPF do paciente a ser buscado.
     * @return O objeto Paciente encontrado, ou null caso não seja encontrado.
     */
    Paciente buscarPacientePorCpf(String cpf) throws PacienteNotFoundException;
}