package com.orangetalents.proposta.consultasolicitante;

import com.orangetalents.proposta.novaproposta.Status;

public enum StatusRestricao {

  COM_RESTRICAO, SEM_RESTRICAO;

  public Status normalizaStatus() {
    if (this == SEM_RESTRICAO) {
      return Status.ELEGIVEL;
    }
    return Status.NAO_ELEGIVEL;
  }
}
