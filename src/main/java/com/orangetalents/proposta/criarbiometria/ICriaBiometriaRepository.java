package com.orangetalents.proposta.criarbiometria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICriaBiometriaRepository extends JpaRepository<Biometria, Long> {

}
