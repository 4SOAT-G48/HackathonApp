package br.com.fiap.soat4.grupo48.telemed.consulta.application.service;

import br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception.MedicoNotFoundException;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception.PacienteNotFoundException;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.out.IMedicoRepository;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.out.IPacienteRepository;
import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Medico;
import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Paciente;
import br.com.fiap.soat4.grupo48.telemed.consulta.application.exception.ConsultaMedicaIllegalArgumentException;
import br.com.fiap.soat4.grupo48.telemed.consulta.application.exception.ConsultaMedicaNotFoundException;
import br.com.fiap.soat4.grupo48.telemed.consulta.application.exception.HorarioDisponivelNotFoundException;
import br.com.fiap.soat4.grupo48.telemed.consulta.application.port.in.IConsultaMedicaService;
import br.com.fiap.soat4.grupo48.telemed.consulta.application.port.out.IConsultaMedicaRepository;
import br.com.fiap.soat4.grupo48.telemed.consulta.application.port.out.IHorarioDisponivelRepository;
import br.com.fiap.soat4.grupo48.telemed.consulta.domain.model.ConsultaMedica;
import br.com.fiap.soat4.grupo48.telemed.consulta.domain.model.HorarioDisponivel;
import br.com.fiap.soat4.grupo48.telemed.consulta.domain.model.SituacaoConsultaMedica;

import java.time.LocalTime;
import java.util.*;

public class ConsultaMedicaService implements IConsultaMedicaService {

    private final IConsultaMedicaRepository consultaMedicaRepository;
    private final IHorarioDisponivelRepository horarioDisponivelRepository;
    private final IMedicoRepository medicoRepository;
    private final IPacienteRepository pacienteRepository;

    public ConsultaMedicaService(IConsultaMedicaRepository consultaMedicaRepository, IHorarioDisponivelRepository horarioDisponivelRepository, IMedicoRepository medicoRepository, IPacienteRepository pacienteRepository) {
        this.consultaMedicaRepository = consultaMedicaRepository;
        this.horarioDisponivelRepository = horarioDisponivelRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public ConsultaMedica criarConsultaMedica(UUID medicoId, UUID pacienteId, UUID horarioId) throws ConsultaMedicaIllegalArgumentException, HorarioDisponivelNotFoundException, MedicoNotFoundException, PacienteNotFoundException {

        if (Objects.isNull(medicoId)) {
            throw new ConsultaMedicaIllegalArgumentException("Médico é obrigatório.");
        }
        if (Objects.isNull(pacienteId)) {
            throw new ConsultaMedicaIllegalArgumentException("Paciente é obrigatório.");
        }
        if (Objects.isNull(horarioId)) {
            throw new ConsultaMedicaIllegalArgumentException("Horário é obrigatório.");
        }
        // verificar se o horário disponível existe
        HorarioDisponivel horarioDisponivel = horarioDisponivelRepository.findById(horarioId)
            .orElseThrow(() -> new HorarioDisponivelNotFoundException("Horário não encontrado."));

        // verificar se o horário disponível está disponível
        if (consultaMedicaRepository.existsByHorarioId(horarioDisponivel.getId())) {
            throw new ConsultaMedicaIllegalArgumentException("Horário indisponível.");
        }

        // não pode criar consulta médica para datas passadas
        if ((new Date()).before(horarioDisponivel.getData())) {
            throw new ConsultaMedicaIllegalArgumentException("Data não pode ser no passado.");
        }

        // não pode criar consulta médica para horas passadas
        if (horarioDisponivel.getHoraInicio().isBefore(LocalTime.now())) {
            throw new ConsultaMedicaIllegalArgumentException("Hora de início não pode ser no passado.");
        }

        Medico medico = medicoRepository.findById(medicoId)
            .orElseThrow(() -> new MedicoNotFoundException("Médico não encontrado."));

        Paciente paciente = pacienteRepository.findById(pacienteId)
            .orElseThrow(() -> new PacienteNotFoundException("Paciente não encontrado."));

        ConsultaMedica consultaMedica = new ConsultaMedica();
        consultaMedica.setMedico(medico);
        consultaMedica.setPaciente(paciente);
        consultaMedica.setHorario(horarioDisponivel);
        consultaMedica.setStatus(SituacaoConsultaMedica.AGENDADA);

        return consultaMedicaRepository.save(consultaMedica);
    }

    @Override
    public ConsultaMedica atualizarConsultaMedica(UUID id, UUID horarioId) throws ConsultaMedicaIllegalArgumentException, HorarioDisponivelNotFoundException {
        ConsultaMedica consultaMedicaAchada = consultaMedicaRepository.findById(id)
            .orElseThrow(() -> new ConsultaMedicaIllegalArgumentException("Consulta médica não encontrada."));
        HorarioDisponivel horarioDisponivel = horarioDisponivelRepository.findById(horarioId)
            .orElseThrow(() -> new HorarioDisponivelNotFoundException("Horário não encontrado."));

        consultaMedicaAchada.setHorario(horarioDisponivel);
        return consultaMedicaRepository.save(consultaMedicaAchada);

    }

    @Override
    public ConsultaMedica aceitarConsulta(UUID id) throws ConsultaMedicaIllegalArgumentException {
        ConsultaMedica consultaMedica = consultaMedicaRepository.findById(id)
            .orElseThrow(() -> new ConsultaMedicaIllegalArgumentException("Consulta médica não encontrada."));
        consultaMedica.setStatus(SituacaoConsultaMedica.ACEITA);
        return consultaMedicaRepository.save(consultaMedica);
    }

    @Override
    public ConsultaMedica recusarConsulta(UUID id) throws ConsultaMedicaIllegalArgumentException {
        ConsultaMedica consultaMedica = consultaMedicaRepository.findById(id)
            .orElseThrow(() -> new ConsultaMedicaIllegalArgumentException("Consulta médica não encontrada."));
        consultaMedica.setStatus(SituacaoConsultaMedica.RECUSADA);
        return consultaMedicaRepository.save(consultaMedica);
    }

    @Override
    public ConsultaMedica cancelarConsulta(UUID id) throws ConsultaMedicaIllegalArgumentException {
        ConsultaMedica consultaMedica = consultaMedicaRepository.findById(id)
            .orElseThrow(() -> new ConsultaMedicaIllegalArgumentException("Consulta médica não encontrada."));
        consultaMedica.setStatus(SituacaoConsultaMedica.CANCELADA);
        return consultaMedicaRepository.save(consultaMedica);
    }

    @Override
    public ConsultaMedica buscarPorId(UUID id) throws ConsultaMedicaNotFoundException {
        Optional<ConsultaMedica> consultaMedica = consultaMedicaRepository.findById(id);
        return consultaMedica.orElseThrow(() -> new ConsultaMedicaNotFoundException("Consulta médica não encontrada."));
    }

    @Override
    public List<ConsultaMedica> listarTodas() {
        return consultaMedicaRepository.findAll();
    }

    @Override
    public List<ConsultaMedica> listarPorPaciente(UUID pacienteId) {
        return consultaMedicaRepository.findByPaciente(pacienteId);
    }

    @Override
    public List<ConsultaMedica> listarPorMedico(UUID medicoId) {
        return consultaMedicaRepository.findByMedico(medicoId);
    }

    @Override
    public List<ConsultaMedica> listarPorData(Date data) {
        return consultaMedicaRepository.findByData(data);
    }

    @Override
    public List<ConsultaMedica> listarPorPeriodo(Date dataInicio, Date dataFim) {
        return consultaMedicaRepository.findByPeriodo(dataInicio, dataFim);
    }

    @Override
    public List<ConsultaMedica> listarPorMedicoStatusPeriodo(UUID medicoId, SituacaoConsultaMedica status, Date dataInicio, Date dataFim) {
        return consultaMedicaRepository.findByMedicoAndStatusAndPeriodo(medicoId, status, dataInicio, dataFim);
    }

}