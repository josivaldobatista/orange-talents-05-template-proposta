package com.orangetalents.proposta.associacartaopaypal;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.orangetalents.proposta.associacartaoproposta.Cartao;

@Entity
@Table(name = "tb_carteira")
public class Carteira {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonProperty
  private String uuid = UUID.randomUUID().toString();

  @NotBlank
  @Email
  private String email;

  @JsonProperty
  @NotBlank
  private String carteira;

  @OneToOne
  private Cartao cartao;

  @Deprecated
  public Carteira() {
  }

  public Carteira(String email, String carteira, Cartao cartao) {
    this.email = email;
    this.carteira = carteira;
    this.cartao = cartao;
  }

}
