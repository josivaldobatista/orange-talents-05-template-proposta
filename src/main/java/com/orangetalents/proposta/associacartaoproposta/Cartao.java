package com.orangetalents.proposta.associacartaoproposta;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.orangetalents.proposta.novaproposta.Proposta;

@Entity
@Table(name = "tb_cartao")
public class Cartao {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  @NotNull
  private String uuid = UUID.randomUUID().toString();

  @NotNull
  private LocalDateTime emitidoEm;

  @NotNull
  private String titular;

  @PositiveOrZero
  private BigDecimal limite;

  @OneToOne
  private Proposta proposta;

  public Cartao(String uuid, LocalDateTime emitidoEm, String titular, BigDecimal limite, Proposta proposta) {
    this.uuid = uuid;
    this.emitidoEm = emitidoEm;
    this.titular = titular;
    this.limite = limite;
    this.proposta = proposta;
  }

  @Deprecated
  public Cartao() {
  }

  public String getUuid() {
    return uuid;
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

}
