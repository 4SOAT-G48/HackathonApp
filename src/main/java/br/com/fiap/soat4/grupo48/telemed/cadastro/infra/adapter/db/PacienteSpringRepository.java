package br.com.fiap.soat4.grupo48.telemed.cadastro.infra.adapter.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repositório Spring Data JPA para operações de Paciente.
 */
public interface PacienteSpringRepository extends JpaRepository<PacienteEntity, UUID> {
    /**
     * Busca um paciente pelo CPF.
     *
     * @param cpf O CPF do paciente a ser buscado.
     * @return Um {@link Optional} contendo o {@link PacienteEntity} encontrado, ou um {@link Optional#empty()} se nenhum paciente for encontrado com o CPF fornecido.
     */
    Optional<PacienteEntity> findByCpf(String cpf);

    /**
     * Busca um paciente pelo email.
     *
     * @param email O email do paciente a ser buscado.
     * @return Um {@link Optional} contendo o {@link PacienteEntity} encontrado, ou um {@link Optional#empty()} se nenhum paciente for encontrado com o email fornecido.
     */
    Optional<PacienteEntity> findByEmail(String email);
}
