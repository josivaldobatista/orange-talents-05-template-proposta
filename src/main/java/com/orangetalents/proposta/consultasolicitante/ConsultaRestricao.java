package com.orangetalents.proposta.consultasolicitante;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orangetalents.proposta.novaproposta.Proposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import feign.FeignException;

@Component
public class ConsultaRestricao {

  @Autowired
  private IConsultaSolicitante consultaDadosSolicitante;

  public void consulta(Proposta proposta) throws JsonProcessingException {
    SolicitanteForm solicitanteForm = new SolicitanteForm(proposta);

    try {
      ResponseEntity<SolicitanteForm> restricaoResponse = consultaDadosSolicitante
          .consultaRestricaoSolicitante(solicitanteForm);
      proposta.atualizaStatus(restricaoResponse.getBody().getResultadoSolicitacao().normalizaStatus());
    } catch (FeignException e) {
      // HttpStatus status = HttpStatus.valueOf(e.status());
      String body = e.contentUTF8();
      SolicitanteForm payload = new ObjectMapper().readValue(body, SolicitanteForm.class);
      proposta.atualizaStatus(payload.getResultadoSolicitacao().normalizaStatus());
    }
  }
}
