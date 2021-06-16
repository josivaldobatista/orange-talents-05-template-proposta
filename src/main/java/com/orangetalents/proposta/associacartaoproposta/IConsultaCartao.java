package com.orangetalents.proposta.associacartaoproposta;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "${host.cartao}", name = "cartaocliente")
public interface IConsultaCartao {
  
  @PostMapping("/api/cartoes")
  ResponseEntity<CartaoResponse> consultar(CartaoRequest request);
}
