package br.com.fiap.soat4.grupo48.telemed.cadastro.application.service;

import br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception.MedicoIllegalArgumentException;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception.MedicoNotFoundException;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.in.IMedicoService;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.out.IEspecialidadeRepository;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.out.IMedicoRepository;
import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Especialidade;
import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Medico;

import java.util.List;
import java.util.UUID;

public class MedicoService implements IMedicoService {

    public static final String MEDICO_NAO_ENCONTRADO_COM_ID = "Médico não encontrado com ID: ";
    private final IMedicoRepository medicoRepository;
    private final IEspecialidadeRepository especialidadeRepository;

    public MedicoService(IMedicoRepository medicoRepository, IEspecialidadeRepository especialidadeRepository) {
        this.medicoRepository = medicoRepository;
        this.especialidadeRepository = especialidadeRepository;
    }

    @Override
    public Medico criarMedico(String nome, String email, String crm) throws MedicoIllegalArgumentException {
        if (nome == null || email == null || crm == null) {
            throw new MedicoIllegalArgumentException("Nome, email e CRM são obrigatórios");
        }
        Medico medico = new Medico();
        medico.setNome(nome);
        medico.setEmail(email);
        medico.setCrm(crm);
        return medicoRepository.save(medico);
    }

    @Override
    public Medico atualizarMedico(UUID id, String nome, String email, String crm) throws MedicoNotFoundException, MedicoIllegalArgumentException {
        if (id == null || nome == null || email == null || crm == null) {
            throw new MedicoIllegalArgumentException("ID, nome, email e CRM são obrigatórios");
        }
        return medicoRepository.findById(id).map(medico -> {
            medico.setNome(nome);
            medico.setEmail(email);
            medico.setCrm(crm);
            return medicoRepository.save(medico);
        }).orElseThrow(() -> new MedicoNotFoundException(MEDICO_NAO_ENCONTRADO_COM_ID + id));
    }

    @Override
    public void deletarMedico(UUID id) throws MedicoNotFoundException, MedicoIllegalArgumentException {
        if (id == null) {
            throw new MedicoIllegalArgumentException("ID é obrigatório");
        }
        Medico medico = medicoRepository.findById(id).orElseThrow(() -> new MedicoNotFoundException(MEDICO_NAO_ENCONTRADO_COM_ID + id));
        medicoRepository.deleteById(id);
    }

    @Override
    public Medico buscarMedico(UUID id) throws MedicoNotFoundException {
        return medicoRepository.findById(id).orElseThrow(() -> new MedicoNotFoundException(MEDICO_NAO_ENCONTRADO_COM_ID + id));
    }

    @Override
    public Medico buscarMedicoPorEmail(String email) throws MedicoNotFoundException {
        return medicoRepository.findByEmail(email).orElseThrow(() -> new MedicoNotFoundException("Médico não encontrado com email: " + email));
    }

    @Override
    public Medico buscarMedicoPorCrm(String crm) throws MedicoNotFoundException {
        return medicoRepository.findByCrm(crm).orElseThrow(() -> new MedicoNotFoundException("Médico não encontrado com CRM: " + crm));
    }

    @Override
    public List<Medico> buscarMedicosPorEspecialidade(UUID idEspecialidade) {
        return medicoRepository.findAll().stream()
            .filter(medico -> medico.getEspecialidades().stream()
                .anyMatch(especialidade -> especialidade.getId().equals(idEspecialidade)))
            .toList();
    }

    @Override
    public Medico vincularEspecialidade(UUID idMedico, UUID idEspecialidade) throws MedicoNotFoundException {
        Medico medico = medicoRepository.findById(idMedico).orElseThrow(() -> new MedicoNotFoundException(MEDICO_NAO_ENCONTRADO_COM_ID + idMedico));
        Especialidade especialidade = especialidadeRepository.findById(idEspecialidade).orElseThrow(() -> new MedicoNotFoundException("Especialidade não encontrada com ID: " + idEspecialidade));
        medico.getEspecialidades().add(especialidade);
        return medicoRepository.save(medico);
    }

    @Override
    public Medico desvincularEspecialidade(UUID idMedico, UUID idEspecialidade) throws MedicoNotFoundException {
        Medico medico = medicoRepository.findById(idMedico).orElseThrow(() -> new MedicoNotFoundException(MEDICO_NAO_ENCONTRADO_COM_ID + idMedico));
        Especialidade especialidadeToRemove = especialidadeRepository.findById(idEspecialidade)
            .orElseThrow(() -> new MedicoNotFoundException("Especialidade não encontrada com ID: " + idEspecialidade));
        medico.getEspecialidades().remove(especialidadeToRemove);
        return medicoRepository.save(medico);
    }
}