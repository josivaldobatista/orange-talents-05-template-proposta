package com.orangetalents.proposta.bloqueiocartao;

import java.util.List;
import java.util.Optional;

import com.orangetalents.proposta.associacartaoproposta.Cartao;
import com.orangetalents.proposta.associacartaoproposta.EStatusCartao;
import com.orangetalents.proposta.associacartaoproposta.ICartaoRepository;
import com.orangetalents.proposta.associacartaoproposta.IConsultaCartao;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import feign.FeignException;

@Component
public class RealizaBloqueioCartao {

  private IBloqueioCartaoRepository bloqueioCartaoRepository;
  private ICartaoRepository cartaoRepository;
  private IConsultaCartao consultaCartao;

  public RealizaBloqueioCartao(IBloqueioCartaoRepository bloqueioCartaoRepository, ICartaoRepository cartaoRepository,
      IConsultaCartao consultaCartao) {
    this.bloqueioCartaoRepository = bloqueioCartaoRepository;
    this.cartaoRepository = cartaoRepository;
    this.consultaCartao = consultaCartao;
  }

  public void bloqueiaCartaoApiProposta(Cartao cartao, String userAgent, String ipCliente) {
    BloqueioCartao bloqueioCartao = new BloqueioCartao(ipCliente, userAgent, cartao);
    bloqueioCartaoRepository.save(bloqueioCartao);
    cartao.alterarStatusParaBloqueioPendente();
    cartaoRepository.save(cartao);
  }

  @Scheduled(fixedDelay = 5000)
  public void avisaBloqueio() {

    Optional<List<Cartao>> cartaoPendenteBloqueio = cartaoRepository.findByStatus(EStatusCartao.BLOQUEIO_PENDENTE);
    
    if (cartaoPendenteBloqueio.isPresent()) {
      cartaoPendenteBloqueio.get().forEach(cartao -> {
        try {
          BloqueioCartaoResposnse response = consultaCartao.bloquearCartao(cartao.getNumeroCartao(),
            new BloqueioCartaoRequest("proposta"));
              // System.out.println("****************" + cartao.getNumeroCartao());
          if (response.getResultado().equals("BLOQUEADO")) {
            cartao.bloquear();
          }
        } catch (FeignException e) {}
      });
    }
  }

}
