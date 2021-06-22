package com.orangetalents.proposta.compartilhada.exceptions;

import org.springframework.http.HttpStatus;

public class ErroApiException extends RuntimeException {
  
  private final HttpStatus status;
  private final String mensagem;


  public ErroApiException(HttpStatus status, String mensagem) {
    this.status = status;
    this.mensagem = mensagem;
  }

  public HttpStatus getStatus() {
    return this.status;
  }

  public String getMensagem() {
    return this.mensagem;
  }

}
