package com.orangetalents.proposta.avisonovaviagem;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "${host.cartao}/api/cartoes", name = "avisoViagemClient")
public interface IAvisoViagemClientFeign {
  
  @PostMapping(value = "/{id}/avisos")
	AvisoViagemResponse notificaAvisoViagem(@PathVariable("id") String idCartao, AvisoViagemRequest request);
}
