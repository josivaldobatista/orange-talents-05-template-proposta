package com.orangetalents.proposta.associacartaoproposta;

import com.orangetalents.proposta.bloqueiocartao.BloqueioCartaoRequest;
import com.orangetalents.proposta.bloqueiocartao.BloqueioCartaoResposnse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "${host.cartao}/api/cartoes", name = "cartaocliente")
public interface IConsultaCartao {
  
  @PostMapping
  ResponseEntity<CartaoResponse> consultar(CartaoRequest request);

  @RequestMapping(method = RequestMethod.POST, path = "/{numeroCartao}/bloqueios")
  BloqueioCartaoResposnse bloquearCartao(@PathVariable("numeroCartao") String numeroCartao, BloqueioCartaoRequest request);
}
