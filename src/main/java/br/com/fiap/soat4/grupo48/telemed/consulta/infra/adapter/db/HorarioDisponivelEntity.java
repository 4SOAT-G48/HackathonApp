package br.com.fiap.soat4.grupo48.telemed.consulta.infra.adapter.db;

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
@Table(name = "HORARIO_DISPONIVEL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HorarioDisponivelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "medico_id")
    private UUID medicoId;

    @Column(name = "data", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data;

    @Column(name = "hora_inicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;

    @Column(name = "hora_fim")
    @Temporal(TemporalType.TIME)
    private Date horaFim;

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

    @PrePersist
    public void insereDatas() {
        if (Objects.isNull(this.dataCriacao)) {
            this.dataCriacao = new Timestamp(Calendar.getInstance().getTimeInMillis());
            this.dataAtualizacao = new Timestamp(Calendar.getInstance().getTimeInMillis());
        }
    }

    @PreUpdate
    public void atualizaDataAtualizacao() {
        this.dataAtualizacao = new Timestamp(Calendar.getInstance().getTimeInMillis());
    }
}
