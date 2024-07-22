package br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.out;

import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Paciente;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    Optional<Paciente> findById(UUID id);

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
    void deleteById(UUID id);

    /**
     * Busca todos os pacientes cadastrados no sistema.
     *
     * @return Uma lista contendo todos os pacientes cadastrados.
     */
    List<Paciente> findAll();
}