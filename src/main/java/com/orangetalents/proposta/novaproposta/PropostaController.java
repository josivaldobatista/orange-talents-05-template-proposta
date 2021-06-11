package com.orangetalents.proposta.novaproposta;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
  
  @PostMapping
  public ResponseEntity<?> novaProposta(@RequestBody @Valid PropostaRequest request) {
    Proposta entity = request.toModel();
    repositry.save(entity);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
      .buildAndExpand(entity.getId()).toUri();

    return ResponseEntity.created(uri).build();
  }
}
