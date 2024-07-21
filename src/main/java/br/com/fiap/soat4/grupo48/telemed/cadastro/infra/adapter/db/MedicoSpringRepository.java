package br.com.fiap.soat4.grupo48.telemed.cadastro.infra.adapter.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repositório Spring Data JPA para operações de Médico.
 */
public interface MedicoSpringRepository extends JpaRepository<MedicoEntity, UUID> {
    /**
     * Busca um médico pelo endereço de email.
     *
     * @param email O endereço de email do médico a ser buscado.
     * @return Um {@link Optional} contendo o {@link MedicoEntity} encontrado, ou um {@link Optional#empty()} se nenhum médico for encontrado com o email fornecido.
     */
    Optional<MedicoEntity> findByEmail(String email);

    /**
     * Busca um médico pelo CRM.
     *
     * @param crm O CRM do médico a ser buscado.
     * @return Um {@link Optional} contendo o {@link MedicoEntity} encontrado, ou um {@link Optional#empty()} se nenhum médico for encontrado com o CRM fornecido.
     */
    Optional<MedicoEntity> findByCrm(String crm);
}
