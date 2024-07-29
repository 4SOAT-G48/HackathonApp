package br.com.fiap.soat4.grupo48.telemed.cadastro.infra.adapter.db;

import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.out.IEspecialidadeRepository;
import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Especialidade;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class EspecialidadeRepository implements IEspecialidadeRepository {

    private final EspecialidadeSpringRepository especialidadeSpringRepository;

    public EspecialidadeRepository(EspecialidadeSpringRepository especialidadeSpringRepository) {
        this.especialidadeSpringRepository = especialidadeSpringRepository;
    }

    @Override
    public Especialidade save(Especialidade especialidade) {
        EspecialidadeEntity especialidadeEntity = convertToEntity(especialidade);
        EspecialidadeEntity savedEntity = especialidadeSpringRepository.save(especialidadeEntity);
        return convertToDomain(savedEntity);
    }

    @Override
    public Optional<Especialidade> findById(UUID id) {
        Optional<EspecialidadeEntity> especialidadeEntity = especialidadeSpringRepository.findById(id);
        return especialidadeEntity.map(this::convertToDomain);
    }

    @Override
    public void deleteById(UUID id) {
        especialidadeSpringRepository.deleteById(id);
    }

    @Override
    public List<Especialidade> findAll() {
        List<EspecialidadeEntity> entities = especialidadeSpringRepository.findAll();
        return entities.stream().map(this::convertToDomain).toList();
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