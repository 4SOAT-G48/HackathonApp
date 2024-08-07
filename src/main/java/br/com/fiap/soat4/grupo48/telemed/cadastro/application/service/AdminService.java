package br.com.fiap.soat4.grupo48.telemed.cadastro.application.service;

import br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception.AdminIllegalArgumentException;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception.AdminNotFoundException;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.in.IAdminService;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.out.IAdminRepository;
import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Admin;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class AdminService implements IAdminService {

    public static final String ADMIN_NAO_ENCONTRADO = "Admin não encontrado";
    public static final String ADMIN_NAO_ENCONTRADO_POR_EMAIL = "Admin não encontrado por email";
    private final IAdminRepository adminRepository;

    public AdminService(IAdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin criarAdmin(String nome, String email) throws AdminIllegalArgumentException {
        if (Objects.isNull(nome) || Objects.isNull(email)) {
            throw new AdminIllegalArgumentException("Nome e email são obrigatórios");
        }
        Admin admin = new Admin();
        admin.setNome(nome);
        admin.setEmail(email);
        return adminRepository.save(admin);
    }

    @Override
    public Admin atualizarAdmin(UUID id, String nome, String email) throws AdminNotFoundException, AdminIllegalArgumentException {
        if (Objects.isNull(id) || Objects.isNull(nome) || Objects.isNull(email)) {
            throw new AdminIllegalArgumentException("ID, nome e email são obrigatórios");
        }
        Optional<Admin> adminOptional = adminRepository.findById(id);
        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            admin.setNome(nome);
            admin.setEmail(email);
            return adminRepository.save(admin);
        }
        throw new AdminNotFoundException(ADMIN_NAO_ENCONTRADO);
    }

    @Override
    public void deletarAdmin(UUID id) throws AdminNotFoundException, AdminIllegalArgumentException {
        if (Objects.isNull(id)) {
            throw new AdminIllegalArgumentException("ID é obrigatório");
        }
        Optional<Admin> adminOptional = adminRepository.findById(id);
        if (adminOptional.isPresent()) {
            adminRepository.deleteById(id);
        } else {
            throw new AdminNotFoundException(ADMIN_NAO_ENCONTRADO);
        }
    }

    @Override
    public Admin buscarAdmin(UUID id) throws AdminNotFoundException {
        return adminRepository.findById(id).orElseThrow(() -> new AdminNotFoundException(ADMIN_NAO_ENCONTRADO));
    }

    @Override
    public List<Admin> buscarTodosAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin buscarAdminPorEmail(String email) throws AdminNotFoundException {
        return adminRepository.findByEmail(email).orElseThrow(() -> new AdminNotFoundException(ADMIN_NAO_ENCONTRADO_POR_EMAIL));
    }
}