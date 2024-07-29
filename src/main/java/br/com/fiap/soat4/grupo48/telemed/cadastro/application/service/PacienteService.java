package br.com.fiap.soat4.grupo48.telemed.cadastro.application.service;

import br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception.PacienteIllegalArgumentException;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception.PacienteNotFoundException;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.in.IPacienteService;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.out.IPacienteRepository;
import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Paciente;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class PacienteService implements IPacienteService {

    public static final String PACIENTE_NAO_ENCONTRADO_COM_ID = "Paciente não encontrado com ID: ";
    private final IPacienteRepository pacienteRepository;

    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente cadastrarPaciente(String nome, String email, String cpf) throws PacienteIllegalArgumentException {
        if (Objects.isNull(nome) || Objects.isNull(email) || Objects.isNull(cpf)) {
            throw new PacienteIllegalArgumentException("Nome, email e CPF são obrigatórios");
        }
        Paciente paciente = new Paciente();
        paciente.setNome(nome);
        paciente.setEmail(email);
        paciente.setCpf(cpf);
        return pacienteRepository.save(paciente);
    }

    @Override
    public Paciente atualizarPaciente(UUID id, String nome, String email, String cpf) throws PacienteNotFoundException, PacienteIllegalArgumentException {
        if (Objects.isNull(id) || Objects.isNull(nome) || Objects.isNull(email) || Objects.isNull(cpf)) {
            throw new PacienteIllegalArgumentException("ID, nome, email e CPF são obrigatórios");
        }
        return pacienteRepository.findById(id).map(paciente -> {
            paciente.setNome(nome);
            paciente.setEmail(email);
            paciente.setCpf(cpf);
            return pacienteRepository.save(paciente);
        }).orElseThrow(() -> new PacienteNotFoundException(PACIENTE_NAO_ENCONTRADO_COM_ID + id));
    }

    @Override
    public void excluirPaciente(UUID id) throws PacienteNotFoundException, PacienteIllegalArgumentException {
        if (Objects.isNull(id)) {
            throw new PacienteIllegalArgumentException("ID é obrigatório");
        }
        pacienteRepository.findById(id).orElseThrow(() -> new PacienteNotFoundException(PACIENTE_NAO_ENCONTRADO_COM_ID + id));
        pacienteRepository.deleteById(id);
    }

    @Override
    public Paciente buscarPaciente(UUID id) throws PacienteNotFoundException {
        return pacienteRepository.findById(id).orElseThrow(() -> new PacienteNotFoundException(PACIENTE_NAO_ENCONTRADO_COM_ID + id));
    }

    @Override
    public List<Paciente> buscarTodosPacientes() {
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente buscarPacientePorEmail(String email) throws PacienteNotFoundException {
        return pacienteRepository.findByEmail(email).orElseThrow(() -> new PacienteNotFoundException("Paciente não encontrado com email: " + email));
    }

    @Override
    public Paciente buscarPacientePorCpf(String cpf) throws PacienteNotFoundException {
        return pacienteRepository.findByCpf(cpf).orElseThrow(() -> new PacienteNotFoundException("Paciente não encontrado com CPF: " + cpf));
    }
}