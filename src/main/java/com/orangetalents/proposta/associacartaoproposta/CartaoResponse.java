package com.orangetalents.proposta.associacartaoproposta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.orangetalents.proposta.novaproposta.Proposta;

public class CartaoResponse {

  @NotNull
  private String id;

  @NotNull
  private LocalDateTime emitidoEm;

  @NotNull
  private String titular;

  @PositiveOrZero
  private BigDecimal limite;

  public CartaoResponse(@NotNull String id, @NotNull LocalDateTime emitidoEm, @NotNull String titular,
      @PositiveOrZero BigDecimal limite) {
    this.id = id;
    this.emitidoEm = emitidoEm;
    this.titular = titular;
    this.limite = limite;
  }

  public Cartao toModel(Proposta proposta) {
    return new Cartao(id, emitidoEm, titular, limite, proposta);
  }

}
