package br.com.fiap.soat4.grupo48.telemed.cadastro.application.service;

import br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception.EspecialidadeNotFoundException;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.in.IEspecialidadeService;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.out.IEspecialidadeRepository;
import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Especialidade;

import java.util.UUID;

public class EspecialidadeService implements IEspecialidadeService {

    private final IEspecialidadeRepository especialidadeRepository;

    public EspecialidadeService(IEspecialidadeRepository especialidadeRepository) {
        this.especialidadeRepository = especialidadeRepository;
    }

    @Override
    public Especialidade cadastrarEspecialidade(Long codigo, String descricao) {
        Especialidade especialidade = new Especialidade();
        especialidade.setId(UUID.randomUUID());
        especialidade.setCodigo(codigo);
        especialidade.setDescricao(descricao);
        return especialidadeRepository.save(especialidade);
    }

    @Override
    public Especialidade atualizarEspecialidade(String id, Long codigo, String descricao) throws EspecialidadeNotFoundException {
        UUID uuid = UUID.fromString(id);
        return especialidadeRepository.findById(uuid).map(especialidade -> {
            especialidade.setCodigo(codigo);
            especialidade.setDescricao(descricao);
            return especialidadeRepository.save(especialidade);
        }).orElseThrow(() -> new EspecialidadeNotFoundException("Especialidade não encontrada com ID: " + id));
    }

    @Override
    public Especialidade excluirEspecialidade(String id) throws EspecialidadeNotFoundException {
        UUID uuid = UUID.fromString(id);
        Especialidade especialidade = especialidadeRepository.findById(uuid).orElseThrow(() -> new EspecialidadeNotFoundException("Especialidade não encontrada com ID: " + id));
        especialidadeRepository.deleteById(uuid);
        return especialidade;
    }
}