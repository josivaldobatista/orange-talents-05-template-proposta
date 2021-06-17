package com.orangetalents.proposta.novaproposta;

import java.math.BigDecimal;

import com.orangetalents.proposta.novaproposta.endereco.EnderecoResponse;

public class PropostaResponse {

  private String nome;
  private String email;
  private String documento;
  private EnderecoResponse endereco;
  private BigDecimal salario;
  private Status status;
  private String cartao;

  public PropostaResponse(Proposta entity) {
    nome = entity.getNome();
    email = entity.getEmail();
    documento = entity.getDocumento();
    endereco = new EnderecoResponse(entity.getEndereco());
    salario = entity.getSalario();
    status = entity.getStatus();
    cartao = entity.getCartao().getUuid();
  }

  public String getNome() {
    return this.nome;
  }

  public String getEmail() {
    return this.email;
  }

  public String getDocumento() {
    return this.documento;
  }

  public EnderecoResponse getEndereco() {
    return this.endereco;
  }

  public BigDecimal getSalario() {
    return this.salario;
  }

  public Status getStatus() {
    return this.status;
  }

  public String getCartao() {
    return this.cartao;
  }

}
