package com.orangetalents.proposta.novaproposta;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.orangetalents.proposta.associacartaoproposta.Cartao;
import com.orangetalents.proposta.novaproposta.endereco.Endereco;

import org.springframework.lang.Nullable;

@Entity
@Table(name = "tb_proposta")
public class Proposta {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String uuid = UUID.randomUUID().toString();

  private @NotBlank String nome;
  private @NotBlank @Email String email;
  private @NotBlank String documento;

  private @Embedded @NotNull Endereco endereco;

  private @Positive @NotNull BigDecimal salario;

  private @Enumerated Status status;

  @Nullable
  @OneToOne(mappedBy = "proposta", cascade = CascadeType.MERGE)
  private Cartao cartao;

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

  public Long getId() {
    return id;
  }

  public String getUuid() {
    return uuid;
  }

  public String getDocumento() {
    return documento;
  }

  public String getNome() {
    return nome;
  }

  public void setCartao(Cartao cartao) {
    this.cartao = cartao;
  }

  public String getEmail() {
    return this.email;
  }

  public Endereco getEndereco() {
    return this.endereco;
  }

  public BigDecimal getSalario() {
    return this.salario;
  }

  public Status getStatus() {
    return this.status;
  }

  public Cartao getCartao() {
    return this.cartao;
  }

}
