package br.com.fiap.soat4.grupo48.telemed.consulta.infra.adapter.db;

import jakarta.persistence.TemporalType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Interface que define os métodos de consulta de consultas médicas usando Spring Data JPA.
 */
public interface ConsultaMedicaSpringRepository extends JpaRepository<ConsultaMedicaEntity, UUID> {

    List<ConsultaMedicaEntity> findByPacienteId(UUID pacienteId);

    List<ConsultaMedicaEntity> findByMedicoId(UUID medicoId);

    @Query("SELECT c FROM ConsultaMedicaEntity c JOIN c.horario h " +
        "WHERE h.data = :data")
    List<ConsultaMedicaEntity> findByData(@Param("data") Date data);

    boolean existsByHorarioId(UUID horarioId);

    @Query("SELECT c FROM ConsultaMedicaEntity c JOIN c.horario h " +
        "WHERE h.horaInicio BETWEEN :horaInicio AND :horaFim OR h.horaFim BETWEEN :horaInicio AND :horaFim")
    List<ConsultaMedicaEntity> findByPeriodo(
        @Param("horaInicio") @Temporal(TemporalType.TIMESTAMP) Date horaInicio,
        @Param("horaFim") @Temporal(TemporalType.TIMESTAMP) Date horaFim
    );


    @Query("SELECT c FROM ConsultaMedicaEntity c JOIN c.horario h " +
        "WHERE c.medico.id = :medicoId AND c.status = :status AND (h.horaInicio BETWEEN :horaInicio AND :horaFim OR h.horaFim BETWEEN :horaInicio AND :horaFim)")
    List<ConsultaMedicaEntity> findByMedicoIdAndStatusAndPeriodo(
        @Param("medicoId") UUID medicoId,
        @Param("status") String status,
        @Param("horaInicio") @Temporal(TemporalType.TIMESTAMP) Date horaInicio,
        @Param("horaFim") @Temporal(TemporalType.TIMESTAMP) Date horaFim
    );
}