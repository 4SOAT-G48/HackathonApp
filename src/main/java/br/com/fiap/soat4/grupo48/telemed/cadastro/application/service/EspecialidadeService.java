package br.com.fiap.soat4.grupo48.telemed.cadastro.application.service;

import br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception.EspecialidadeIllegalArgumentException;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception.EspecialidadeNotFoundException;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.in.IEspecialidadeService;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.out.IEspecialidadeRepository;
import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Especialidade;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class EspecialidadeService implements IEspecialidadeService {

    public static final String ESPECIALIDADE_NAO_ENCONTRADA_COM_ID = "Especialidade não encontrada com ID: ";
    private final IEspecialidadeRepository especialidadeRepository;

    public EspecialidadeService(IEspecialidadeRepository especialidadeRepository) {
        this.especialidadeRepository = especialidadeRepository;
    }

    @Override
    public Especialidade criarEspecialidade(String codigo, String descricao) throws EspecialidadeIllegalArgumentException {
        if (Objects.isNull(codigo) || Objects.isNull(descricao)) {
            throw new EspecialidadeIllegalArgumentException("Código e descrição são obrigatórios");
        }
        Especialidade especialidade = new Especialidade();
        especialidade.setId(UUID.randomUUID());
        especialidade.setCodigo(codigo);
        especialidade.setDescricao(descricao);
        return especialidadeRepository.save(especialidade);
    }

    @Override
    public Especialidade atualizarEspecialidade(UUID id, String codigo, String descricao) throws EspecialidadeNotFoundException, EspecialidadeIllegalArgumentException {
        if (Objects.isNull(id) || Objects.isNull(codigo) || Objects.isNull(descricao)) {
            throw new EspecialidadeIllegalArgumentException("ID, código e descrição são obrigatórios");
        }
        return especialidadeRepository.findById(id).map(especialidade -> {
            especialidade.setCodigo(codigo);
            especialidade.setDescricao(descricao);
            return especialidadeRepository.save(especialidade);
        }).orElseThrow(() -> new EspecialidadeNotFoundException(ESPECIALIDADE_NAO_ENCONTRADA_COM_ID + id));
    }

    @Override
    public void deletarEspecialidade(UUID id) throws EspecialidadeNotFoundException, EspecialidadeIllegalArgumentException {
        if (Objects.isNull(id)) {
            throw new EspecialidadeIllegalArgumentException("ID é obrigatório");
        }
        Especialidade especialidade = especialidadeRepository.findById(id).orElseThrow(() -> new EspecialidadeNotFoundException(ESPECIALIDADE_NAO_ENCONTRADA_COM_ID + id));
        especialidadeRepository.deleteById(id);
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