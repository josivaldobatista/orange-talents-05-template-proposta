package com.orangetalents.proposta.associacartaocarteira;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
  @NotNull
  @Enumerated(EnumType.STRING)
  private EEmissor emissor;

  @OneToOne
  private Cartao cartao;

  @Deprecated
  public Carteira() {
  }

  public Carteira(String uuid, String email, String emissor, Cartao cartao) {
    this.uuid = uuid;
    this.email = email;
    this.emissor = EEmissor.valueOf(emissor);
    this.cartao = cartao;
  }

}
