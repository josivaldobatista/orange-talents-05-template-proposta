package com.orangetalents.proposta.novaproposta.endereco;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Embeddable
public class Endereco {

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

  @Deprecated
  public Endereco() {
  }

  public Endereco(String logradouro, int numero, String cidade, String pais, String estado, String cep) {
    this.logradouro = logradouro;
    this.numero = numero;
    this.cidade = cidade;
    this.pais = pais;
    this.estado = estado;
    this.cep = cep;
  }

}
