package com.orangetalents.proposta.consultasolicitante;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "http://localhost:9999", name = "consultaRestricao")
public interface ConsultaSolicitante {

  @RequestMapping(method = RequestMethod.POST, value = "/api/solicitacao", consumes = "application/json")
  ResponseEntity<SolicitanteForm> consultaRestricaoSolicitante(
    @RequestBody SolicitanteForm formAnalise);
}
