package br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.in;

import br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception.EspecialidadeIllegalArgumentException;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception.EspecialidadeNotFoundException;
import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Especialidade;

import java.util.List;
import java.util.UUID;

/**
 * Interface IEspecialidadeService define os serviços disponíveis para operações relacionadas a especialidades médicas.
 * Inclui funcionalidades para cadastrar, atualizar e excluir especialidades.
 */
public interface IEspecialidadeService {

    /**
     * Cadastra uma nova especialidade com o código e descrição fornecidos.
     *
     * @param codigo    O código único da especialidade a ser cadastrada.
     * @param descricao A descrição da especialidade a ser cadastrada.
     * @return O objeto Especialidade cadastrado.
     */
    Especialidade criarEspecialidade(String codigo, String descricao) throws EspecialidadeIllegalArgumentException;

    /**
     * Atualiza uma especialidade existente, identificada pelo ID, com novos valores de código e descrição.
     *
     * @param id        O identificador único da especialidade a ser atualizada.
     * @param codigo    O novo código para a especialidade.
     * @param descricao A nova descrição para a especialidade.
     * @return O objeto Especialidade atualizado.
     */
    Especialidade atualizarEspecialidade(UUID id, String codigo, String descricao) throws EspecialidadeNotFoundException, EspecialidadeIllegalArgumentException;

    /**
     * Exclui uma especialidade do sistema, identificada pelo ID fornecido.
     *
     * @param id O identificador único da especialidade a ser excluída.
     */
    void deletarEspecialidade(UUID id) throws EspecialidadeNotFoundException, EspecialidadeIllegalArgumentException;

    /**
     * Busca todas as especialidades cadastradas no sistema.
     *
     * @return Uma lista de {@link Especialidade}, que pode estar vazia caso não existam especialidades cadastradas.
     */
    List<Especialidade> buscarTodasEspecialidades();

    /**
     * Busca uma especialidade específica pelo seu identificador único.
     *
     * @param id O identificador único da especialidade a ser buscada.
     * @return Um objeto {@link Especialidade} correspondente ao identificador fornecido. Pode retornar {@code null} caso a especialidade não seja encontrada.
     */
    Especialidade buscarEspecialidade(UUID id) throws EspecialidadeNotFoundException;
}