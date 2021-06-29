package com.orangetalents.proposta.associacartaopaypal;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import com.orangetalents.proposta.associacartaoproposta.Cartao;
import com.orangetalents.proposta.associacartaoproposta.ICartaoRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import feign.FeignException;

@RestController
@RequestMapping(value = "/api/cartoes")
public class AssociaCartaoPaypalController {

  private ICartaoRepository cartaoRepository;
  private IAssociaCartaoPaypalClientFeign clientFeign;
  private ICarteiraRepository carteiraRepository;

  public AssociaCartaoPaypalController(ICartaoRepository cartaoRepository, IAssociaCartaoPaypalClientFeign clientFeign,
      ICarteiraRepository carteiraRepository) {
    this.cartaoRepository = cartaoRepository;
    this.clientFeign = clientFeign;
    this.carteiraRepository = carteiraRepository;
  }

  @PostMapping(value = "/{id}/carteiras")
  public ResponseEntity<?> associaCartaoPaypal(@PathVariable("id") UUID id,
      @RequestBody @Valid AssociaCartaoPaypalRequest request) {
    Optional<Cartao> possivelCartao = cartaoRepository.findByUuid(id.toString());

    if (possivelCartao.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Cartao cartao = possivelCartao.get();

    Optional<Carteira> possivelCarteira = carteiraRepository.findByCartaoAndCarteira(cartao, request.getCarteira());

    if (possivelCarteira.isPresent()) {
      return ResponseEntity.unprocessableEntity().body("resultado: FALHA");
    }
    AssociaCartaoPaypalResponse response = clientFeign.associaCartaoPaypal(cartao.getNumeroCartao(), request);

    try {
      Carteira carteira = request.toModel(cartao);
      carteiraRepository.save(carteira);

    } catch (FeignException e) {
      ResponseEntity.status(e.status()).build();
    }
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(cartao.getUuid()).toUri();
    return ResponseEntity.created(uri).body(response);

  }
}
