package br.com.fiap.soat4.grupo48.telemed.cadastro.infra.config;

import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.in.IAdminService;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.in.IEspecialidadeService;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.in.IMedicoService;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.in.IPacienteService;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.out.IAdminRepository;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.out.IEspecialidadeRepository;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.out.IMedicoRepository;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.out.IPacienteRepository;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.service.AdminService;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.service.EspecialidadeService;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.service.MedicoService;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.service.PacienteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CadastroBeanConfiguration {

    @Bean
    IAdminService adminService(IAdminRepository adminRepository) {
        return new AdminService(adminRepository);
    }

    @Bean
    IEspecialidadeService especialidadeService(IEspecialidadeRepository especialidadeRepository) {
        return new EspecialidadeService(especialidadeRepository);
    }

    @Bean
    IMedicoService medicoService(IMedicoRepository medicoRepository, IEspecialidadeRepository especialidadeRepository) {
        return new MedicoService(medicoRepository, especialidadeRepository);
    }

    @Bean
    IPacienteService pacienteService(IPacienteRepository pacienteRepository) {
        return new PacienteService(pacienteRepository);
    }
}
