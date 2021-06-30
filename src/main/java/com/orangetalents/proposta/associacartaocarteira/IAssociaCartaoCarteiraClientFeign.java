package com.orangetalents.proposta.associacartaocarteira;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${host.cartao}/api/cartoes", name = "associaCartaoCarteira")
public interface IAssociaCartaoCarteiraClientFeign {

  @PostMapping("/{id}/carteiras")
  AssociaCartaoCarteiraResponse associaCartaoCarteira(@PathVariable("id") String idCartao,
      @RequestBody @Valid AssociaCartaoCarteiraRequest request);
}
