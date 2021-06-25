package com.orangetalents.proposta.bloqueiocartao;

public class BloqueioCartaoRequest {

  private Long idCartao;
  private String ipCliente;
  private String userAgent;

  @Deprecated
  public BloqueioCartaoRequest() {
  }

  public BloqueioCartaoRequest(Long idCartao, String ipCliente, String userAgent) {
    this.idCartao = idCartao;
    this.ipCliente = ipCliente;
    this.userAgent = userAgent;
  }

  public Long getIdCartao() {
    return idCartao;
  }

  public String getIpCliente() {
    return this.ipCliente;
  }

  public String getUserAgent() {
    return this.userAgent;
  }

}
