package com.orangetalents.proposta.associacartaocarteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.orangetalents.proposta.associacartaoproposta.Cartao;

public class AssociaCartaoCarteiraRequest {

  @JsonProperty
  @NotBlank
  @Email
  private String email;

  @JsonProperty
  @NotNull
  private String carteira;

  public AssociaCartaoCarteiraRequest(String email, String carteira) {
    this.email = email;
    this.carteira = carteira;
  }

  public Carteira toModel(Cartao cartao, String uuid) {
    return new Carteira(uuid, email, carteira, cartao);
  }

  public String getCarteira() {
    return carteira;
  }

}
