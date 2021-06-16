package com.orangetalents.proposta.associacartaoproposta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
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
  private String id;

  @NotNull
  private LocalDateTime emitidoEm;

  @NotNull
  private String titular;

  @PositiveOrZero
  private BigDecimal limite;

  @OneToOne
  private Proposta proposta;

  public Cartao(String id, @NotNull LocalDateTime emitidoEm, @NotNull String titular, BigDecimal limite,
      Proposta proposta) {
    this.id = id;
    this.emitidoEm = emitidoEm;
    this.titular = titular;
    this.limite = limite;
    this.proposta = proposta;
  }

  @Deprecated
  public Cartao() {
  }

}
