package com.orangetalents.proposta.novaproposta.endereco;

public class EnderecoResponse {

  private String logradouro;
  private int numero;
  private String cidade;
  private String pais;
  private String estado;
  private String cep;

  public EnderecoResponse(Endereco endereco) {
    logradouro = endereco.getLogradouro();
    numero = endereco.getNumero();
    cidade = endereco.getCidade();
    pais = endereco.getPais();
    estado = endereco.getEstado();
    cep = endereco.getCep();
  }

  public String getLogradouro() {
    return this.logradouro;
  }

  public int getNumero() {
    return this.numero;
  }

  public String getCidade() {
    return this.cidade;
  }

  public String getPais() {
    return this.pais;
  }

  public String getEstado() {
    return this.estado;
  }

  public String getCep() {
    return this.cep;
  }

}
