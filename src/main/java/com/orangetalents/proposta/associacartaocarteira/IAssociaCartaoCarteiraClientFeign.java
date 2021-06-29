package com.orangetalents.proposta.associacartaocarteira;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${host.cartao}/api/cartoes", name = "associaCartaoPaypal")
public interface IAssociaCartaoCarteiraClientFeign {

  @PostMapping("/{id}/carteiras")
  AssociaCartaoCarteiraResponse associaCartaoPaypal(@PathVariable("id") String idCartao,
      @RequestBody @Valid AssociaCartaoCarteiraRequest request);
}
