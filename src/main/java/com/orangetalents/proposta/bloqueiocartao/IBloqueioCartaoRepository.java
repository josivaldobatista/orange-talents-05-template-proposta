package com.orangetalents.proposta.bloqueiocartao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBloqueioCartaoRepository extends JpaRepository<BloqueioCartao, Long> {

}
