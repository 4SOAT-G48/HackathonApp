package br.com.fiap.soat4.grupo48.telemed.cadastro.infra.adapter.db;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa a entidade Admin no banco de dados.
 * Esta classe é uma extensão da classe PessoaEntity, indicando uma especialização da entidade Pessoa para Admins.
 * Utiliza anotações do JPA para mapeamento objeto-relacional.
 */
@Entity
@DiscriminatorValue("ADMIN") // Define o valor do discriminador para diferenciar entre tipos de Pessoa.
@Table(name = "ADMIN")
@Getter
@Setter
@AllArgsConstructor
public class AdminEntity extends PessoaEntity {

}