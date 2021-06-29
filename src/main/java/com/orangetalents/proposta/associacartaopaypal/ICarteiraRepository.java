package com.orangetalents.proposta.associacartaopaypal;

import java.util.Optional;

import com.orangetalents.proposta.associacartaoproposta.Cartao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarteiraRepository extends JpaRepository<Carteira, Long> {

  Optional<Carteira> findByCartaoAndCarteira(Cartao cartao, String carteira);

}
