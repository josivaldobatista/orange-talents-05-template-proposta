package com.orangetalents.proposta.avisonovaviagem;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.orangetalents.proposta.associacartaoproposta.Cartao;

public class AvisoViagemRequest {

  @NotBlank
  private String destino;

  @NotNull
  @FutureOrPresent
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  private LocalDate terminoViagem;

  @JsonCreator
  public AvisoViagemRequest(String destino, LocalDate terminoViagem) {
    this.destino = destino;
    this.terminoViagem = terminoViagem;
  }

  public AvisoViagem toModel(Cartao cartao, String ipCliente, String userAgent) {
    return new AvisoViagem(destino, terminoViagem, ipCliente, userAgent, cartao);
  }

}
