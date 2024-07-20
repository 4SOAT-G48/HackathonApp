package br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.out;

import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Paciente;

import java.util.Optional;

/**
 * Interface IPacienteRepository define os métodos de acesso ao banco de dados para os pacientes.
 */
public interface IPacienteRepository {

    /**
     * Salva um novo paciente no banco de dados.
     *
     * @param paciente O paciente a ser salvo.
     * @return O paciente salvo.
     */
    Paciente save(Paciente paciente);

    /**
     * Busca um paciente pelo seu ID.
     *
     * @param id O ID do paciente.
     * @return Um Optional contendo o paciente encontrado ou um Optional vazio se não encontrar.
     */
    Optional<Paciente> findById(String id);

    /**
     * Busca um paciente pelo seu email.
     *
     * @param email O email do paciente.
     * @return Um Optional contendo o paciente encontrado ou um Optional vazio se não encontrar.
     */
    Optional<Paciente> findByEmail(String email);

    /**
     * Busca um paciente pelo seu CPF.
     *
     * @param cpf O CPF do paciente.
     * @return Um Optional contendo o paciente encontrado ou um Optional vazio se não encontrar.
     */
    Optional<Paciente> findByCpf(String cpf);

    /**
     * Exclui um paciente do banco de dados pelo seu ID.
     *
     * @param id O ID do paciente a ser excluído.
     */
    void deleteById(String id);
}