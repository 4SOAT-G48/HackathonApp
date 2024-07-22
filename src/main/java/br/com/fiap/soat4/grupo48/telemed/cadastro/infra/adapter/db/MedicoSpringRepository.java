package br.com.fiap.soat4.grupo48.telemed.cadastro.infra.adapter.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
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

    /**
     * Busca todos os médicos que possuem uma especialidade com o ID fornecido.
     *
     * @param especialidadeId O ID da especialidade a ser buscada.
     * @return Uma lista de {@link MedicoEntity} que possuem a especialidade com o ID fornecido.
     */
    @Query("SELECT m FROM MedicoEntity m JOIN m.especialidades e WHERE e.id = :especialidadeId")
    List<MedicoEntity> findByEspecialidadesId(UUID especialidadeId);
}

