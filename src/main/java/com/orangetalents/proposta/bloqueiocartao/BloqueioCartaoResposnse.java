package com.orangetalents.proposta.bloqueiocartao;

import com.orangetalents.proposta.associacartaoproposta.Cartao;

public class BloqueioCartaoResposnse {

  private String resultado;

  public BloqueioCartao toModel(Cartao cartao, String ipCliente, String userAgent) {
    return new BloqueioCartao(ipCliente, userAgent, cartao);
  }

  public String getResultado() {
    return resultado;
  }
}
