package br.com.fiap.soat4.grupo48.telemed.consulta.infra.adapter.db;

import br.com.fiap.soat4.grupo48.telemed.cadastro.infra.adapter.db.MedicoEntity;
import br.com.fiap.soat4.grupo48.telemed.cadastro.infra.adapter.db.PacienteEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "CONSULTA_MEDICA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaMedicaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(
        optional = false
    )
    @JoinColumn(name = "medico_id", nullable = false, updatable = false)
    private MedicoEntity medico;

    @ManyToOne(
        optional = false
    )
    @JoinColumn(name = "paciente_id", nullable = false, updatable = false)
    private PacienteEntity paciente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "horario_id", nullable = false, updatable = false)
    private HorarioDisponivelEntity horario;

    private String status;

    @Column(name = "justificativa_cancelamento")
    private String justificativaCancelamento;

    @Column(
        name = "data_criacao",
        nullable = false,
        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;

    @Column(
        name = "data_atualizacao",
        nullable = false,
        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

    /**
     * Método chamado antes da persistência da entidade para inserir as datas de criação e atualização.
     * Se a data de criação for nula, ambas as datas são definidas para o momento atual.
     */
    @PrePersist
    public void insereDatas() {
        if (Objects.isNull(this.dataCriacao)) {
            this.dataCriacao = new Timestamp(Calendar.getInstance().getTimeInMillis());
            this.dataAtualizacao = new Timestamp(Calendar.getInstance().getTimeInMillis());
        }
    }

    /**
     * Método chamado antes da atualização da entidade para atualizar a data de atualização.
     * A data de atualização é definida para o momento atual.
     */
    @PreUpdate
    public void atualizaDataAtualizacao() {
        this.dataAtualizacao = new Timestamp(Calendar.getInstance().getTimeInMillis());
    }


}
