package com.orangetalents.proposta.novaproposta;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, UUID> {

  Set<Proposta> findByCartaoIsNullAndStatus(Status elegivel);

  Optional<Proposta> findByDocumento(String documento);

}
