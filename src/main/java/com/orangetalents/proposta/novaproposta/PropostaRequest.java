package com.orangetalents.proposta.novaproposta;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.orangetalents.proposta.compartilhada.utils.EncryptEDecrypt;
import com.orangetalents.proposta.compartilhada.validacoes.CpfOrCnpjValue;
import com.orangetalents.proposta.compartilhada.validacoes.UniqueValue;
import com.orangetalents.proposta.novaproposta.endereco.EnderecoRequest;

public class PropostaRequest {

  @NotBlank
  private String nome;

  @NotBlank
  @Email
  private String email;

  @NotBlank
  @CpfOrCnpjValue
  @UniqueValue(domainClass = Proposta.class, fieldName = "documento")
  private String documento;

  @NotNull
  private EnderecoRequest endereco;

  @Positive
  @NotNull
  private BigDecimal salario;

  public PropostaRequest(String nome, String email, String documento, EnderecoRequest endereco, 
    BigDecimal salario) {
    this.nome = nome;
    this.email = email;
    this.documento = documento;
    this.endereco = endereco;
    this.salario = salario;
  }
  
  public Proposta toModel(EncryptEDecrypt encryptEDecrypt) {
    this.documento = encryptEDecrypt.encrypt(documento);
    return new Proposta(nome, email, documento, endereco.toModel(), salario);
  }

  public String getNome() {
    return this.nome;
  }

  public String getEmail() {
    return this.email;
  }

  public String getDocumento() {
    return documento;
  }

  public EnderecoRequest getEndereco() {
    return endereco;
  }

  public BigDecimal getSalario() {
    return this.salario;
  }

}
