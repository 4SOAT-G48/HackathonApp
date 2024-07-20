package br.com.fiap.soat4.grupo48.telemed.cadastro.infra.adapter.db;

import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.out.IAdminRepository;
import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Admin;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementação do repositório de Admin que interage com a base de dados.
 * Esta classe serve como um adaptador entre a camada de domínio e a camada de persistência,
 * utilizando o Spring Data JPA para realizar operações no banco de dados.
 */
@Component
public class AdminRepository implements IAdminRepository {

    private final AdminSpringRepository adminSpringRepository;

    /**
     * Construtor que injeta a dependência do repositório Spring Data JPA.
     *
     * @param adminSpringRepository Repositório Spring Data JPA para operações de Admin.
     */
    public AdminRepository(AdminSpringRepository adminSpringRepository) {
        this.adminSpringRepository = adminSpringRepository;
    }

    /**
     * Salva uma entidade Admin no banco de dados.
     *
     * @param admin A entidade Admin a ser salva.
     * @return A entidade Admin salva, incluindo seu identificador único gerado.
     */
    @Override
    public Admin save(Admin admin) {
        AdminEntity adminEntity = convertToEntity(admin);
        AdminEntity savedEntity = adminSpringRepository.save(adminEntity);
        return convertToDomain(savedEntity);
    }

    /**
     * Busca uma entidade Admin pelo seu identificador único.
     *
     * @param id O identificador único do Admin.
     * @return Um Optional contendo a entidade Admin encontrada ou um Optional vazio se não encontrada.
     */
    @Override
    public Optional<Admin> findById(UUID id) {
        Optional<AdminEntity> adminEntity = adminSpringRepository.findById(id);
        return adminEntity.map(this::convertToDomain);
    }

    /**
     * Busca uma entidade Admin pelo seu endereço de email.
     *
     * @param email O endereço de email do Admin.
     * @return Um Optional contendo a entidade Admin encontrada ou um Optional vazio se não encontrada.
     */
    @Override
    public Optional<Admin> findByEmail(String email) {
        Optional<AdminEntity> adminEntity = adminSpringRepository.findByEmail(email);
        return adminEntity.map(this::convertToDomain);
    }

    /**
     * Exclui uma entidade Admin pelo seu identificador único.
     *
     * @param id O identificador único do Admin a ser excluído.
     */
    @Override
    public void deleteById(UUID id) {
        adminSpringRepository.deleteById(id);
    }

    /**
     * Busca todas as entidades Admin cadastradas no banco de dados.
     *
     * @return Uma lista com todas as entidades Admin cadastradas.
     */
    @Override
    public List<Admin> findAll() {
        List<AdminEntity> adminEntities = adminSpringRepository.findAll();
        return adminEntities.stream().map(this::convertToDomain).toList();
    }

    /**
     * Converte uma entidade Admin do domínio para uma entidade AdminEntity de persistência.
     *
     * @param admin A entidade Admin do domínio.
     * @return A entidade AdminEntity de persistência.
     */
    private AdminEntity convertToEntity(Admin admin) {
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setId(admin.getId());
        adminEntity.setNome(admin.getNome());
        adminEntity.setEmail(admin.getEmail());
        adminEntity.setDataCriacao(admin.getDataCriacao());
        adminEntity.setDataAtualizacao(admin.getDataAtualizacao());
        return adminEntity;
    }

    /**
     * Converte uma entidade AdminEntity de persistência para uma entidade Admin do domínio.
     *
     * @param adminEntity A entidade AdminEntity de persistência.
     * @return A entidade Admin do domínio.
     */
    private Admin convertToDomain(AdminEntity adminEntity) {
        Admin admin = new Admin();
        admin.setId(adminEntity.getId());
        admin.setNome(adminEntity.getNome());
        admin.setEmail(adminEntity.getEmail());
        admin.setDataCriacao(adminEntity.getDataCriacao());
        admin.setDataAtualizacao(adminEntity.getDataAtualizacao());
        return admin;
    }
}