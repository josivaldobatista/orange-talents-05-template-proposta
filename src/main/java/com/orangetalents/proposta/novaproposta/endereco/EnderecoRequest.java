package com.orangetalents.proposta.novaproposta.endereco;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EnderecoRequest {

  @NotBlank
  private String logradouro;

  @NotNull
  private int numero;

  @NotBlank
  private String cidade;

  @NotBlank
  private String pais;

  @NotBlank
  private String estado;

  @NotBlank
  private String cep;

  public EnderecoRequest(String logradouro, int numero, String cidade, String pais, String estado, String cep) {
    this.logradouro = logradouro;
    this.numero = numero;
    this.cidade = cidade;
    this.pais = pais;
    this.estado = estado;
    this.cep = cep;
  }

  public Endereco toModel() {
    return new Endereco(logradouro, numero, cidade, pais, estado, cep);
  }

}
