package com.orangetalents.proposta.consultasolicitante;

import javax.validation.constraints.NotBlank;

import com.orangetalents.proposta.novaproposta.Proposta;

public class SolicitanteForm {

  @NotBlank
  private String documento;

  @NotBlank
  private String nome;

  @NotBlank
  private String idProposta;

  private StatusRestricao resultadoSolicitacao;

  @Deprecated
  public SolicitanteForm() {
  }

  public SolicitanteForm(Proposta proposta) {
    this.documento = proposta.getDocumento();
    this.nome = proposta.getNome();
    this.idProposta = proposta.getId().toString();
  }

  public SolicitanteForm(String documento, String nome, String idProposta, StatusRestricao resultadoSolicitacao) {
    this.documento = documento;
    this.nome = nome;
    this.idProposta = idProposta;
    this.resultadoSolicitacao = resultadoSolicitacao;
  }

  public String getDocumento() {
    return this.documento;
  }

  public String getNome() {
    return this.nome;
  }

  public String getIdProposta() {
    return this.idProposta;
  }

  public StatusRestricao getResultadoSolicitacao() {
    return resultadoSolicitacao;
  }

}
