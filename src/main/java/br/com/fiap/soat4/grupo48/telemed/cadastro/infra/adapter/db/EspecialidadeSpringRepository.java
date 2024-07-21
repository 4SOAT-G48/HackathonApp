package br.com.fiap.soat4.grupo48.telemed.cadastro.infra.adapter.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repositório Spring Data JPA para operações de Especialidade.
 */
public interface EspecialidadeSpringRepository extends JpaRepository<EspecialidadeEntity, UUID> {
    /**
     * Busca uma especialidade pela descrição.
     *
     * @param descricao A descrição da especialidade a ser buscada.
     * @return Um {@link Optional} contendo a {@link EspecialidadeEntity} encontrada, ou um {@link Optional#empty()} se nenhuma especialidade for encontrada com a descrição fornecida.
     */
    Optional<EspecialidadeEntity> findByDescricao(String descricao);
}
