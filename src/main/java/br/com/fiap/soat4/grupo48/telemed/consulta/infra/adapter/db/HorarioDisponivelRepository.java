package br.com.fiap.soat4.grupo48.telemed.consulta.infra.adapter.db;

import br.com.fiap.soat4.grupo48.telemed.consulta.application.port.out.IHorarioDisponivelRepository;
import br.com.fiap.soat4.grupo48.telemed.consulta.domain.model.HorarioDisponivel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class HorarioDisponivelRepository implements IHorarioDisponivelRepository {

    private final HorarioDisponivelSpringRepository horarioDisponivelSpringRepository;

    @Autowired
    public HorarioDisponivelRepository(HorarioDisponivelSpringRepository horarioDisponivelSpringRepository) {
        this.horarioDisponivelSpringRepository = horarioDisponivelSpringRepository;
    }


    @Override
    public HorarioDisponivel save(HorarioDisponivel horarioDisponivel) {
        HorarioDisponivelEntity horarioDisponivelEntity = convertToEntity(horarioDisponivel);
        HorarioDisponivelEntity savedEntity = horarioDisponivelSpringRepository.save(horarioDisponivelEntity);
        return convertToDomain(savedEntity);
    }

    @Override
    public Optional<HorarioDisponivel> findById(UUID id) {
        Optional<HorarioDisponivelEntity> horarioDisponivelEntity = horarioDisponivelSpringRepository.findById(id);
        return horarioDisponivelEntity.map(this::convertToDomain);
    }

    @Override
    public void deleteById(UUID id) {
        horarioDisponivelSpringRepository.deleteById(id);
    }

    @Override
    public List<HorarioDisponivel> findAll() {
        List<HorarioDisponivelEntity> entities = horarioDisponivelSpringRepository.findAll();
        return entities.stream().map(this::convertToDomain).toList();
    }

    @Override
    public List<HorarioDisponivel> findByMedicoIdAndData(UUID medicoId, Date data) {
        List<HorarioDisponivelEntity> entities = horarioDisponivelSpringRepository.findByMedicoIdAndData(medicoId, data);
        return entities.stream().map(this::convertToDomain).toList();
    }

    @Override
    public List<HorarioDisponivel> findByMedicoIdAndDataAndIdNot(UUID medicoId, Date data, UUID id) {
        List<HorarioDisponivelEntity> entities = horarioDisponivelSpringRepository.findByMedicoIdAndDataAndIdNot(medicoId, data, id);
        return entities.stream().map(this::convertToDomain).toList();
    }

    @Override
    public List<HorarioDisponivel> findByMedicoIdAndDataGreaterThanEqual(UUID id, Date date) {
        List<HorarioDisponivelEntity> entities = horarioDisponivelSpringRepository.findByMedicoIdAndDataGreaterThanEqual(id, date);
        return entities.stream().map(this::convertToDomain).toList();
    }

    private HorarioDisponivelEntity convertToEntity(HorarioDisponivel horarioDisponivel) {
        HorarioDisponivelEntity entity = new HorarioDisponivelEntity();
        entity.setId(horarioDisponivel.getId());
        entity.setMedicoId(horarioDisponivel.getMedicoId());
        entity.setData(horarioDisponivel.getData());
        entity.setHoraInicio(horarioDisponivel.getHoraInicio());
        entity.setHoraFim(horarioDisponivel.getHoraFim());
        entity.setDataCriacao(horarioDisponivel.getDataCriacao());
        entity.setDataAtualizacao(horarioDisponivel.getDataAtualizacao());
        return entity;
    }

    private HorarioDisponivel convertToDomain(HorarioDisponivelEntity entity) {
        HorarioDisponivel horarioDisponivel = new HorarioDisponivel();
        horarioDisponivel.setId(entity.getId());
        horarioDisponivel.setMedicoId(entity.getMedicoId());
        horarioDisponivel.setData(entity.getData());
        horarioDisponivel.setHoraInicio(entity.getHoraInicio());
        horarioDisponivel.setHoraFim(entity.getHoraFim());
        horarioDisponivel.setDataCriacao(entity.getDataCriacao());
        horarioDisponivel.setDataAtualizacao(entity.getDataAtualizacao());
        return horarioDisponivel;
    }
}