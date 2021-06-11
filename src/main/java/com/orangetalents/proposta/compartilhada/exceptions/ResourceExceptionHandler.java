package com.orangetalents.proposta.compartilhada.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<StandardError> erroDeValidacao(MethodArgumentNotValidException e, HttpServletRequest request) {
    StandardError erro = new StandardError();
    HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
    erro.setTimestamp(Instant.now());
    erro.setStatus(status.value());
    erro.setError("Erro de validação");
    erro.setMessage(e.getMessage());
    erro.setPath(request.getRequestURI());

    for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
      erro.addError(fieldError.getField(), fieldError.getDefaultMessage());
    }
    return ResponseEntity.status(status).body(erro);
  }
}
