package com.orangetalents.proposta.compartilhada.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class StandardError {

  private Instant timestamp;
  private Integer status;
  private String error;
  private String message;
  private String path;

  private List<FieldMessage> errors = new ArrayList<>();

  public StandardError() {
  }

  public List<FieldMessage> getErrors() {
    return this.errors;
  }

  public void addError(String fieldName, String message) {
    errors.add(new FieldMessage(fieldName, message));
  }

  public Instant getTimestamp() {
    return this.timestamp;
  }

  public void setTimestamp(Instant timestamp) {
    this.timestamp = timestamp;
  }

  public Integer getStatus() {
    return this.status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getError() {
    return this.error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getPath() {
    return this.path;
  }

  public void setPath(String path) {
    this.path = path;
  }
}