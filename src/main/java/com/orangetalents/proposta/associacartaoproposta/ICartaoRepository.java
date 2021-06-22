package com.orangetalents.proposta.associacartaoproposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartaoRepository extends JpaRepository<Cartao, Long> {
  
}
