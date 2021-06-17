package com.orangetalents.proposta.associacartaoproposta;

import com.orangetalents.proposta.novaproposta.Proposta;

public class CartaoRequest {

  private String idProposta;
  private String documento;
  private String nome;

  public CartaoRequest(Proposta proposta) {
		this.idProposta = proposta.getUuid();
		this.documento = proposta.getDocumento();
		this.nome = proposta.getNome();
	}

  public String getIdProposta() {
    return idProposta;
  }
  
  public String getDocumento() {
    return this.documento;
  }

  public String getNome() {
    return this.nome;
  }

}
