package br.com.fiap.soat4.grupo48.telemed.consulta.infra.adapter.db;

import br.com.fiap.soat4.grupo48.telemed.consulta.domain.model.SituacaoConsultaMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Interface que define os métodos de consulta de consultas médicas usando Spring Data JPA.
 */
@Repository
public interface ConsultaMedicaSpringRepository extends JpaRepository<ConsultaMedicaEntity, UUID> {

    List<ConsultaMedicaEntity> findByPacienteId(UUID pacienteId);

    List<ConsultaMedicaEntity> findByMedicoId(UUID medicoId);

    List<ConsultaMedicaEntity> findByData(Date data);

    boolean existsByHorarioId(UUID horarioId);

    @Query("SELECT c FROM ConsultaMedicaEntity c, HorarioDisponivelEntity h " +
        "WHERE c.horarioId = h.id AND (h.dataInicio BETWEEN :dataInicio AND :dataFim OR h.dataFim BETWEEN :dataInicio AND :dataFim)")
    List<ConsultaMedicaEntity> findByPeriodo(@Param("dataInicio") Date dataInicio, @Param("dataFim") Date dataFim);


    @Query("SELECT c FROM ConsultaMedicaEntity c, HorarioDisponivelEntity h " +
        "WHERE c.medicoId = :medicoId AND c.status = :status AND (h.dataInicio BETWEEN :dataInicio AND :dataFim OR h.dataFim BETWEEN :dataInicio AND :dataFim)")
    List<ConsultaMedicaEntity> findByMedicoIdAndStatusAndPeriodo(@Param("medicoId") UUID medicoId, @Param("status") SituacaoConsultaMedica status, @Param("dataInicio") Date dataInicio, @Param("dataFim") Date dataFim);
}