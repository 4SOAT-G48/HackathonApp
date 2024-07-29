package br.com.fiap.soat4.grupo48.telemed.cadastro.infra.adapter.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * Classe abstrata que representa a entidade Pessoa no banco de dados.
 * Esta classe é utilizada como superclasse para todas as entidades que representam tipos específicos de pessoas,
 * como Admins, Médicos, etc., utilizando a estratégia de tabela única para herança.
 */
@Setter
@Getter
// Indica que esta classe é uma superclasse mapeada e seus atributos serão mapeados nas classes filhas.
@MappedSuperclass
@DiscriminatorColumn(name = "tipo_pessoa", discriminatorType = DiscriminatorType.STRING)
// Define a coluna discriminadora para diferenciar os tipos de Pessoa.
public abstract class PessoaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Configura a geração automática do identificador.
    private UUID id;
    private String nome;
    private String email;

    @Column(name = "data_criacao", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;

    @Column(name = "data_atualizacao", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
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