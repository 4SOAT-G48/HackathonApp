package br.com.fiap.soat4.grupo48.telemed.cadastro.infra.adapter.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Interface para o repositório de Admin, estendendo JpaRepository.
 * Esta interface permite a realização de operações de banco de dados para entidades Admin.
 * Utiliza Spring Data JPA para abstrair e simplificar o acesso ao banco de dados.
 */
public interface AdminSpringRepository extends JpaRepository<AdminEntity, UUID> {

    /**
     * Busca uma entidade Admin pelo seu endereço de email.
     *
     * @param email O endereço de email do Admin a ser buscado.
     * @return Um Optional contendo a entidade AdminEntity encontrada ou um Optional vazio se não encontrada.
     */
    Optional<AdminEntity> findByEmail(String email);
}