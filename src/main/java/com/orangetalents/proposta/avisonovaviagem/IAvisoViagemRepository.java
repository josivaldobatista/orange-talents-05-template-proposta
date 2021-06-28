package com.orangetalents.proposta.avisonovaviagem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAvisoViagemRepository extends JpaRepository<AvisoViagem, Long> {
  
}
