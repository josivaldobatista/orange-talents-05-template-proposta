package com.orangetalents.proposta.associacartaoproposta;

import java.util.Set;

import com.orangetalents.proposta.novaproposta.Proposta;
import com.orangetalents.proposta.novaproposta.PropostaRepository;
import com.orangetalents.proposta.novaproposta.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import feign.FeignException;

@Component
public class AssociaCartaoProposta {

  Logger logger = LoggerFactory.getLogger(AssociaCartaoProposta.class);

  @Autowired
  private IConsultaCartao iConsultaCartao;

  @Autowired
  private PropostaRepository repository;

  @Scheduled(fixedDelay = 5000)
  public void executar() {
    Set<Proposta> propostasElegiveis = repository.findByCartaoIsNullAndStatus(Status.ELEGIVEL);
    propostasElegiveis.forEach(proposta -> associarCartao(proposta));
  }

  private void associarCartao(Proposta proposta) {
    try {
      CartaoRequest request = new CartaoRequest(proposta);
      ResponseEntity<CartaoResponse> responseEntity = iConsultaCartao.consultar(request);
      CartaoResponse response = responseEntity.getBody();
      Cartao cartao = response.toModel(proposta);
      proposta.setCartao(cartao);
      repository.save(proposta);
    } catch (FeignException e) {
      logger.warn(e.contentUTF8().toString());
    }
  }
}
