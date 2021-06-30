package com.orangetalents.proposta.novaproposta;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.orangetalents.proposta.configs.MetricasParaProposta;
import com.orangetalents.proposta.consultasolicitante.ConsultaRestricao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.opentracing.Span;
import io.opentracing.Tracer;

@RestController
@RequestMapping(value = "/api/propostas")
public class PropostaController {

  private final Tracer tracer;

  private IPropostaRepository propostaRepositry;
  private ConsultaRestricao consultaRestricao;
  private MetricasParaProposta metricasParaProposta;

  private final Logger logger = LoggerFactory.getLogger(PropostaController.class);

  public PropostaController(Tracer tracer, IPropostaRepository propostaRepositry, ConsultaRestricao consultaRestricao,
      MetricasParaProposta metricasParaProposta) {
    this.tracer = tracer;
    this.propostaRepositry = propostaRepositry;
    this.consultaRestricao = consultaRestricao;
    this.metricasParaProposta = metricasParaProposta;
  }

  @GetMapping(value = "/{uuid}")
  public ResponseEntity<?> buscarPropostaPorUuid(@PathVariable("uuid") String uuid) {
    Optional<Proposta> entity = propostaRepositry.findByUuid(uuid);
    if (entity.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    Proposta proposta = entity.get();
    PropostaResponse response = new PropostaResponse(proposta);
    return ResponseEntity.ok().body(response);
  }

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
    
    // Testes de OpenTracer
    Span activeSpan = tracer.activeSpan();
    activeSpan.setTag("proposta.email", proposta.getEmail());
    activeSpan.setBaggageItem("proposta.email", proposta.getEmail());
    activeSpan.log("Meu log");

    consultaRestricao.consulta(proposta);

    logger.info(String.format("Proposta criada com sucesso para: {nome: %s, documento: %s}", proposta.getNome(),
        proposta.getDocumento()));

    metricasParaProposta.contaPropostasCriadas();

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(proposta.getId()).toUri();

    return ResponseEntity.created(uri).build();
  }
}
