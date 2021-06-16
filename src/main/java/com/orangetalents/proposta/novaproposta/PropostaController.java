package com.orangetalents.proposta.novaproposta;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.orangetalents.proposta.consultasolicitante.ConsultaRestricao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/propostas")
public class PropostaController {

  @Autowired
  private PropostaRepository propostaRepositry;

  @Autowired
  private ConsultaRestricao consultaRestricao;

  @PostMapping
  @Transactional
  public ResponseEntity<?> novaProposta(@RequestBody @Valid PropostaRequest request)
      throws JsonMappingException, JsonProcessingException {
        
    Optional<Proposta> possivelProposta = propostaRepositry.findByDocumento(request.getDocumento());
    if (possivelProposta.isPresent()) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Documento informado já possui uma proposta");
    }
    Proposta proposta = request.toModel();
    propostaRepositry.save(proposta);

    consultaRestricao.consulta(proposta);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
      .buildAndExpand(proposta.getId()).toUri();

    return ResponseEntity.created(uri).build();
  }
}
