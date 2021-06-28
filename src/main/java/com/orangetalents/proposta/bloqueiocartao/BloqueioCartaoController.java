package com.orangetalents.proposta.bloqueiocartao;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.orangetalents.proposta.associacartaoproposta.Cartao;
import com.orangetalents.proposta.associacartaoproposta.ICartaoRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/bloqueios")
public class BloqueioCartaoController {

  private RealizaBloqueioCartao realizaBloqueioCartao;
  private ICartaoRepository cartaoRepository;

  public BloqueioCartaoController(RealizaBloqueioCartao realizaBloqueioCartao, ICartaoRepository cartaoRepository) {
    this.realizaBloqueioCartao = realizaBloqueioCartao;
    this.cartaoRepository = cartaoRepository;
  }

  @PostMapping(value = "/{id}")
  private ResponseEntity<?> bloqueioCartao(@PathVariable("id") String numeroCartao, HttpServletRequest request) {
    Optional<Cartao> possivelCartao = cartaoRepository.findByNumeroCartao(numeroCartao);

    if (possivelCartao.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Cartao cartao = possivelCartao.get();

    if (cartao.bloqueado()) {
      return ResponseEntity.unprocessableEntity().body("Este cartão já está bloqueado");
    }

    realizaBloqueioCartao.bloqueiaCartaoApiProposta(cartao, request.getRemoteAddr(), 
      request.getHeader("User-Agent"));
    return ResponseEntity.ok().build();
  }
}
