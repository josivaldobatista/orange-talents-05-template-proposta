package com.orangetalents.proposta.associacartaoproposta;

import java.util.UUID;

import com.orangetalents.proposta.novaproposta.Proposta;

public class CartaoRequest {

  private UUID idProposta;
  private String documento;
  private String nome;

  public CartaoRequest(Proposta proposta) {
		this.idProposta = proposta.getId();
		this.documento = proposta.getDocumento();
		this.nome = proposta.getNome();
	}

  public UUID getIdProposta() {
    return idProposta;
  }

  public String getDocumento() {
    return this.documento;
  }

  public String getNome() {
    return this.nome;
  }

}
