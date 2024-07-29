package br.com.fiap.soat4.grupo48.telemed.cadastro.infra.adapter.db;

import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.out.IPacienteRepository;
import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Paciente;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class PacienteRepository implements IPacienteRepository {

    private final PacienteSpringRepository pacienteSpringRepository;

    public PacienteRepository(PacienteSpringRepository pacienteSpringRepository) {
        this.pacienteSpringRepository = pacienteSpringRepository;
    }

    @Override
    public Paciente save(Paciente paciente) {
        PacienteEntity pacienteEntity = convertToEntity(paciente);
        PacienteEntity savedEntity = pacienteSpringRepository.save(pacienteEntity);
        return convertToDomain(savedEntity);
    }

    @Override
    public Optional<Paciente> findById(UUID id) {
        Optional<PacienteEntity> pacienteEntity = pacienteSpringRepository.findById(id);
        return pacienteEntity.map(this::convertToDomain);
    }

    @Override
    public Optional<Paciente> findByEmail(String email) {
        return pacienteSpringRepository.findByEmail(email).map(this::convertToDomain);
    }

    @Override
    public Optional<Paciente> findByCpf(String cpf) {
        return pacienteSpringRepository.findByCpf(cpf).map(this::convertToDomain);
    }

    @Override
    public void deleteById(UUID id) {
        pacienteSpringRepository.deleteById(id);
    }

    @Override
    public List<Paciente> findAll() {
        List<PacienteEntity> entities = pacienteSpringRepository.findAll();
        return entities.stream().map(this::convertToDomain).toList();
    }

    private PacienteEntity convertToEntity(Paciente paciente) {
        PacienteEntity entity = new PacienteEntity();
        entity.setId(paciente.getId());
        entity.setNome(paciente.getNome());
        entity.setEmail(paciente.getEmail());
        entity.setCpf(paciente.getCpf());
        entity.setDataCriacao(paciente.getDataCriacao());
        entity.setDataAtualizacao(paciente.getDataAtualizacao());
        return entity;
    }

    private Paciente convertToDomain(PacienteEntity entity) {
        Paciente paciente = new Paciente();
        paciente.setId(entity.getId());
        paciente.setNome(entity.getNome());
        paciente.setEmail(entity.getEmail());
        paciente.setCpf(entity.getCpf());
        paciente.setDataCriacao(entity.getDataCriacao());
        paciente.setDataAtualizacao(entity.getDataAtualizacao());
        return paciente;
    }
}