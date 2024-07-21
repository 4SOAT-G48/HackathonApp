package br.com.fiap.soat4.grupo48.telemed.cadastro.application.service;

import br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception.EspecialidadeNotFoundException;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.in.IEspecialidadeService;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.out.IEspecialidadeRepository;
import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Especialidade;

import java.util.List;
import java.util.UUID;

public class EspecialidadeService implements IEspecialidadeService {

    public static final String ESPECIALIDADE_NAO_ENCONTRADA_COM_ID = "Especialidade não encontrada com ID: ";
    private final IEspecialidadeRepository especialidadeRepository;

    public EspecialidadeService(IEspecialidadeRepository especialidadeRepository) {
        this.especialidadeRepository = especialidadeRepository;
    }

    @Override
    public Especialidade criarEspecialidade(Long codigo, String descricao) {
        Especialidade especialidade = new Especialidade();
        especialidade.setId(UUID.randomUUID());
        especialidade.setCodigo(codigo);
        especialidade.setDescricao(descricao);
        return especialidadeRepository.save(especialidade);
    }

    @Override
    public Especialidade atualizarEspecialidade(UUID id, Long codigo, String descricao) throws EspecialidadeNotFoundException {

        return especialidadeRepository.findById(id).map(especialidade -> {
            especialidade.setCodigo(codigo);
            especialidade.setDescricao(descricao);
            return especialidadeRepository.save(especialidade);
        }).orElseThrow(() -> new EspecialidadeNotFoundException(ESPECIALIDADE_NAO_ENCONTRADA_COM_ID + id));
    }

    @Override
    public Especialidade deletarEspecialidade(UUID id) throws EspecialidadeNotFoundException {
        Especialidade especialidade = especialidadeRepository.findById(id).orElseThrow(() -> new EspecialidadeNotFoundException(ESPECIALIDADE_NAO_ENCONTRADA_COM_ID + id));
        especialidadeRepository.deleteById(id);
        return especialidade;
    }

    @Override
    public List<Especialidade> buscarTodasEspecialidades() {
        return especialidadeRepository.findAll();
    }

    @Override
    public Especialidade buscarEspecialidade(UUID id) throws EspecialidadeNotFoundException {
        return especialidadeRepository.findById(id).orElseThrow(() -> new EspecialidadeNotFoundException(ESPECIALIDADE_NAO_ENCONTRADA_COM_ID + id));
    }
}