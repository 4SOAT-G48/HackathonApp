package br.com.fiap.soat4.grupo48.telemed.cadastro.application.port.in;

import br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception.MedicoIllegalArgumentException;
import br.com.fiap.soat4.grupo48.telemed.cadastro.application.exception.MedicoNotFoundException;
import br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model.Medico;

import java.util.List;
import java.util.UUID;

/**
 * Interface IMedicoService define os serviços disponíveis para operações relacionadas a médicos.
 * Inclui funcionalidades para cadastrar, atualizar, excluir médicos, buscar médicos por diferentes critérios
 * e gerenciar a vinculação de especialidades a médicos.
 */
public interface IMedicoService {

    /**
     * Cadastra um novo médico com nome, email e CRM fornecidos.
     *
     * @param nome  O nome do médico a ser cadastrado.
     * @param email O email do médico a ser cadastrado.
     * @param crm   O CRM do médico a ser cadastrado.
     * @return O objeto Medico cadastrado.
     */
    Medico criarMedico(String nome, String email, String crm) throws MedicoIllegalArgumentException;

    /**
     * Atualiza um médico existente, identificado pelo ID, com novos valores de nome, email e CRM.
     *
     * @param id    O identificador único do médico a ser atualizado.
     * @param nome  O novo nome para o médico.
     * @param email O novo email para o médico.
     * @param crm   O novo CRM para o médico.
     * @return O objeto Medico atualizado.
     */
    Medico atualizarMedico(UUID id, String nome, String email, String crm) throws MedicoNotFoundException, MedicoIllegalArgumentException;

    /**
     * Exclui um médico do sistema, identificado pelo ID fornecido.
     *
     * @param id O identificador único do médico a ser excluído.
     */
    void deletarMedico(UUID id) throws MedicoNotFoundException, MedicoIllegalArgumentException;

    /**
     * Busca um médico pelo seu identificador único.
     *
     * @param id O identificador único do médico a ser buscado.
     * @return O objeto Medico encontrado, ou null caso não seja encontrado.
     */
    Medico buscarMedico(UUID id) throws MedicoNotFoundException;

    /**
     * Busca um médico pelo seu email.
     *
     * @param email O email do médico a ser buscado.
     * @return O objeto Medico encontrado, ou null caso não seja encontrado.
     */
    Medico buscarMedicoPorEmail(String email) throws MedicoNotFoundException;

    /**
     * Busca um médico pelo seu CRM.
     *
     * @param crm O CRM do médico a ser buscado.
     * @return O objeto Medico encontrado, ou null caso não seja encontrado.
     */
    Medico buscarMedicoPorCrm(String crm) throws MedicoNotFoundException;

    /**
     * Busca médicos que possuem uma determinada especialidade, identificada pelo ID da especialidade.
     *
     * @param idEspecialidade O identificador único da especialidade.
     * @return Uma lista de objetos Medico que possuem a especialidade especificada.
     */
    List<Medico> buscarMedicosPorEspecialidade(UUID idEspecialidade);

    /**
     * Vincula uma especialidade, identificada pelo seu ID, a um médico, também identificado pelo seu ID.
     *
     * @param idMedico        O identificador único do médico.
     * @param idEspecialidade O identificador único da especialidade a ser vinculada.
     * @return O objeto Medico com a especialidade vinculada.
     */
    Medico vincularEspecialidade(UUID idMedico, UUID idEspecialidade) throws MedicoNotFoundException;

    /**
     * Desvincula uma especialidade, identificada pelo seu ID, de um médico, também identificado pelo seu ID.
     *
     * @param idMedico        O identificador único do médico.
     * @param idEspecialidade O identificador único da especialidade a ser desvinculada.
     * @return O objeto Medico com a especialidade desvinculada.
     */
    Medico desvincularEspecialidade(UUID idMedico, UUID idEspecialidade) throws MedicoNotFoundException;
}