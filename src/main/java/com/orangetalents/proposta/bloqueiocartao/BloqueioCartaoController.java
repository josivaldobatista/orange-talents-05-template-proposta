package com.orangetalents.proposta.bloqueiocartao;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.orangetalents.proposta.associacartaoproposta.Cartao;
import com.orangetalents.proposta.associacartaoproposta.ICartaoRepository;
import com.orangetalents.proposta.compartilhada.exceptions.ResourceNotFoundException;
import com.orangetalents.proposta.compartilhada.exceptions.UnprocessableEntityException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/bloqueios")
public class BloqueioCartaoController {

  private IBloqueioCartaoRepository bloqueioCartaoRepository;

  private ICartaoRepository cartaoRepository;

  public BloqueioCartaoController(IBloqueioCartaoRepository bloqueioCartaoRepository, ICartaoRepository cartaoRepository) {
    this.bloqueioCartaoRepository = bloqueioCartaoRepository;
    this.cartaoRepository = cartaoRepository;
  }

  @PostMapping(value = "/{idCartao}")
  private ResponseEntity<?> bloqueioCartao(@Valid BloqueioCartaoRequest request,
      @PathVariable Long idCartao, HttpServletRequest servletRequest) {

    String userAgent = servletRequest.getHeader("User-Agent");
    String ipCliente = servletRequest.getHeader("X-FORWARDED-FOR");

    if (ipCliente == null) {
      ipCliente = servletRequest.getRemoteHost();
    }

    Optional<Cartao> cartao = cartaoRepository.findById(idCartao);
    if (cartao.isEmpty()) {
      throw new ResourceNotFoundException("Cartão não encontrado");
    }

    if (cartao.get().getBloqueado() == true) {
      throw new UnprocessableEntityException("Cartão já está bloqueado");
    }
    cartao.get().setBloqueado(true);

    cartaoRepository.save(cartao.get());

    BloqueioCartao bloqueioCartao = new BloqueioCartao(ipCliente, userAgent, idCartao);
    bloqueioCartaoRepository.save(bloqueioCartao);

    return ResponseEntity.ok().build();
  } 
}
