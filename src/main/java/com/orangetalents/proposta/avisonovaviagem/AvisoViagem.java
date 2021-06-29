package com.orangetalents.proposta.avisonovaviagem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.orangetalents.proposta.associacartaoproposta.Cartao;

@Entity
@Table(name = "tb_aviso_viagem")
public class AvisoViagem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonProperty
  private String uuid = UUID.randomUUID().toString();

  @NotBlank
  private String destino;

  @JsonProperty
  private LocalDate validoAte;

  @JsonProperty
  private LocalDateTime instante = LocalDateTime.now();

  @JsonProperty
  private String ipCliente;

  @JsonProperty
  private String userAgent;

  @OneToOne
  private Cartao cartao;

  @Deprecated
  public AvisoViagem() {
  }

  public AvisoViagem(String destino, LocalDate validoAte, String ipCliente, String userAgent, Cartao cartao) {
    this.destino = destino;
    this.validoAte = validoAte;
    this.ipCliente = ipCliente;
    this.userAgent = userAgent;
    this.cartao = cartao;
  }

}
