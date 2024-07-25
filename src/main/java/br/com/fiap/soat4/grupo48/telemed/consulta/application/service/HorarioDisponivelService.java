package br.com.fiap.soat4.grupo48.telemed.consulta.application.service;

import br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception.MedicoIllegalArgumentException;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception.MedicoNotFoundException;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.out.IMedicoRepository;
import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Medico;
import br.com.fiap.soat4.grupo48.telemed.consulta.application.exception.HorarioDisponivelIllegalArgumentException;
import br.com.fiap.soat4.grupo48.telemed.consulta.application.port.in.IHorarioDisponivelService;
import br.com.fiap.soat4.grupo48.telemed.consulta.application.port.out.IHorarioDisponivelRepository;
import br.com.fiap.soat4.grupo48.telemed.consulta.application.port.out.IIntervalUtils;
import br.com.fiap.soat4.grupo48.telemed.consulta.domain.model.HorarioDisponivel;

import java.time.LocalTime;
import java.util.*;

public class HorarioDisponivelService implements IHorarioDisponivelService {

    private final IHorarioDisponivelRepository horarioDisponivelRepository;
    private final IMedicoRepository medicoRepository;
    private final IIntervalUtils intervalUtils;

    public HorarioDisponivelService(IHorarioDisponivelRepository horarioDisponivelRepository, IMedicoRepository medicoRepository, IIntervalUtils intervalUtils) {
        this.horarioDisponivelRepository = horarioDisponivelRepository;
        this.medicoRepository = medicoRepository;
        this.intervalUtils = intervalUtils;
    }

    @Override
    public HorarioDisponivel criarHorarioDisponivel(UUID medicoId, Date data, LocalTime horaInicio, LocalTime horaFim) throws MedicoIllegalArgumentException, MedicoNotFoundException, HorarioDisponivelIllegalArgumentException {

        if (Objects.isNull(medicoId)) {
            throw new MedicoIllegalArgumentException("Médico é obrigatório.");
        }
        Optional<Medico> medico = medicoRepository.findById(medicoId);
        if (medico.isEmpty()) {
            throw new MedicoNotFoundException("Médico não encontrado.");
        }
        if (Objects.isNull(data)) {
            throw new HorarioDisponivelIllegalArgumentException("Data é obrigatória.");
        }
        if (Objects.isNull(horaInicio)) {
            throw new HorarioDisponivelIllegalArgumentException("Hora de início é obrigatória.");
        }
        if (Objects.isNull(horaFim)) {
            throw new HorarioDisponivelIllegalArgumentException("Hora de fim é obrigatória.");
        }
        if (horaInicio.isAfter(horaFim)) {
            throw new HorarioDisponivelIllegalArgumentException("Hora de início deve ser menor que a hora de fim.");
        }
        // não pode criar horário disponível para datas passadas
        if (data.before(new Date())) {
            throw new HorarioDisponivelIllegalArgumentException("Data não pode ser no passado.");
        }

        HorarioDisponivel horarioDisponivel = new HorarioDisponivel();
        horarioDisponivel.setMedicoId(medicoId);
        horarioDisponivel.setData(data);
        horarioDisponivel.setHoraInicio(horaInicio);
        horarioDisponivel.setHoraFim(horaFim);

        // buscar horários disponíveis do médico na data
        List<HorarioDisponivel> horariosDisponiveis = horarioDisponivelRepository.findByMedicoIdAndData(medicoId, data);
        // verificar se o horário disponível a ser criado não conflita com horários já existentes
        for (HorarioDisponivel hd : horariosDisponiveis) {
            if (intervalUtils.intervalsOverlap(horarioDisponivel.getHoraInicio(), horarioDisponivel.getHoraFim(), hd.getHoraInicio(), hd.getHoraFim())) {
                throw new HorarioDisponivelIllegalArgumentException("Horário conflita com horário disponível já existente.");
            }
        }

        return horarioDisponivelRepository.save(horarioDisponivel);
    }

    @Override
    public HorarioDisponivel buscarPorId(UUID id) throws HorarioDisponivelIllegalArgumentException {
        Optional<HorarioDisponivel> horarioDisponivel = horarioDisponivelRepository.findById(id);
        return horarioDisponivel.orElseThrow(() -> new HorarioDisponivelIllegalArgumentException("Horário disponível não encontrado."));
    }

    @Override
    public List<HorarioDisponivel> listarTodos() {
        return horarioDisponivelRepository.findAll();
    }

    @Override
    public List<HorarioDisponivel> buscarPorMedicoEData(UUID medicoId, Date data) {
        return horarioDisponivelRepository.findByMedicoIdAndData(medicoId, data);
    }

    @Override
    public HorarioDisponivel atualizarHorarioDisponivel(UUID id, Date data, LocalTime horaInicio, LocalTime horaFim) throws HorarioDisponivelIllegalArgumentException {

        HorarioDisponivel horarioDisponivel = horarioDisponivelRepository.findById(id)
            .orElseThrow(() -> new HorarioDisponivelIllegalArgumentException("Horário disponível não encontrado."));

        if (Objects.isNull(data)) {
            throw new HorarioDisponivelIllegalArgumentException("Data é obrigatória.");
        }
        if (Objects.isNull(horaInicio)) {
            throw new HorarioDisponivelIllegalArgumentException("Hora de início é obrigatória.");
        }
        if (Objects.isNull(horaFim)) {
            throw new HorarioDisponivelIllegalArgumentException("Hora de fim é obrigatória.");
        }
        if (horaInicio.isAfter(horaFim)) {
            throw new HorarioDisponivelIllegalArgumentException("Hora de início deve ser menor que a hora de fim.");
        }
        // não pode criar horário disponível para datas passadas
        if (data.before(new Date())) {
            throw new HorarioDisponivelIllegalArgumentException("Data não pode ser no passado.");
        }
        // buscar horários disponíveis do médico na data
        List<HorarioDisponivel> horariosDisponiveis =
            horarioDisponivelRepository.findByMedicoIdAndDataAndIdNot(horarioDisponivel.getMedicoId(), data, id);
        // verificar se o horário disponível a ser criado não conflita com horários já existentes
        for (HorarioDisponivel hd : horariosDisponiveis) {
            if (intervalUtils.intervalsOverlap(horarioDisponivel.getHoraInicio(), horarioDisponivel.getHoraFim(), hd.getHoraInicio(), hd.getHoraFim())) {
                throw new HorarioDisponivelIllegalArgumentException("Horário disponível conflita com horário já existente.");
            }
        }

        horarioDisponivel.setData(data);
        horarioDisponivel.setHoraInicio(horaInicio);
        horarioDisponivel.setHoraFim(horaFim);
        return horarioDisponivelRepository.save(horarioDisponivel);
    }

    @Override
    public void deletarPorId(UUID id) throws HorarioDisponivelIllegalArgumentException {
        buscarPorId(id); // Verifica se o horário disponível existe
        horarioDisponivelRepository.deleteById(id);
    }

    @Override
    public List<HorarioDisponivel> listarPorMedico(UUID id) {
        //Busca todos os horários disponíveis para o médico a partir de hoje
        return horarioDisponivelRepository.findByMedicoIdAndDataGreaterThanEqual(id, new Date());
    }
}