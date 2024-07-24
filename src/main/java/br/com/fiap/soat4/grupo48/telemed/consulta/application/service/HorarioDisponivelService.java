package br.com.fiap.soat4.grupo48.telemed.consulta.application.service;

import br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception.MedicoIllegalArgumentException;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception.MedicoNotFoundException;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.out.IMedicoRepository;
import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Medico;
import br.com.fiap.soat4.grupo48.telemed.consulta.application.exception.HorarioDisponivelIllegalArgumentException;
import br.com.fiap.soat4.grupo48.telemed.consulta.application.port.in.IHorarioDisponivelService;
import br.com.fiap.soat4.grupo48.telemed.consulta.application.port.out.IHorarioDisponivelRepository;
import br.com.fiap.soat4.grupo48.telemed.consulta.domain.model.HorarioDisponivel;

import java.util.*;

public class HorarioDisponivelService implements IHorarioDisponivelService {

    private final IHorarioDisponivelRepository horarioDisponivelRepository;
    private final IMedicoRepository medicoRepository;

    public HorarioDisponivelService(IHorarioDisponivelRepository horarioDisponivelRepository, IMedicoRepository medicoRepository) {
        this.horarioDisponivelRepository = horarioDisponivelRepository;
        this.medicoRepository = medicoRepository;
    }

    @Override
    public HorarioDisponivel criarHorarioDisponivel(UUID medicoId, Date data, Date horaInicio, Date horaFim) throws MedicoIllegalArgumentException, MedicoNotFoundException, HorarioDisponivelIllegalArgumentException {

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
        if (horaInicio.after(horaFim)) {
            throw new HorarioDisponivelIllegalArgumentException("Hora de início deve ser menor que a hora de fim.");
        }
        // não pode criar horário disponível para datas passadas
        if (data.before(new Date())) {
            throw new HorarioDisponivelIllegalArgumentException("Data não pode ser no passado.");
        }
        // buscar horários disponíveis do médico na data
        List<HorarioDisponivel> horariosDisponiveis = horarioDisponivelRepository.findByMedicoIdAndData(medicoId, data);
        // verificar se o horário disponível a ser criado não conflita com horários já existentes
        for (HorarioDisponivel hd : horariosDisponiveis) {
            if ((horaInicio.before(hd.getHoraFim()) && horaInicio.after(hd.getHoraInicio()))
                || (horaFim.before(hd.getHoraFim()) && horaFim.after(hd.getHoraInicio()))) {
                throw new HorarioDisponivelIllegalArgumentException("Horário disponível conflita com horário já existente.");
            }
        }
        HorarioDisponivel horarioDisponivel = new HorarioDisponivel();
        horarioDisponivel.setMedicoId(medicoId);
        horarioDisponivel.setData(data);
        horarioDisponivel.setHoraInicio(horaInicio);
        horarioDisponivel.setHoraFim(horaFim);
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
    public HorarioDisponivel atualizarHorarioDisponivel(UUID id, Date data, Date horaInicio, Date horaFim) throws HorarioDisponivelIllegalArgumentException {

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
        if (horaInicio.after(horaFim)) {
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
            if (horaInicio.before(hd.getHoraFim()) && horaFim.after(hd.getHoraInicio())) {
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