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

@RestController
@RequestMapping(value = "/api/aviso-viagem")
public class AvisoViagemController {

  private IAvisoViagemRepository avisoViagemRepository;
  private ICartaoRepository cartaoRepository;

  public AvisoViagemController(IAvisoViagemRepository avisoViagemRepository, ICartaoRepository cartaoRepository) {
    this.avisoViagemRepository = avisoViagemRepository;
    this.cartaoRepository = cartaoRepository;
  }

  @PostMapping(value = "/{uuid}")
  private ResponseEntity<?> avisoViagem(@PathVariable("uuid") UUID uuid, @RequestBody @Valid AvisoViagemRequest request,
      HttpServletRequest servletRequest) {
    Optional<Cartao> cartao = cartaoRepository.findByUuid(uuid.toString());

    if (cartao.isEmpty()) {
      ResponseEntity.notFound().build();
    }

    AvisoViagem avisoViagem = request.toModel(cartao.get(), servletRequest.getRemoteAddr(),
        servletRequest.getHeader("User-Agent"));
    avisoViagemRepository.save(avisoViagem);

    return ResponseEntity.ok().build();
  }
}
