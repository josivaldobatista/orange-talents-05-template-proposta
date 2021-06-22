package com.orangetalents.proposta.criarbiometria;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.orangetalents.proposta.associacartaoproposta.Cartao;

public class BiometriaRequest {

  @NotNull
  private List<String> fingerprint;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public BiometriaRequest(List<String> fingerprint) {
    this.fingerprint = fingerprint;
  }

  public Biometria toModel(Cartao cartao) {
    return new Biometria(transformaEmByte(), cartao);
  }

  private List<byte[]> transformaEmByte() {
    return this.fingerprint.stream().map(String::getBytes).collect(Collectors.toList());
  }

  public List<String> getFingerprint() {
    return fingerprint;
  }

}
