package com.orangetalents.proposta.bloqueiocartao;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "tb_bloqueio_cartao")
public class BloqueioCartao {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonProperty
  private String uuid = UUID.randomUUID().toString();

  @JsonProperty
  private LocalDateTime instanteBloqueio = LocalDateTime.now();

  @NotBlank  
  private String ipCliente;

  @NotBlank
  private String userAgent;

  @NotNull
  private String idCartao;

  @Deprecated
  public BloqueioCartao() {
  }

  public BloqueioCartao(String ipCliente, String userAgent, String idCartao) {
    this.ipCliente = ipCliente;
    this.userAgent = userAgent;
    this.idCartao = idCartao;
  }

  public Long getId() {
    return id;
  }

}
