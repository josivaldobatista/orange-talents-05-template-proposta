package com.orangetalents.proposta.criarbiometria;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.orangetalents.proposta.associacartaoproposta.Cartao;

@Entity
@Table(name = "tb_biometria")
public class Biometria {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonProperty
  private String uuid = UUID.randomUUID().toString();
  
  @NotNull
  @ElementCollection
  private List<byte[]> fingerprint = new ArrayList<>();

  @Column(nullable = false)
  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
  private LocalDateTime criadoEm = LocalDateTime.now();

  @JsonProperty
  @ManyToOne
  @JoinColumn(name = "cartao_id")
  private Cartao cartao;

  @Deprecated
  public Biometria() {
  }

  public Biometria(@NotNull List<byte[]> fingerprint, Cartao cartao) {
    this.fingerprint.addAll(fingerprint);
    this.cartao = cartao;
  }

  public Long getId() {
    return id;
  }

}
