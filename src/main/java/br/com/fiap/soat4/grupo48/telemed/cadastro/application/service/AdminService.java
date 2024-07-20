package br.com.fiap.soat4.grupo48.telemed.cadastro.application.service;

import br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception.AdminNotFoundException;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.in.IAdminService;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.out.IAdminRepository;
import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Admin;

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
    public Admin cadastrarAdmin(String nome, String email) {
        Admin admin = new Admin();
        admin.setNome(nome);
        admin.setEmail(email);
        return adminRepository.save(admin);
    }

    @Override
    public Admin atualizarAdmin(UUID id, String nome, String email) throws AdminNotFoundException {
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
    public Admin excluirAdmin(UUID id) throws AdminNotFoundException {
        Optional<Admin> adminOptional = adminRepository.findById(id);
        if (adminOptional.isPresent()) {
            adminRepository.deleteById(id);
            return adminOptional.get();
        } else {
            throw new AdminNotFoundException(ADMIN_NAO_ENCONTRADO);
        }
    }

    @Override
    public Admin buscarAdmin(UUID id) throws AdminNotFoundException {
        return adminRepository.findById(id).orElseThrow(() -> new AdminNotFoundException(ADMIN_NAO_ENCONTRADO));
    }

    @Override
    public Admin buscarAdminPorEmail(String email) throws AdminNotFoundException {
        return adminRepository.findByEmail(email).orElseThrow(() -> new AdminNotFoundException(ADMIN_NAO_ENCONTRADO_POR_EMAIL));
    }
}