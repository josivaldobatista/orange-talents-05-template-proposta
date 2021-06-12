package com.orangetalents.proposta.novaproposta;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.orangetalents.proposta.novaproposta.endereco.Endereco;

@Entity
@Table(name = "tb_proposta")
public class Proposta {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  private @NotBlank String nome;
  private @NotBlank @Email String email;
  private @NotBlank String documento;

  private @Embedded @NotNull Endereco endereco;

  private @Positive @NotNull BigDecimal salario;

  private @Enumerated Status status;

  @Deprecated
  public Proposta() {
  }

  public Proposta(String nome, String email, String documento, Endereco endereco, BigDecimal salario) {
    this.nome = nome;
    this.email = email;
    this.documento = documento;
    this.endereco = endereco;
    this.salario = salario;
  }

  public void atualizaStatus(Status status) {
    this.status = status;
  }

  public UUID getId() {
    return id;
  }

  public String getDocumento() {
    return documento;
  }

  public String getNome() {
    return nome;
  }

}
