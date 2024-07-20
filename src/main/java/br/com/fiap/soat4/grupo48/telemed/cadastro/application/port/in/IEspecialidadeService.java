package br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.in;

import br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception.EspecialidadeNotFoundException;
import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Especialidade;

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
    Especialidade criarEspecialidade(Long codigo, String descricao);

    /**
     * Atualiza uma especialidade existente, identificada pelo ID, com novos valores de código e descrição.
     *
     * @param id        O identificador único da especialidade a ser atualizada.
     * @param codigo    O novo código para a especialidade.
     * @param descricao A nova descrição para a especialidade.
     * @return O objeto Especialidade atualizado.
     */
    Especialidade atualizarEspecialidade(String id, Long codigo, String descricao) throws EspecialidadeNotFoundException;

    /**
     * Exclui uma especialidade do sistema, identificada pelo ID fornecido.
     *
     * @param id O identificador único da especialidade a ser excluída.
     * @return O objeto Especialidade excluído.
     */
    Especialidade deletarEspecialidade(String id) throws EspecialidadeNotFoundException;
}