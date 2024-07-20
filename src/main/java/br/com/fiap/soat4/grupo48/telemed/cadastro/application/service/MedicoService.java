package br.com.fiap.soat4.grupo48.telemed.cadastro.application.service;

import br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception.MedicoNotFoundException;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.in.IMedicoService;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.out.IEspecialidadeRepository;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.out.IMedicoRepository;
import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Especialidade;
import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Medico;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class MedicoService implements IMedicoService {

    public static final String MEDICO_NAO_ENCONTRADO_COM_ID = "Médico não encontrado com ID: ";
    private final IMedicoRepository medicoRepository;
    private final IEspecialidadeRepository especialidadeRepository;

    public MedicoService(IMedicoRepository medicoRepository, IEspecialidadeRepository especialidadeRepository) {
        this.medicoRepository = medicoRepository;
        this.especialidadeRepository = especialidadeRepository;
    }

    @Override
    public Medico cadastrarMedico(String nome, String email, String crm) {
        Medico medico = new Medico();
        medico.setNome(nome);
        medico.setEmail(email);
        medico.setCrm(crm);
        return medicoRepository.save(medico);
    }

    @Override
    public Medico atualizarMedico(String id, String nome, String email, String crm) throws MedicoNotFoundException {
        UUID uuid = UUID.fromString(id);
        return medicoRepository.findById(uuid).map(medico -> {
            medico.setNome(nome);
            medico.setEmail(email);
            medico.setCrm(crm);
            return medicoRepository.save(medico);
        }).orElseThrow(() -> new MedicoNotFoundException(MEDICO_NAO_ENCONTRADO_COM_ID + id));
    }

    @Override
    public Medico excluirMedico(String id) throws MedicoNotFoundException {
        UUID uuid = UUID.fromString(id);
        Medico medico = medicoRepository.findById(uuid).orElseThrow(() -> new MedicoNotFoundException(MEDICO_NAO_ENCONTRADO_COM_ID + id));
        medicoRepository.deleteById(uuid);
        return medico;
    }

    @Override
    public Medico buscarMedico(String id) throws MedicoNotFoundException {
        UUID uuid = UUID.fromString(id);
        return medicoRepository.findById(uuid).orElseThrow(() -> new MedicoNotFoundException(MEDICO_NAO_ENCONTRADO_COM_ID + id));
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
    public List<Medico> buscarMedicosPorEspecialidade(String idEspecialidade) {
        UUID especialidadeId = UUID.fromString(idEspecialidade);
        return medicoRepository.findAll().stream()
            .filter(medico -> medico.getEspecialidades().stream()
                .anyMatch(especialidade -> especialidade.getId().equals(especialidadeId)))
            .collect(Collectors.toList());
    }

    @Override
    public Medico vincularEspecialidade(String idMedico, String idEspecialidade) throws MedicoNotFoundException {
        UUID uuidMedico = UUID.fromString(idMedico);
        Medico medico = medicoRepository.findById(uuidMedico).orElseThrow(() -> new MedicoNotFoundException(MEDICO_NAO_ENCONTRADO_COM_ID + idMedico));
        Especialidade especialidade = especialidadeRepository.findById(UUID.fromString(idEspecialidade)).orElseThrow(() -> new MedicoNotFoundException("Especialidade não encontrada com ID: " + idEspecialidade));
        medico.getEspecialidades().add(especialidade);
        return medicoRepository.save(medico);
    }

    @Override
    public Medico desvincularEspecialidade(String idMedico, String idEspecialidade) throws MedicoNotFoundException {
        UUID uuidMedico = UUID.fromString(idMedico);
        Medico medico = medicoRepository.findById(uuidMedico).orElseThrow(() -> new MedicoNotFoundException(MEDICO_NAO_ENCONTRADO_COM_ID + idMedico));
        Especialidade especialidadeToRemove = especialidadeRepository.findById(UUID.fromString(idEspecialidade))
            .orElseThrow(() -> new MedicoNotFoundException("Especialidade não encontrada com ID: " + idEspecialidade));
        medico.getEspecialidades().remove(especialidadeToRemove);
        return medicoRepository.save(medico);
    }
}