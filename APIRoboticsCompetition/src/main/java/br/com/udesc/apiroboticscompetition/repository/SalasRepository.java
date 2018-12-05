package br.com.udesc.apiroboticscompetition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.udesc.apiroboticscompetition.model.Salas;

public interface SalasRepository extends JpaRepository<Salas, Long>{

}
