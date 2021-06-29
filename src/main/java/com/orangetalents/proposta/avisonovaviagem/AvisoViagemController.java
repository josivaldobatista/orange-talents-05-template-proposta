package com.orangetalents.proposta.avisonovaviagem;

import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.orangetalents.proposta.associacartaoproposta.Cartao;
import com.orangetalents.proposta.associacartaoproposta.ICartaoRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import feign.FeignException;

@RestController
@RequestMapping(value = "/api/aviso-viagem")
public class AvisoViagemController {

  private IAvisoViagemRepository avisoViagemRepository;
  private ICartaoRepository cartaoRepository;
  private IAvisoViagemClientFeign clientFeign;

  public AvisoViagemController(IAvisoViagemRepository avisoViagemRepository, ICartaoRepository cartaoRepository,
      IAvisoViagemClientFeign clientFeign) {
    this.avisoViagemRepository = avisoViagemRepository;
    this.cartaoRepository = cartaoRepository;
    this.clientFeign = clientFeign;
  }

  @PostMapping(value = "/{uuid}")
  private ResponseEntity<AvisoViagemResponse> avisoViagem(@PathVariable("uuid") UUID id,
      @RequestBody @Valid AvisoViagemRequest request, HttpServletRequest servletRequest) {
    Optional<Cartao> possivelCartao = cartaoRepository.findByUuid(id.toString());

    if (possivelCartao.isEmpty()) {
      ResponseEntity.notFound().build();
    }

    Cartao cartao = possivelCartao.get();
    AvisoViagemResponse response = clientFeign.notificaAvisoViagem(cartao.getNumeroCartao(), request);
    try {
      if (response.getResultado().equals("CRIADO")) {
        AvisoViagem avisoViagem = request.toModel(cartao, servletRequest.getRemoteAddr(),
            servletRequest.getHeader("User-Agent"));
        avisoViagemRepository.save(avisoViagem);
      }

    } catch (FeignException e) {
      ResponseEntity.status(e.status()).build();
    }
    return ResponseEntity.ok().body(response);

  }
}
