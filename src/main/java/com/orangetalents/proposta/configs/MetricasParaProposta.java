package com.orangetalents.proposta.configs;

import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Component
public class MetricasParaProposta {

  private final MeterRegistry meterRegistry;

  public MetricasParaProposta(MeterRegistry meterRegistry) {
    this.meterRegistry = meterRegistry;
  }

  public void contaPropostasCriadas() {
    contador("proposta_criada");
  }

  private void contador(String nomeDaMetrica) {
    Counter counter = this.meterRegistry.counter(nomeDaMetrica);
    counter.increment();
  }
}
