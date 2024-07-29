package br.com.fiap.soat4.grupo48.telemed.consulta.infra.config;

import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.out.IMedicoRepository;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.out.IPacienteRepository;
import br.com.fiap.soat4.grupo48.telemed.consulta.application.port.in.IConsultaMedicaService;
import br.com.fiap.soat4.grupo48.telemed.consulta.application.port.in.IHorarioDisponivelService;
import br.com.fiap.soat4.grupo48.telemed.consulta.application.port.out.IConsultaMedicaRepository;
import br.com.fiap.soat4.grupo48.telemed.consulta.application.port.out.IHorarioDisponivelRepository;
import br.com.fiap.soat4.grupo48.telemed.consulta.application.port.out.IIntervalUtils;
import br.com.fiap.soat4.grupo48.telemed.consulta.application.service.ConsultaMedicaService;
import br.com.fiap.soat4.grupo48.telemed.consulta.application.service.HorarioDisponivelService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsultaBeanConfiguration {

    @Bean
    IHorarioDisponivelService horarioDisponivelService(IHorarioDisponivelRepository horarioDisponivelRepository, IMedicoRepository medicoRepository, IIntervalUtils intervalUtils) {
        return new HorarioDisponivelService(horarioDisponivelRepository, medicoRepository, intervalUtils);
    }

    @Bean
    IConsultaMedicaService consultaMedicaService(
        IConsultaMedicaRepository consultaMedicaRepository,
        IHorarioDisponivelRepository horarioDisponivelRepository,
        IMedicoRepository medicoRepository,
        IPacienteRepository pacienteRepository
    ) {
        return new ConsultaMedicaService(consultaMedicaRepository, horarioDisponivelRepository, medicoRepository, pacienteRepository);
    }
}
