package com.orangetalents.proposta.associacartaoproposta;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartaoRepository extends JpaRepository<Cartao, Long> {

  Optional<List<Cartao>> findByStatus(EStatusCartao bloqueioPendente);

  Optional<Cartao> findByNumeroCartao(String numeroCartao);

  Optional<Cartao> findByUuid(String idCartao);
}
