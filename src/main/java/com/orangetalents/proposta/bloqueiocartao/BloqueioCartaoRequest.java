package com.orangetalents.proposta.bloqueiocartao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BloqueioCartaoRequest {

	@JsonProperty
	private String sistemaResponsavel;

	public BloqueioCartaoRequest(String sistemaResponsavel) {
		this.sistemaResponsavel = sistemaResponsavel;
	}

}
