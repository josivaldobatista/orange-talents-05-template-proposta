package com.orangetalents.proposta.associacartaocarteira;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import com.orangetalents.proposta.associacartaoproposta.Cartao;
import com.orangetalents.proposta.associacartaoproposta.ICartaoRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import feign.FeignException;

@RestController
@RequestMapping(value = "/api/cartoes")
public class AssociaCartaoCarteiraController {

  private ICartaoRepository cartaoRepository;
  private IAssociaCartaoCarteiraClientFeign clientFeign;
  private ICarteiraRepository carteiraRepository;
  private ValidaEmissor validaEmissor;

  public AssociaCartaoCarteiraController(ICartaoRepository cartaoRepository, IAssociaCartaoCarteiraClientFeign clientFeign,
      ICarteiraRepository carteiraRepository, ValidaEmissor validaEmissor) {
    this.cartaoRepository = cartaoRepository;
    this.clientFeign = clientFeign;
    this.carteiraRepository = carteiraRepository;
    this.validaEmissor = validaEmissor;
  }

  @InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(validaEmissor);
	}

  @PostMapping(value = "/{id}/carteiras")
  public ResponseEntity<?> associaCartaoPaypal(@PathVariable("id") UUID id,
      @RequestBody @Valid AssociaCartaoCarteiraRequest request) {
    Optional<Cartao> possivelCartao = cartaoRepository.findByUuid(id.toString());

    if (possivelCartao.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Cartao cartao = possivelCartao.get();

    Optional<Carteira> possivelCarteira = carteiraRepository.findByCartaoAndEmissor(cartao, 
      EEmissor.valueOf(request.getCarteira()));

    if (possivelCarteira.isPresent()) {
      return ResponseEntity.unprocessableEntity().body("resultado: FALHA");
    }
    AssociaCartaoCarteiraResponse response = clientFeign.associaCartaoCarteira(cartao.getNumeroCartao(), request);

    try {
      Carteira carteira = request.toModel(cartao, response.getId());
      carteiraRepository.save(carteira);

    } catch (FeignException e) {
      ResponseEntity.status(e.status()).build();
    }
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(cartao.getUuid()).toUri();
    return ResponseEntity.created(uri).body(response);

  }
}
