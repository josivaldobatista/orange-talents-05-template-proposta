package com.orangetalents.proposta.novaproposta;

import java.net.URI;

import javax.validation.Valid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.orangetalents.proposta.consultasolicitante.ConsultaRestricao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/propostas")
public class PropostaController {

  @Autowired
  private PropostaRepository repositry;

  @Autowired
  private ConsultaRestricao consultaRestricao;
  
  @PostMapping
  @Transactional
  public ResponseEntity<?> novaProposta(@RequestBody @Valid PropostaRequest request) throws JsonMappingException, JsonProcessingException {
    Proposta entity = request.toModel();
    repositry.save(entity);
    consultaRestricao.consulta(entity);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
      .buildAndExpand(entity.getId()).toUri();

    return ResponseEntity.created(uri).build();
  }
}
