package br.com.fiap.soat4.grupo48.telemed.consulta.application.service;

import br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception.MedicoIllegalArgumentException;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception.MedicoNotFoundException;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.out.IMedicoRepository;
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
    public HorarioDisponivel criarHorarioDisponivel(HorarioDisponivel horarioDisponivel) throws MedicoIllegalArgumentException, MedicoNotFoundException, HorarioDisponivelIllegalArgumentException {

        if (Objects.isNull(horarioDisponivel.getMedicoId())) {
            throw new MedicoIllegalArgumentException("Médico é obrigatório.");
        }
        if (medicoRepository.findById(horarioDisponivel.getMedicoId()).isEmpty()) {
            throw new MedicoNotFoundException("Médico não encontrado.");
        }
        if (Objects.isNull(horarioDisponivel.getData())) {
            throw new HorarioDisponivelIllegalArgumentException("Data é obrigatória.");
        }
        if (Objects.isNull(horarioDisponivel.getHoraInicio())) {
            throw new HorarioDisponivelIllegalArgumentException("Hora de início é obrigatória.");
        }
        if (Objects.isNull(horarioDisponivel.getHoraFim())) {
            throw new HorarioDisponivelIllegalArgumentException("Hora de fim é obrigatória.");
        }
        if (horarioDisponivel.getHoraInicio().after(horarioDisponivel.getHoraFim())) {
            throw new HorarioDisponivelIllegalArgumentException("Hora de início deve ser menor que a hora de fim.");
        }
        // não pode criar horário disponível para datas passadas
        if (horarioDisponivel.getData().before(new Date())) {
            throw new HorarioDisponivelIllegalArgumentException("Data não pode ser no passado.");
        }
        // buscar horários disponíveis do médico na data
        List<HorarioDisponivel> horariosDisponiveis = horarioDisponivelRepository.findByMedicoIdAndData(horarioDisponivel.getMedicoId(), horarioDisponivel.getData());
        // verificar se o horário disponível a ser criado não conflita com horários já existentes
        for (HorarioDisponivel hd : horariosDisponiveis) {
            if (horarioDisponivel.getHoraInicio().before(hd.getHoraFim()) && horarioDisponivel.getHoraFim().after(hd.getHoraInicio())) {
                throw new HorarioDisponivelIllegalArgumentException("Horário disponível conflita com horário já existente.");
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
    public HorarioDisponivel atualizarHorarioDisponivel(UUID id, HorarioDisponivel horarioDisponivel) throws HorarioDisponivelIllegalArgumentException {
        buscarPorId(id); // Verifica se o horário disponível existe
        horarioDisponivel.setId(id); // Garante que o ID é mantido
        return horarioDisponivelRepository.save(horarioDisponivel);
    }

    @Override
    public void deletarPorId(UUID id) throws HorarioDisponivelIllegalArgumentException {
        buscarPorId(id); // Verifica se o horário disponível existe
        horarioDisponivelRepository.deleteById(id);
    }
}