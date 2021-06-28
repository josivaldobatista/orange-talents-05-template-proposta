package com.orangetalents.proposta.associacartaoproposta;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.orangetalents.proposta.novaproposta.Proposta;

@Entity
@Table(name = "tb_cartao")
public class Cartao {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String uuid = UUID.randomUUID().toString();

  @NotBlank
  private String numeroCartao;

  @NotNull
  private LocalDateTime emitidoEm;

  @NotNull
  private String titular;

  @PositiveOrZero
  private BigDecimal limite;

  @OneToOne
  private Proposta proposta;

  @Enumerated(EnumType.STRING)
  private EStatusCartao status = EStatusCartao.ATIVO;

  @Deprecated
  public Cartao() {
  }

  public Cartao(String numeroCartao, LocalDateTime emitidoEm, String titular, BigDecimal limite, Proposta proposta) {
    this.numeroCartao = numeroCartao;
    this.emitidoEm = emitidoEm;
    this.titular = titular;
    this.limite = limite;
    this.proposta = proposta;
  }

  public String getUuid() {
    return uuid;
  }

  public String getNumeroCartao() {
    return numeroCartao;
  }

  public LocalDateTime getEmitidoEm() {
    return this.emitidoEm;
  }

  public String getTitular() {
    return this.titular;
  }

  public BigDecimal getLimite() {
    return this.limite;
  }

  public Proposta getProposta() {
    return this.proposta;
  }

  public boolean bloqueado() {
    return status.equals(EStatusCartao.BLOQUEADO);
  }

  public void alterarStatusParaBloqueioPendente() {
    this.status = EStatusCartao.BLOQUEIO_PENDENTE;
  }

  public void bloquear() {
    this.status = EStatusCartao.BLOQUEADO;
  }

  @Override
  public String toString() {
    return "Cartao [emitidoEm=" + emitidoEm + ", id=" + id + ", limite=" + limite + ", numeroCartao=" + numeroCartao
        + ", proposta=" + proposta + ", status=" + status + ", titular=" + titular + ", uuid=" + uuid + "]";
  }

}
