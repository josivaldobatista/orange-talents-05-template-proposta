package com.orangetalents.proposta.associacartaocarteira;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ValidaEmissor implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return AssociaCartaoCarteiraRequest.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    if (errors.hasErrors()) {
      return;
    }
    AssociaCartaoCarteiraRequest request = (AssociaCartaoCarteiraRequest) target;

    try {
      EEmissor.valueOf(request.getCarteira());
    } catch (IllegalArgumentException e) {
      errors.rejectValue("carteira", null, request.getCarteira() + " não está entre os valores aceitos: "
          + Arrays.stream(EEmissor.values()).map(EEmissor::name).collect(Collectors.toList()));
    }

  }

}
