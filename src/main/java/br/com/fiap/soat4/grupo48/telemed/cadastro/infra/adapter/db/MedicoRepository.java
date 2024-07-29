package br.com.fiap.soat4.grupo48.telemed.cadastro.infra.adapter.db;

import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.out.IMedicoRepository;
import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Especialidade;
import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Medico;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implementação do repositório de Médico que interage com a base de dados.
 * Esta classe serve como um adaptador entre a camada de domínio e a camada de persistência,
 * utilizando o Spring Data JPA para realizar operações no banco de dados.
 */
@Component
public class MedicoRepository implements IMedicoRepository {

    private final MedicoSpringRepository medicoSpringRepository;

    public MedicoRepository(MedicoSpringRepository medicoSpringRepository) {
        this.medicoSpringRepository = medicoSpringRepository;
    }

    @Override
    public Medico save(Medico medico) {
        MedicoEntity medicoEntity = convertToEntity(medico);
        MedicoEntity savedEntity = medicoSpringRepository.save(medicoEntity);
        return convertToDomain(savedEntity);
    }

    @Override
    public Optional<Medico> findById(UUID id) {
        Optional<MedicoEntity> medicoEntity = medicoSpringRepository.findById(id);
        return medicoEntity.map(this::convertToDomain);
    }

    @Override
    public void deleteById(UUID id) {
        medicoSpringRepository.deleteById(id);
    }

    @Override
    public Optional<Medico> findByEmail(String email) {
        return medicoSpringRepository.findByEmail(email).map(this::convertToDomain);
    }

    @Override
    public Optional<Medico> findByCrm(String crm) {
        return medicoSpringRepository.findByCrm(crm).map(this::convertToDomain);
    }

    @Override
    public List<Medico> findAll() {
        List<MedicoEntity> entities = medicoSpringRepository.findAll();
        return entities.stream().map(this::convertToDomain).toList();
    }

    @Override
    public List<Medico> findByEspecialidadesId(UUID especialidadeId) {
        List<MedicoEntity> entities = medicoSpringRepository.findByEspecialidadesId(especialidadeId);
        return entities.stream().map(this::convertToDomain).toList();
    }

    @NotNull
    private MedicoEntity convertToEntity(@NotNull Medico medico) {
        MedicoEntity entity = new MedicoEntity();
        entity.setId(medico.getId());
        entity.setNome(medico.getNome());
        entity.setEmail(medico.getEmail());
        entity.setCrm(medico.getCrm());
        entity.setDataCriacao(medico.getDataCriacao());
        entity.setDataAtualizacao(medico.getDataAtualizacao());
        entity.setEspecialidades(medico.getEspecialidades().stream().map(this::convertToEntity).collect(Collectors.toList()));
        return entity;
    }

    @NotNull
    private Medico convertToDomain(@NotNull MedicoEntity entity) {
        Medico medico = new Medico();
        medico.setId(entity.getId());
        medico.setNome(entity.getNome());
        medico.setEmail(entity.getEmail());
        medico.setCrm(entity.getCrm());
        medico.setDataCriacao(entity.getDataCriacao());
        medico.setDataAtualizacao(entity.getDataAtualizacao());
        medico.setEspecialidades(entity.getEspecialidades().stream().map(this::convertToDomain).collect(Collectors.toList()));
        return medico;
    }


    private EspecialidadeEntity convertToEntity(Especialidade especialidade) {
        EspecialidadeEntity entity = new EspecialidadeEntity();
        entity.setId(especialidade.getId());
        entity.setCodigo(especialidade.getCodigo());
        entity.setDescricao(especialidade.getDescricao());
        entity.setDataCriacao(especialidade.getDataCriacao());
        entity.setDataAtualizacao(especialidade.getDataAtualizacao());
        return entity;
    }

    private Especialidade convertToDomain(EspecialidadeEntity entity) {
        Especialidade especialidade = new Especialidade();
        especialidade.setId(entity.getId());
        especialidade.setCodigo(entity.getCodigo());
        especialidade.setDescricao(entity.getDescricao());
        especialidade.setDataCriacao(entity.getDataCriacao());
        especialidade.setDataAtualizacao(entity.getDataAtualizacao());
        return especialidade;
    }
}