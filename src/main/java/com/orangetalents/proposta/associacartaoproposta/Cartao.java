package com.orangetalents.proposta.associacartaoproposta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.orangetalents.proposta.novaproposta.Proposta;

@Entity
@Table(name = "tb_cartao")
public class Cartao {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String idCartao;

  @NotNull
  private LocalDateTime emitidoEm;

  @NotNull
  private String titular;

  @PositiveOrZero
  private BigDecimal limite;

  @OneToOne
  private Proposta proposta;

  @JsonProperty
  private Boolean bloqueado = false;

  public Cartao(String idCartao, LocalDateTime emitidoEm, String titular, BigDecimal limite, 
      Proposta proposta) {
    this.idCartao = idCartao;
    this.emitidoEm = emitidoEm;
    this.titular = titular;
    this.limite = limite;
    this.proposta = proposta;
  }

  @Deprecated
  public Cartao() {
  }

  public String getIdCartao() {
    return idCartao;
  }

  public LocalDateTime getEmitidoEm() {
    return this.emitidoEm;
  }

  public String getTitular() {
    return this.titular;
  }

  public BigDecimal getLimite() {
    return this.limite;
  }

  public Proposta getProposta() {
    return this.proposta;
  }

  public Boolean getBloqueado() {
    return bloqueado;
  }

  public void setBloqueado(Boolean bloqueado) {
    this.bloqueado = bloqueado;
  }

}
