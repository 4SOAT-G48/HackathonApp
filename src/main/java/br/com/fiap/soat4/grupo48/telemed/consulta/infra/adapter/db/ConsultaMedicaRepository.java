package br.com.fiap.soat4.grupo48.telemed.consulta.infra.adapter.db;

import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Medico;
import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Paciente;
import br.com.fiap.soat4.grupo48.telemed.cadastro.infra.adapter.db.MedicoEntity;
import br.com.fiap.soat4.grupo48.telemed.cadastro.infra.adapter.db.PacienteEntity;
import br.com.fiap.soat4.grupo48.telemed.consulta.application.port.out.IConsultaMedicaRepository;
import br.com.fiap.soat4.grupo48.telemed.consulta.domain.model.ConsultaMedica;
import br.com.fiap.soat4.grupo48.telemed.consulta.domain.model.SituacaoConsultaMedica;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ConsultaMedicaRepository implements IConsultaMedicaRepository {

    private final ConsultaMedicaSpringRepository consultaMedicaSpringRepository;

    public ConsultaMedicaRepository(ConsultaMedicaSpringRepository consultaMedicaSpringRepository) {
        this.consultaMedicaSpringRepository = consultaMedicaSpringRepository;
    }

    @Override
    public ConsultaMedica save(ConsultaMedica consultaMedica) {
        ConsultaMedicaEntity entity = convertToEntity(consultaMedica);
        ConsultaMedicaEntity savedEntity = consultaMedicaSpringRepository.save(entity);
        return convertToDomain(savedEntity);
    }

    @Override
    public Optional<ConsultaMedica> findById(UUID id) {
        Optional<ConsultaMedicaEntity> entity = consultaMedicaSpringRepository.findById(id);
        return entity.map(this::convertToDomain);
    }

    @Override
    public List<ConsultaMedica> findByPaciente(UUID pacienteId) {
        List<ConsultaMedicaEntity> entities = consultaMedicaSpringRepository.findByPacienteId(pacienteId);
        return entities.stream().map(this::convertToDomain).toList();
    }

    @Override
    public List<ConsultaMedica> findByMedico(UUID medicoId) {
        List<ConsultaMedicaEntity> entities = consultaMedicaSpringRepository.findByMedicoId(medicoId);
        return entities.stream().map(this::convertToDomain).toList();
    }

    @Override
    public List<ConsultaMedica> findByData(Date data) {
        List<ConsultaMedicaEntity> entities = consultaMedicaSpringRepository.findByData(data);
        return entities.stream().map(this::convertToDomain).toList();
    }

    @Override
    public List<ConsultaMedica> findByPeriodo(Date dataInicio, Date dataFim) {
        List<ConsultaMedicaEntity> entities = consultaMedicaSpringRepository.findByPeriodo(dataInicio, dataFim);
        return entities.stream().map(this::convertToDomain).toList();
    }

    @Override
    public void deleteById(UUID id) {
        consultaMedicaSpringRepository.deleteById(id);
    }

    @Override
    public List<ConsultaMedica> findAll() {
        List<ConsultaMedicaEntity> entities = consultaMedicaSpringRepository.findAll();
        return entities.stream().map(this::convertToDomain).toList();
    }

    @Override
    public boolean existsById(UUID id) {
        return consultaMedicaSpringRepository.existsById(id);
    }

    @Override
    public boolean existsByHorarioId(UUID id) {
        return consultaMedicaSpringRepository.existsByHorarioId(id);
    }

    @Override
    public List<ConsultaMedica> findByMedicoAndStatusAndPeriodo(UUID medicoId, SituacaoConsultaMedica status, Date dataInicio, Date dataFim) {
        List<ConsultaMedicaEntity> entities = consultaMedicaSpringRepository.findByMedicoIdAndStatusAndPeriodo(medicoId, status, dataInicio, dataFim);
        return entities.stream().map(this::convertToDomain).toList();
    }

    private ConsultaMedicaEntity convertToEntity(ConsultaMedica consultaMedica) {
        ConsultaMedicaEntity entity = new ConsultaMedicaEntity();
        entity.setId(consultaMedica.getId());
        entity.setPaciente(convertToEntity(consultaMedica.getPaciente()));
        entity.setMedico(convertToEntity(consultaMedica.getMedico()));
        entity.setStatus(consultaMedica.getStatus().toString());
        entity.setDataCriacao(consultaMedica.getDataCriacao());
        entity.setDataAtualizacao(consultaMedica.getDataAtualizacao());
        return entity;
    }

    private ConsultaMedica convertToDomain(ConsultaMedicaEntity entity) {
        ConsultaMedica consultaMedica = new ConsultaMedica();
        consultaMedica.setId(entity.getId());
        consultaMedica.setPaciente(convertToDomain(entity.getPaciente()));
        consultaMedica.setMedico(convertToDomain(entity.getMedico()));
        consultaMedica.setStatus(SituacaoConsultaMedica.valueOf(entity.getStatus()));
        consultaMedica.setDataCriacao(entity.getDataCriacao());
        consultaMedica.setDataAtualizacao(entity.getDataAtualizacao());
        return consultaMedica;
    }

    private PacienteEntity convertToEntity(Paciente paciente) {
        PacienteEntity entity = new PacienteEntity();
        entity.setId(paciente.getId());
        entity.setNome(paciente.getNome());
        entity.setEmail(paciente.getEmail());
        entity.setDataCriacao(paciente.getDataCriacao());
        entity.setDataAtualizacao(paciente.getDataAtualizacao());
        return entity;
    }

    private Paciente convertToDomain(PacienteEntity entity) {
        Paciente paciente = new Paciente();
        paciente.setId(entity.getId());
        paciente.setNome(entity.getNome());
        paciente.setEmail(entity.getEmail());
        paciente.setDataCriacao(entity.getDataCriacao());
        paciente.setDataAtualizacao(entity.getDataAtualizacao());
        return paciente;
    }

    private MedicoEntity convertToEntity(Medico medico) {
        MedicoEntity entity = new MedicoEntity();
        entity.setId(medico.getId());
        entity.setNome(medico.getNome());
        entity.setEmail(medico.getEmail());
        entity.setCrm(medico.getCrm());
        entity.setDataCriacao(medico.getDataCriacao());
        entity.setDataAtualizacao(medico.getDataAtualizacao());
        return entity;
    }

    private Medico convertToDomain(MedicoEntity entity) {
        Medico medico = new Medico();
        medico.setId(entity.getId());
        medico.setNome(entity.getNome());
        medico.setEmail(entity.getEmail());
        medico.setCrm(entity.getCrm());
        medico.setDataCriacao(entity.getDataCriacao());
        medico.setDataAtualizacao(entity.getDataAtualizacao());
        return medico;
    }
}