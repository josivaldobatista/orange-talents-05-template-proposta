package com.orangetalents.proposta.associacartaopaypal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.orangetalents.proposta.associacartaoproposta.Cartao;

public class AssociaCartaoPaypalRequest {

  @JsonProperty
  @NotBlank
  @Email
  private String email;

  @JsonProperty
  @NotBlank
  private String carteira;

  public AssociaCartaoPaypalRequest(String email, String carteira) {
    this.email = email;
    this.carteira = carteira;
  }

  public Carteira toModel(Cartao cartao) {
    return new Carteira(email, carteira, cartao);
  }

  public String getCarteira() {
    return carteira;
  }

}
