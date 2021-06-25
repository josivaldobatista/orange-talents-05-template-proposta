package com.orangetalents.proposta.criarbiometria;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import com.orangetalents.proposta.associacartaoproposta.Cartao;
import com.orangetalents.proposta.associacartaoproposta.ICartaoRepository;
import com.orangetalents.proposta.compartilhada.exceptions.ResourceNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/api/biometrias")
public class BiometriaController {

  private ICriaBiometriaRepository biometriaRepository;

  private ICartaoRepository cartaoRepository;

  public BiometriaController(ICriaBiometriaRepository biometriaRepository, ICartaoRepository cartaoRepository) {
    this.biometriaRepository = biometriaRepository;
    this.cartaoRepository = cartaoRepository;
  }

  @PostMapping(value = "/{idCartao}")
  public ResponseEntity<?> criarBiometria(@Valid @RequestBody BiometriaRequest request, @PathVariable Long idCartao) {

    Optional<Cartao> possivelCartao = cartaoRepository.findById(idCartao);
    if (possivelCartao.isEmpty()) {
      throw new ResourceNotFoundException("Cartão não encontrado");
    }
    Biometria entity = request.toModel(possivelCartao.get());
    Biometria biometria = biometriaRepository.save(entity);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/biometrias/{idBiometria}")
        .buildAndExpand(biometria.getId()).toUri();

    return ResponseEntity.created(uri).build();
  }

}
